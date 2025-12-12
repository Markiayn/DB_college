package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.ArtistRequest;
import ua.markiyan.sonara.dto.response.ArtistResponse;
import ua.markiyan.sonara.service.ArtistService;
import ua.markiyan.sonara.hateoas.ArtistModelAssembler;


@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;
    private final ArtistModelAssembler assembler;
    private final PagedResourcesAssembler<ArtistResponse> pagedAssembler;

    @PostMapping
    public ResponseEntity<EntityModel<ArtistResponse>> create(@Valid @RequestBody ArtistRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(artistService.create(req)));
    }

    @GetMapping("/{id}")
    public EntityModel<ArtistResponse> get(@PathVariable Long id) {
        return assembler.toModel(artistService.get(id));
    }

    @GetMapping
    public PagedModel<EntityModel<ArtistResponse>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String country,
            Pageable pageable // автоматично підхопить page, size, sort
    ) {
        Page<ArtistResponse> page = artistService.search(name, country, pageable);
        return pagedAssembler.toModel(page, assembler);
    }

    @PatchMapping("/{id}")
    public EntityModel<ArtistResponse> patch(@PathVariable Long id, @RequestBody ua.markiyan.sonara.dto.request.ArtistUpdateRequest req) {
        return assembler.toModel(artistService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        artistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
