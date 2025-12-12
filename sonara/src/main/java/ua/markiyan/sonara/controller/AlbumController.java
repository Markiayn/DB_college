package ua.markiyan.sonara.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.AlbumRequest;
import ua.markiyan.sonara.dto.response.AlbumResponse;

import ua.markiyan.sonara.service.AlbumService;
import ua.markiyan.sonara.hateoas.AlbumModelAssembler;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;
    private final AlbumModelAssembler assembler;

    @PostMapping
    public ResponseEntity<EntityModel<AlbumResponse>> create(@Valid @RequestBody AlbumRequest req) {
        AlbumResponse created = albumService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(created));
    }

    @GetMapping("/{id}")
    public EntityModel<AlbumResponse> get(@PathVariable Long id) {
        return assembler.toModel(albumService.get(id));
    }

    @GetMapping
    public PagedModel<EntityModel<AlbumResponse>> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) LocalDate releaseDate,
            Pageable pageable, // автоматично підхопить page, size, sort
            PagedResourcesAssembler<AlbumResponse> pagedAssembler
    ) {
        Page<AlbumResponse> page = albumService.search(title, releaseDate, pageable);
        return pagedAssembler.toModel(page, assembler);
    }

    @PatchMapping("/{id}")
    public EntityModel<AlbumResponse> patch(@PathVariable Long id, @RequestBody ua.markiyan.sonara.dto.request.AlbumUpdateRequest req) {
        return assembler.toModel(albumService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        albumService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
