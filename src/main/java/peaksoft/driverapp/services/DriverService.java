package peaksoft.driverapp.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.driverapp.dto.bankaccount.BankAccountRequestDto;
import peaksoft.driverapp.dto.bankaccount.BankAccountRequestResponse;
import peaksoft.driverapp.dto.car.CarSaveRequestDto;
import peaksoft.driverapp.dto.car.CarSaveRequestResponse;
import peaksoft.driverapp.dto.driver.DriverRegisterResponseDto;
import peaksoft.driverapp.dto.driver.DriverRequestDto;
import peaksoft.driverapp.dto.driver.mapper.DriverRegisterRequestMapper;
import peaksoft.driverapp.exceptions.BadRequestException;
import peaksoft.driverapp.exceptions.NotFoundException;
import peaksoft.driverapp.models.entities.BankAccount;
import peaksoft.driverapp.models.entities.Car;
import peaksoft.driverapp.models.entities.Driver;
import peaksoft.driverapp.models.entities.DriverLicense;
import peaksoft.driverapp.repositories.DriverRepository;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.UUID;

/**
 * @author B.Muhammed
 */
@Service
@AllArgsConstructor
public class DriverService {

    private final DriverRegisterRequestMapper driverRegisterRequestMapper;
    private final ModelMapper modelMapper;
    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public DriverRegisterResponseDto register(DriverRequestDto newDriver) throws MessagingException {

        String email = newDriver.getEmail();

        if (driverRepository.existsByAuthInfoEmail(email)) {
            throw new BadRequestException(
                    String.format("driver with email = %s already in use", email)
            );
        }

        // DriverRequestDto -> Driver & DriverLicense
        // encode password
        String encodedPassword = passwordEncoder.encode(newDriver.getPassword());
        newDriver.setPassword(encodedPassword);

        Driver driver = driverRegisterRequestMapper.convert(newDriver);

        DriverLicense driverLicense = modelMapper.map(newDriver.getDriverLicense(), DriverLicense.class);

        assert driver != null;
        driver.setDriverLicense(driverLicense);
        driverLicense.setDriver(driver);

        Driver savedDriver = driverRepository.save(driver);

        String driverEmail = savedDriver.getEmail();

        String message = """
                <p style="color: #6B8E23">
                    You have successfully registered to driver-app
                </p>
                <img src="https://images.pexels.com/photos/21696/pexels-photo.jpg?cs=srgb&dl=pexels-gerd-altmann-21696.jpg&fm=jpg">
                """;

        try {
            emailService.sendHtmlMessage(driverEmail, message);
        } catch (Exception e) {}

        return modelMapper.map(savedDriver, DriverRegisterResponseDto.class);

    }

    @Transactional
    public CarSaveRequestResponse addCarToDriver(String email, CarSaveRequestDto newCar) {
        Driver driver = getDriverByEmail(email);

        Car car = modelMapper.map(newCar, Car.class);

        car.setCarOwner(driver);

        driver.setCar(car);

        String carName = car.getBrand() + " " + car.getModel();

        emailService.send(driver.getEmail(), "You have successfully added your car with name " + carName + " to driver-app");

        return CarSaveRequestResponse.builder()
                .driverName(driver.getDriverName())
                .carName(carName)
                .build();
    }

    private Driver getDriverByEmail(String email) {
        return driverRepository.findDriverByAuthInfoEmail(email)
                .orElseThrow(() -> new NotFoundException(
                        String.format("driver with email = %s does not exists", email)
                ));
    }

    @Transactional
    public BankAccountRequestResponse addBankAccount(String email, BankAccountRequestDto newBankAccount) {
        Driver driver = getDriverByEmail(email);

        BankAccount bankAccount = modelMapper.map(newBankAccount, BankAccount.class);

        driver.setBankAccount(bankAccount);

        emailService.send(
                driver.getEmail(),
                "You have successfully added bank account with account number = " + bankAccount.getAccountNumber() + "to your driver-app"
                );

        return BankAccountRequestResponse.builder()
                .name(bankAccount.getFullName())
                .accountNumber(bankAccount.getAccountNumber())
                .build();
    }

    public Map<String, String> deleteById(UUID driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("driver with id = %s does not exists", driverId)
                ));

        driverRepository.deleteById(driverId);

        emailService.send(driver.getEmail(), "Your account deleted, Good luck!!!");

        return Map.of(
                "status", "SUCCESS"
        );
    }
}
