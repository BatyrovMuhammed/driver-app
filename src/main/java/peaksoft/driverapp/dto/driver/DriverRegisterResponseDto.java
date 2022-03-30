package peaksoft.driverapp.dto.driver;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Beksultan
 */
@Getter @Setter
public class DriverRegisterResponseDto {
    private UUID id;
    private String driverName;
    private String email;
}
