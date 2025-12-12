package ua.markiyan.sonara.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.markiyan.sonara.entity.User;
import ua.markiyan.sonara.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // fallback to scanning all users if repository method isn't resolved by tooling
        User u = repo.findAll().stream()
                .filter(x -> x.getEmail() != null && x.getEmail().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username)));
        return org.springframework.security.core.userdetails.User.withUsername(u.getEmail())
                .password(u.getPasswordHash())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(u.getStatus() != User.Status.ACTIVE)
                .build();
    }
}
