package peaksoft.driverapp.apis;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.driverapp.dto.auth.AuthRequest;
import peaksoft.driverapp.dto.auth.AuthResponse;
import peaksoft.driverapp.services.AuthService;

import javax.annotation.security.PermitAll;

/**
 * @author Beksultan
 */
@RestController
@RequestMapping("/api/authentication")
@AllArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping
    @PermitAll
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }
}
