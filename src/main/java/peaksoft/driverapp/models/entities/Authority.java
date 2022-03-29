package peaksoft.driverapp.models.entities;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Beksultan
 */
public enum Authority implements GrantedAuthority {
    CLIENT,
    DRIVER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
