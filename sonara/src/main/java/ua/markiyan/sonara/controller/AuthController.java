package ua.markiyan.sonara.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.security.JwtUtil;
import ua.markiyan.sonara.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> req) {
        try {
            String email = req.get("email");
            String password = req.get("password");
            authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            var user = userService.findByEmail(email);
            String token = jwtUtil.generateToken(user.email(), Map.of("userId", user.id()));
            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).build();
        }
    }
}
