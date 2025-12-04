package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.AlbumTrackRequest;
import ua.markiyan.sonara.dto.response.TrackResponse;
import ua.markiyan.sonara.service.TrackService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/albums/{albumId}/tracks")
public class AlbumTrackController {

    private final TrackService trackService;


    @GetMapping
    public Page<TrackResponse> search(@PathVariable Long albumId,
                                      @RequestParam(required = false) String title,
                                      @RequestParam(required = false) Integer durationSec,
                                      @RequestParam(required = false) Boolean explicitFlag,
                                      Pageable pageable
    ) {
        return trackService.searchInAlbum(albumId, title, durationSec, explicitFlag, pageable);
    }



    @PostMapping
    public ResponseEntity<TrackResponse> create(@PathVariable Long albumId,
                                                @Valid @RequestBody AlbumTrackRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(trackService.create(albumId, req)); // <-- передаємо albumId із path
    }

    @PatchMapping("/{trackId}")
    public TrackResponse patch(@PathVariable Long albumId, @PathVariable Long trackId,
                               @RequestBody ua.markiyan.sonara.dto.request.TrackUpdateRequest req) {
        // ensure track belongs to album
        trackService.get(trackId);
        // service.update will perform the update
        return trackService.update(trackId, req);
    }

    @DeleteMapping("/{trackId}")
    public ResponseEntity<?> delete(@PathVariable Long albumId, @PathVariable Long trackId) {
        // optionally verify track in album
        trackService.delete(trackId);
        return ResponseEntity.noContent().build();
    }
}
