package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.TrackRequest;
import ua.markiyan.sonara.dto.response.TrackResponse;
import ua.markiyan.sonara.service.TrackService;

@RestController
@RequestMapping("/api/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @PostMapping
    public ResponseEntity<TrackResponse> create(@Valid @RequestBody TrackRequest  req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trackService.create(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackResponse> get(@PathVariable Long id) {
        TrackResponse dto = trackService.get(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public Page<TrackResponse> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer durationSec,
            @RequestParam(required = false) Boolean explicitFlag,
            Pageable pageable
    ) {
        return trackService.search(title, durationSec, explicitFlag, pageable);
    }

    @PatchMapping("/{id}")
    public TrackResponse patch(@PathVariable Long id, @RequestBody ua.markiyan.sonara.dto.request.TrackUpdateRequest req) {
        return trackService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        trackService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
