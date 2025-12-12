package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.UserRequest;
import ua.markiyan.sonara.dto.response.UserResponse;
import ua.markiyan.sonara.hateoas.UserModelAssembler;
import ua.markiyan.sonara.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserModelAssembler assembler;
    private final PagedResourcesAssembler<UserResponse> pagedAssembler;

    @PostMapping
    public ResponseEntity<EntityModel<UserResponse>> create(@Valid @RequestBody UserRequest req) {
        UserResponse created = service.create(req);
        EntityModel<UserResponse> body = assembler.toModel(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @GetMapping("/{id}")
    public EntityModel<UserResponse> get(@PathVariable Long id) {
        return assembler.toModel(service.get(id));
    }

    @GetMapping
    public PagedModel<EntityModel<UserResponse>> search(
            @RequestParam(required = false) String q,
            org.springframework.data.domain.Pageable pageable
    ) {
        org.springframework.data.domain.Page<UserResponse> page = service.search(q, pageable);
        return pagedAssembler.toModel(page, assembler);
    }

    @PatchMapping("/{id}")
    public EntityModel<UserResponse> patch(@PathVariable Long id, @RequestBody ua.markiyan.sonara.dto.request.UserUpdateRequest req) {
        return assembler.toModel(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
