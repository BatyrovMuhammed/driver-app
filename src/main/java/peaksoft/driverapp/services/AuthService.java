package peaksoft.driverapp.services;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import peaksoft.driverapp.dto.auth.AuthRequest;
import peaksoft.driverapp.dto.auth.AuthResponse;
import peaksoft.driverapp.jwt.JwtUtils;

/**
 * @author B.Muhammed
 */
@Service
@AllArgsConstructor
public class AuthService {

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthResponse authenticate(AuthRequest authRequest) {
        Authentication authentication;

        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        ));

        String generatedToken = jwtUtils.generateToken(authentication);

        return AuthResponse.builder()
                .email(authRequest.getEmail())
                .token(generatedToken)
                .build();
    }
}
