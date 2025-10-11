package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.UserRequest;
import ua.markiyan.sonara.dto.response.UserResponse;
import ua.markiyan.sonara.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable Long id) {
        return service.get(id);
    }
}
