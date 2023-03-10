package peaksoft.driverapp.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import peaksoft.driverapp.models.enums.Authority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

/**
 * @author B.Muhammed
 */
@Entity
@Table(name = "authInfo")
@Getter @Setter
public class AuthInfo implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String email;
    private String password;
    private Authority authority;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }
}
