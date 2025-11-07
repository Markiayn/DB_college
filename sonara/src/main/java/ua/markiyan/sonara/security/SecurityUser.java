// ua.markiyan.sonara.security.SecurityUser
package ua.markiyan.sonara.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.markiyan.sonara.entity.Users;

import java.util.Collection;
import java.util.List;

public record SecurityUser(Long id, String email, String password, Users.Status status)
        implements UserDetails {

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return List.of(); }
    @Override public String getUsername() { return email; }
    @Override public String getPassword() { return password; }

    @Override public boolean isEnabled() { return status == Users.Status.ACTIVE; }
    @Override public boolean isAccountNonLocked() { return status != Users.Status.BANNED && status != Users.Status.SUSPENDED; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }

    public static SecurityUser from(Users u){
        return new SecurityUser(u.getId(), u.getEmail(), u.getPasswordHash(), u.getStatus());
    }
}
