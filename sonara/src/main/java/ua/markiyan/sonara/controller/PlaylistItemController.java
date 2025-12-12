package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.PlaylistItemRequest;
import ua.markiyan.sonara.dto.response.PlaylistItemResponse;
import ua.markiyan.sonara.hateoas.PlaylistItemModelAssembler;
import ua.markiyan.sonara.service.PlaylistItemService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/playlists/{playlistId}/items")
@RequiredArgsConstructor
public class PlaylistItemController {

    private final PlaylistItemService service;
    private final PlaylistItemModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<PlaylistItemResponse>> list(@PathVariable Long playlistId) {
        List<PlaylistItemResponse> items = service.list(playlistId);
        var models = items.stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(models, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlaylistItemController.class).list(playlistId)).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<EntityModel<PlaylistItemResponse>> create(@PathVariable Long playlistId, @Valid @RequestBody PlaylistItemRequest req) {
        PlaylistItemResponse created = service.create(playlistId, req);
        return ResponseEntity.status(201).body(assembler.toModel(created));
    }

    @GetMapping("/{itemId}")
    public EntityModel<PlaylistItemResponse> get(@PathVariable Long playlistId, @PathVariable Long itemId) {
        return assembler.toModel(service.get(playlistId, itemId));
    }

    @PatchMapping("/{itemId}")
    public EntityModel<PlaylistItemResponse> patch(@PathVariable Long playlistId, @PathVariable Long itemId, @RequestBody PlaylistItemRequest req) {
        return assembler.toModel(service.update(playlistId, itemId, req));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> delete(@PathVariable Long playlistId, @PathVariable Long itemId) {
        service.delete(playlistId, itemId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reorder")
    public ResponseEntity<?> reorder(@PathVariable Long playlistId, @RequestBody java.util.List<Long> itemIds) {
        service.reorder(playlistId, itemIds);
        return ResponseEntity.ok().build();
    }
}
