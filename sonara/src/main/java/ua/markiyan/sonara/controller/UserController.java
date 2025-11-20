package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.UserRequest;
import ua.markiyan.sonara.dto.response.UserResponse;
import ua.markiyan.sonara.hateoas.GenericModelAssembler;
import ua.markiyan.sonara.service.UserService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final GenericModelAssembler<UserResponse> userAssembler;

    @PostMapping
    public ResponseEntity<EntityModel<UserResponse>> create(@Valid @RequestBody UserRequest req) {
        UserResponse created = service.create(req);
        EntityModel<UserResponse> model = userAssembler.toModel(created);
        return ResponseEntity.created(model.getRequiredLink("self").toUri()).body(model);
    }

    @GetMapping("/{id}")
    public EntityModel<UserResponse> get(@PathVariable Long id) {
        return userAssembler.toModel(service.get(id));
    }

    @GetMapping
    public PagedModel<EntityModel<UserResponse>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) LocalDate createdAt,
            Pageable pageable,
            PagedResourcesAssembler<UserResponse> pagedAssembler
    ) {
        Page<UserResponse> page = service.search(name, country, status, createdAt, pageable);
        return pagedAssembler.toModel(page, userAssembler);
    }
}
