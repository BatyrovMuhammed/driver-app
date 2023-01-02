package peaksoft.driverapp.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class AuthResponse {
    private String email;
    private String token;
}
