package peaksoft.driverapp.apis;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.driverapp.dto.driver.DriverRegisterResponseDto;
import peaksoft.driverapp.dto.driver.DriverRequestDto;
import peaksoft.driverapp.services.DriverService;

import javax.annotation.security.PermitAll;
import javax.mail.MessagingException;
import javax.validation.Valid;

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
}
