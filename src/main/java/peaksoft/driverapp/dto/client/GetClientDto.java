package peaksoft.driverapp.dto.client;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author B.Muhammed
 */
@Getter @Setter
public class GetClientDto {
    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;
}
