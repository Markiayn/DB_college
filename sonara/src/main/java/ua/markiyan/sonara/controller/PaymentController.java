package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.PaymentRequest;
import ua.markiyan.sonara.dto.response.PaymentResponse;
import ua.markiyan.sonara.service.PaymentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping("/api/subscriptions/{subscriptionId}/payments")
    public ResponseEntity<PaymentResponse> create(@PathVariable Long subscriptionId, @Valid @RequestBody PaymentRequest req) {
        return ResponseEntity.status(201).body(service.create(subscriptionId, req));
    }

    @GetMapping("/api/subscriptions/{subscriptionId}/payments")
    public List<PaymentResponse> list(@PathVariable Long subscriptionId) {
        return service.listBySubscription(subscriptionId);
    }

    @GetMapping("/api/payments/{paymentId}")
    public PaymentResponse get(@PathVariable Long paymentId) {
        return service.get(paymentId);
    }

    @GetMapping("/api/users/{userId}/payments")
    public List<PaymentResponse> listByUser(@PathVariable Long userId) {
        return service.listByUser(userId);
    }
}

