package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.ArtistAlbumTrackRequest;
import ua.markiyan.sonara.dto.response.TrackResponse;
import ua.markiyan.sonara.service.TrackService;
import ua.markiyan.sonara.hateoas.TrackModelAssembler;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/artists/{artistId}/albums/{albumId}/tracks")
public class ArtistAlbumTracksController {

    private final TrackService service;
    private final TrackModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<TrackResponse>> list(@PathVariable Long artistId, @PathVariable Long albumId) {
        List<TrackResponse> list = service.listByAlbumWithArtsit(artistId, albumId);
        var models = list.stream().map(assembler::toModel).toList();
        return CollectionModel.of(models, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ArtistAlbumTracksController.class).list(artistId, albumId)).withSelfRel());
    }

    @GetMapping("/{trackId}")
    public EntityModel<TrackResponse> getOne(@PathVariable Long artistId,
                                @PathVariable Long albumId,
                                @PathVariable Long trackId) {
        return assembler.toModel(service.getOne(artistId, albumId, trackId));
    }

// заміни TrackRequest на TrackCreateRequest

    @PostMapping
    public ResponseEntity<EntityModel<TrackResponse>> create(@PathVariable Long artistId,
                                                @PathVariable Long albumId,
                                                @Valid @RequestBody ArtistAlbumTrackRequest req) {
        TrackResponse created = service.createUnderAlbum(artistId, albumId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(created));
    }

    @PatchMapping("/{trackId}")
    public EntityModel<TrackResponse> patch(@PathVariable Long artistId,
                               @PathVariable Long albumId,
                               @PathVariable Long trackId,
                               @RequestBody ua.markiyan.sonara.dto.request.TrackUpdateRequest req) {
        // ensure album belongs to artist
        service.getOne(artistId, albumId, trackId);
        return assembler.toModel(service.update(trackId, req));
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
