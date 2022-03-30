package peaksoft.driverapp.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.driverapp.dto.driver.DriverRegisterResponseDto;
import peaksoft.driverapp.dto.driver.DriverRequestDto;
import peaksoft.driverapp.dto.driver.mapper.DriverRegisterRequestMapper;
import peaksoft.driverapp.exceptions.BadRequestException;
import peaksoft.driverapp.models.entities.Driver;
import peaksoft.driverapp.models.entities.DriverLicense;
import peaksoft.driverapp.repositories.DriverRepository;

import javax.mail.MessagingException;

/**
 * @author Beksultan
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

        System.out.println(driverEmail);

        String message = """
                <p style="color: #6B8E23">
                    You have successfully registered to driver-app
                </p>
                <img src="https://images.pexels.com/photos/21696/pexels-photo.jpg?cs=srgb&dl=pexels-gerd-altmann-21696.jpg&fm=jpg">
                """;

        emailService.sendHtmlMessage(driverEmail, message);

        return modelMapper.map(savedDriver, DriverRegisterResponseDto.class);

    }
}
