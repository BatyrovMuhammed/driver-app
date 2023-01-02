package peaksoft.driverapp.dto.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter @Setter
public class ClientResponseDto {
    private UUID id;
    private String name;
    private String phoneNumber;
}
