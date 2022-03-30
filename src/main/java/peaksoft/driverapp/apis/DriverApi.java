package peaksoft.driverapp.apis;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import peaksoft.driverapp.dto.bankaccount.BankAccountRequestDto;
import peaksoft.driverapp.dto.bankaccount.BankAccountRequestResponse;
import peaksoft.driverapp.dto.car.CarSaveRequestDto;
import peaksoft.driverapp.dto.car.CarSaveRequestResponse;
import peaksoft.driverapp.dto.driver.DriverRegisterResponseDto;
import peaksoft.driverapp.dto.driver.DriverRequestDto;
import peaksoft.driverapp.services.DriverService;

import javax.annotation.security.PermitAll;
import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

/**
 * @author Beksultan
 */
@RestController
@RequestMapping("api/drivers")
@AllArgsConstructor
public class DriverApi {

    private final DriverService driverService;

    @PostMapping("/register")
    @PermitAll
    public DriverRegisterResponseDto register(@Valid @RequestBody DriverRequestDto newDriver) throws MessagingException {
        return driverService.register(newDriver);
    }

    @PostMapping("/add/car")
    @PreAuthorize("hasAuthority('DRIVER')")
    public CarSaveRequestResponse addCarToDriver(Authentication authentication,
                                                 @RequestBody CarSaveRequestDto newCar) {
        return driverService.addCarToDriver(authentication.getName(), newCar);
    }

    @PostMapping("/add/bank_account")
    @PreAuthorize("hasAuthority('DRIVER')")
    public BankAccountRequestResponse addBankAccount(Authentication authentication,
                                                     @RequestBody BankAccountRequestDto newBankAccount) {
        return driverService.addBankAccount(authentication.getName(), newBankAccount);
    }

    @DeleteMapping("/delete/{driverId}")
    @PreAuthorize("hasAnyAuthority('DRIVER', 'ADMIN')")
    public Map<String, String> deleteById(@PathVariable UUID driverId) {
        return driverService.deleteById(driverId);
    }

}
