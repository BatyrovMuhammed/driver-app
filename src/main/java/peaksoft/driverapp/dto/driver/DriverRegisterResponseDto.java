package peaksoft.driverapp.dto.driver;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author B.Muhammed
 */
@Getter @Setter
public class DriverRegisterResponseDto {
    private UUID id;
    private String driverName;
    private String email;
}
