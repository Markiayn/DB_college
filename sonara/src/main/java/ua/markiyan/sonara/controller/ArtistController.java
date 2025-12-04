package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.ArtistRequest;
import ua.markiyan.sonara.dto.response.ArtistResponse;
import ua.markiyan.sonara.service.ArtistService;


@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @PostMapping
    public ResponseEntity<ArtistResponse> create(@Valid @RequestBody ArtistRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistService.create(req));
    }

    @GetMapping("/{id}")
    public ArtistResponse get(@PathVariable Long id) {
        return artistService.get(id);
    }

    @GetMapping
    public Page<ArtistResponse> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String country,
            Pageable pageable // автоматично підхопить page, size, sort
    ) {
        return artistService.search(name, country, pageable);
    }

    @PatchMapping("/{id}")
    public ArtistResponse patch(@PathVariable Long id, @RequestBody ua.markiyan.sonara.dto.request.ArtistUpdateRequest req) {
        return artistService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        artistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
