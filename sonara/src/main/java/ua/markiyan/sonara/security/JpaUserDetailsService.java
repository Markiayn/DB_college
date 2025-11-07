// ua.markiyan.sonara.security.JpaUserDetailsService
package ua.markiyan.sonara.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import ua.markiyan.sonara.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final UsersRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repo.findByEmail(email)
                .map(SecurityUser::from)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }
}
