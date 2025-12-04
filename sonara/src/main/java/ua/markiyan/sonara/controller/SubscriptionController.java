package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.SubscriptionRequest;
import ua.markiyan.sonara.dto.response.SubscriptionResponse;
import ua.markiyan.sonara.service.SubscriptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;

    @GetMapping("/api/users/{userId}/subscriptions")
    public List<SubscriptionResponse> list(@PathVariable Long userId) {
        return service.listByUser(userId);
    }

    @PostMapping("/api/users/{userId}/subscriptions")
    public ResponseEntity<SubscriptionResponse> create(@PathVariable Long userId, @Valid @RequestBody SubscriptionRequest req) {
        return ResponseEntity.status(201).body(service.create(userId, req));
    }
}

