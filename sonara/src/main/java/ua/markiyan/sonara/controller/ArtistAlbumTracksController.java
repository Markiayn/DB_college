package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.ArtistAlbumTrackRequest;
import ua.markiyan.sonara.dto.response.TrackResponse;
import ua.markiyan.sonara.service.TrackService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artists/{artistId}/albums/{albumId}/tracks")
public class ArtistAlbumTracksController {

    private final TrackService service;

    @GetMapping
    public List<TrackResponse> list(@PathVariable Long artistId, @PathVariable Long albumId) {
        return service.listByAlbumWithArtsit(artistId, albumId);
    }

    @GetMapping("/{trackId}")
    public TrackResponse getOne(@PathVariable Long artistId,
                                @PathVariable Long albumId,
                                @PathVariable Long trackId) {
        return service.getOne(artistId, albumId, trackId);
    }

// заміни TrackRequest на TrackCreateRequest

    @PostMapping
    public ResponseEntity<TrackResponse> create(@PathVariable Long artistId,
                                                @PathVariable Long albumId,
                                                @Valid @RequestBody ArtistAlbumTrackRequest req) {
        TrackResponse created = service.createUnderAlbum(artistId, albumId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{trackId}")
    public TrackResponse patch(@PathVariable Long artistId,
                               @PathVariable Long albumId,
                               @PathVariable Long trackId,
                               @RequestBody ua.markiyan.sonara.dto.request.TrackUpdateRequest req) {
        // ensure album belongs to artist
        service.getOne(artistId, albumId, trackId);
        return service.update(trackId, req);
    }

    @DeleteMapping("/{trackId}")
    public ResponseEntity<?> delete(@PathVariable Long artistId,
                                    @PathVariable Long albumId,
                                    @PathVariable Long trackId) {
        // verify ownership
        service.getOne(artistId, albumId, trackId);
        service.delete(trackId);
        return ResponseEntity.noContent().build();
    }
}
