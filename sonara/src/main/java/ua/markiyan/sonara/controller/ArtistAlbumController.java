package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.ArtistAlbumRequest;
import ua.markiyan.sonara.dto.response.AlbumResponse;
import ua.markiyan.sonara.service.AlbumService;
import ua.markiyan.sonara.hateoas.AlbumModelAssembler;

import java.util.List;


@RestController
@RequestMapping("/api/artists/{artistId}/albums")
@RequiredArgsConstructor
public class ArtistAlbumController {

    private final AlbumService albumService;
    private final AlbumModelAssembler assembler;


    @GetMapping
    public CollectionModel<EntityModel<AlbumResponse>> list(@PathVariable Long artistId) {
        List<AlbumResponse> list = albumService.listByArtist(artistId);
        var models = list.stream().map(assembler::toModel).toList();
        return CollectionModel.of(models, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ArtistAlbumController.class).list(artistId)).withSelfRel());
    }

    @GetMapping("{albumId}")
    public EntityModel<AlbumResponse> getAlbumOfArtist(@PathVariable Long artistId,
                                          @PathVariable Long albumId) {
        return assembler.toModel(albumService.getUnderArtist(artistId, albumId));
    }

    @PostMapping()
    public ResponseEntity<EntityModel<AlbumResponse>> create(@PathVariable Long artistId,
                                                @Valid @RequestBody ArtistAlbumRequest req) {
        AlbumResponse created = albumService.createUnderArtist(artistId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(created));
    }

    @PatchMapping("/{albumId}")
    public EntityModel<AlbumResponse> patch(@PathVariable Long artistId, @PathVariable Long albumId,
                               @RequestBody ua.markiyan.sonara.dto.request.AlbumUpdateRequest req) {
        // reuse top-level update; validate that album belongs to artist
        AlbumResponse updated = albumService.update(albumId, req);
        // ensure updated album belongs to artist
        if (!updated.artistId().equals(artistId)) {
            throw new ua.markiyan.sonara.exception.NotFoundException("Album %d not found for artist %d".formatted(albumId, artistId));
        }
        return updated == null ? null : assembler.toModel(updated);
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<?> delete(@PathVariable Long artistId, @PathVariable Long albumId) {
        // verify album belongs to artist
        albumService.getUnderArtist(artistId, albumId);
        albumService.delete(albumId);
        return ResponseEntity.noContent().build();
    }
}
