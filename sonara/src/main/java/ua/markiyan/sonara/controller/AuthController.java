// ua.markiyan.sonara.api.AuthController
package ua.markiyan.sonara.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.auth.AuthService;
import ua.markiyan.sonara.dto.security.request.LoginRequest;
import ua.markiyan.sonara.dto.security.request.RegisterRequest;
import ua.markiyan.sonara.dto.security.response.AuthResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService auth;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest r){
        String token = auth.register(r.email(), r.password(), r.name());
        return ResponseEntity.ok(new AuthResponse(token, "Bearer"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest r){
        String token = auth.login(r.email(), r.password());
        return ResponseEntity.ok(new AuthResponse(token, "Bearer"));
    }
}
