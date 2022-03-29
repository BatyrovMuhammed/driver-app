package peaksoft.driverapp.dto.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

/**
 * @author Beksultan
 */
@Getter @Setter
public class ClientResponseDto {
    private UUID id;
    private String name;
    private String phoneNumber;
}
