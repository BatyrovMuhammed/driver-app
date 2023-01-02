package peaksoft.driverapp.models.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author B.Muhammed
 */
public enum Authority implements GrantedAuthority {
    CLIENT,
    DRIVER,
    ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
