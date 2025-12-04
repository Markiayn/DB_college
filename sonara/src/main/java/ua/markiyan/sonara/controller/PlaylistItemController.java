package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.PlaylistItemRequest;
import ua.markiyan.sonara.dto.response.PlaylistItemResponse;
import ua.markiyan.sonara.service.PlaylistItemService;

import java.util.List;

@RestController
@RequestMapping("/api/playlists/{playlistId}/items")
@RequiredArgsConstructor
public class PlaylistItemController {

    private final PlaylistItemService service;

    @GetMapping
    public List<PlaylistItemResponse> list(@PathVariable Long playlistId) {
        return service.list(playlistId);
    }

    @PostMapping
    public ResponseEntity<PlaylistItemResponse> create(@PathVariable Long playlistId, @Valid @RequestBody PlaylistItemRequest req) {
        return ResponseEntity.status(201).body(service.create(playlistId, req));
    }

    @GetMapping("/{itemId}")
    public PlaylistItemResponse get(@PathVariable Long playlistId, @PathVariable Long itemId) {
        return service.get(playlistId, itemId);
    }

    @PatchMapping("/{itemId}")
    public PlaylistItemResponse patch(@PathVariable Long playlistId, @PathVariable Long itemId, @RequestBody PlaylistItemRequest req) {
        return service.update(playlistId, itemId, req);
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

