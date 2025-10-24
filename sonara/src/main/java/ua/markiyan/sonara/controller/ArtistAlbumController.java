package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.ArtistAlbumRequest;
import ua.markiyan.sonara.dto.response.AlbumResponse;
import ua.markiyan.sonara.dto.response.TrackResponse;
import ua.markiyan.sonara.service.AlbumService;

import java.util.List;


@RestController
@RequestMapping("/api/artists/{artistId}/albums")
@RequiredArgsConstructor
public class ArtistAlbumController {

    private final AlbumService albumService;


    @GetMapping
    public List<AlbumResponse> list(@PathVariable Long artistId) {
        return albumService.listByArtist(artistId);
    }

    @GetMapping("{albumId}")
    public AlbumResponse getAlbumOfArtist(@PathVariable Long artistId,
                                          @PathVariable Long albumId) {
        return albumService.getUnderArtist(artistId, albumId);
    }

    @PostMapping()
    public ResponseEntity<AlbumResponse> create(@PathVariable Long artistId,
                                                @Valid @RequestBody ArtistAlbumRequest req) {
        AlbumResponse created = albumService.createUnderArtist(artistId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
