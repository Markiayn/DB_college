package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.TrackRequest;
import ua.markiyan.sonara.dto.response.TrackResponse;
import ua.markiyan.sonara.hateoas.TrackModelAssembler;
import ua.markiyan.sonara.service.TrackService;

@RestController
@RequestMapping("/api/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;
    private final TrackModelAssembler assembler;
    private final PagedResourcesAssembler<TrackResponse> pagedAssembler;

    @PostMapping
    public ResponseEntity<EntityModel<TrackResponse>> create(@Valid @RequestBody TrackRequest  req) {
        TrackResponse created = trackService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TrackResponse>> get(@PathVariable Long id) {
        TrackResponse dto = trackService.get(id);
        return ResponseEntity.ok(assembler.toModel(dto));
    }

    @GetMapping
    public PagedModel<EntityModel<TrackResponse>> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer durationSec,
            @RequestParam(required = false) Boolean explicitFlag,
            Pageable pageable
    ) {
        Page<TrackResponse> page = trackService.search(title, durationSec, explicitFlag, pageable);
        return pagedAssembler.toModel(page, assembler);
    }

    @PatchMapping("/{id}")
    public EntityModel<TrackResponse> patch(@PathVariable Long id, @RequestBody ua.markiyan.sonara.dto.request.TrackUpdateRequest req) {
        return assembler.toModel(trackService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        trackService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
