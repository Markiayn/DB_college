package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.UserRequest;
import ua.markiyan.sonara.dto.response.ArtistResponse;
import ua.markiyan.sonara.dto.response.UserResponse;
import ua.markiyan.sonara.service.UserService;

import java.time.LocalDate;

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

    @GetMapping
    public Page<UserResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDate createdAt,
            Pageable pageable
    ) {
        return service.search(name, country, status, createdAt, pageable);
    }
}
