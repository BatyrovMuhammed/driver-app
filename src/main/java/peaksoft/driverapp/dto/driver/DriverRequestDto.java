package peaksoft.driverapp.dto.driver;

import lombok.Getter;
import lombok.Setter;
import peaksoft.driverapp.annotations.password.ValidPassword;
import peaksoft.driverapp.annotations.phoneNumber.ValidPhoneNumber;
import peaksoft.driverapp.dto.driver_license.DriverLicenseRequestDto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author Beksultan
 */
@Getter @Setter
public class DriverRequestDto {

    @NotBlank
    private String name;

    @Email
    private String email;

    @ValidPassword
    private String password;

    @ValidPhoneNumber
    private String phoneNumber;

    @Min(value = 0)
    @Max(value = 100)
    private int experience;

    @Valid
    private DriverLicenseRequestDto driverLicense;
}
