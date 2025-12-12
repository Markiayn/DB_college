package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.PaymentRequest;
import ua.markiyan.sonara.dto.response.PaymentResponse;
import ua.markiyan.sonara.hateoas.PaymentModelAssembler;
import ua.markiyan.sonara.service.PaymentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;
    private final PaymentModelAssembler assembler;

    @PostMapping("/api/subscriptions/{subscriptionId}/payments")
    public ResponseEntity<EntityModel<PaymentResponse>> create(@PathVariable Long subscriptionId, @Valid @RequestBody PaymentRequest req) {
        PaymentResponse created = service.create(subscriptionId, req);
        return ResponseEntity.status(201).body(assembler.toModel(created));
    }

    @GetMapping("/api/subscriptions/{subscriptionId}/payments")
    public CollectionModel<EntityModel<PaymentResponse>> list(@PathVariable Long subscriptionId) {
        List<PaymentResponse> list = service.listBySubscription(subscriptionId);
        var models = list.stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(models, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PaymentController.class).list(subscriptionId)).withSelfRel());
    }

    @GetMapping("/api/payments/{paymentId}")
    public EntityModel<PaymentResponse> get(@PathVariable Long paymentId) {
        return assembler.toModel(service.get(paymentId));
    }

    @GetMapping("/api/users/{userId}/payments")
    public CollectionModel<EntityModel<PaymentResponse>> listByUser(@PathVariable Long userId) {
        List<PaymentResponse> list = service.listByUser(userId);
        var models = list.stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(models, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PaymentController.class).listByUser(userId)).withSelfRel());
    }
}
