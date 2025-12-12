package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.SubscriptionRequest;
import ua.markiyan.sonara.dto.response.SubscriptionResponse;
import ua.markiyan.sonara.hateoas.SubscriptionModelAssembler;
import ua.markiyan.sonara.service.SubscriptionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;
    private final SubscriptionModelAssembler assembler;

    @GetMapping("/api/users/{userId}/subscriptions")
    public CollectionModel<EntityModel<SubscriptionResponse>> list(@PathVariable Long userId) {
        List<SubscriptionResponse> list = service.listByUser(userId);
        var models = list.stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(models, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SubscriptionController.class).list(userId)).withSelfRel());
    }

    @PostMapping("/api/users/{userId}/subscriptions")
    public ResponseEntity<EntityModel<SubscriptionResponse>> create(@PathVariable Long userId, @Valid @RequestBody SubscriptionRequest req) {
        SubscriptionResponse created = service.create(userId, req);
        return ResponseEntity.status(201).body(assembler.toModel(created));
    }
}
