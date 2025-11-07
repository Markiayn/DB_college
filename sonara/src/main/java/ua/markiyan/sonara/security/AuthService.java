// ua.markiyan.sonara.auth.AuthService
package ua.markiyan.sonara.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.markiyan.sonara.entity.Users;
import ua.markiyan.sonara.repository.UsersRepository;
import ua.markiyan.sonara.security.JwtService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsersRepository repo;
    private final PasswordEncoder enc;
    private final AuthenticationManager authManager;
    private final JwtService jwt;

    public String register(String email, String rawPassword, String name){
        if (repo.existsByEmail(email)) throw new IllegalArgumentException("Email already used");
        var u = Users.builder()
                .email(email)
                .passwordHash(enc.encode(rawPassword))
                .name(name)
                .status(Users.Status.ACTIVE)
                .build();
        repo.save(u);
        return jwt.generate(u.getEmail(), Map.of()); // без ролей
    }

    public String login(String email, String rawPassword){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(email, rawPassword));
        var u = repo.findByEmail(email).orElseThrow();
        if (u.getStatus() != Users.Status.ACTIVE) throw new RuntimeException("Account disabled");
        return jwt.generate(u.getEmail(), Map.of());
    }
}
