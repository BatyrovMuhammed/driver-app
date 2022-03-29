package peaksoft.driverapp.dto.client;

import lombok.Getter;
import lombok.Setter;
import peaksoft.driverapp.annotations.ValidPhoneNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Beksultan
 */
@Getter @Setter
public class ClientSaveDto {
    @NotBlank
    private String name;
    @Email
    private String email;
    @ValidPhoneNumber
    private String phoneNumber;
    @NotBlank
    private String password;
    @NotBlank
    private String accountNumber;

}
