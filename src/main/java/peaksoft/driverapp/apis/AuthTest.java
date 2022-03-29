package peaksoft.driverapp.apis;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Beksultan
 */
@RestController
@RequestMapping("api/auth")
public class AuthTest {

    @GetMapping("/test")
    @PreAuthorize("hasAnyAuthority('CLIENT', 'DRIVER')")
    public Map<String, String> test(Authentication authentication) {
        return Map.of(
                "email", authentication.getName()
        );
    }
}
