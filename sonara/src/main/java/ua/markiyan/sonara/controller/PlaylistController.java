package ua.markiyan.sonara.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.PlaylistRequest;
import ua.markiyan.sonara.dto.response.PlaylistResponse;
import ua.markiyan.sonara.service.PlaylistService;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    public List<PlaylistResponse> list(@PathVariable Long userId) {
        return playlistService.listByUser(userId);
    }

    @PostMapping
    public ResponseEntity<PlaylistResponse> create(@PathVariable Long userId, @Valid @RequestBody PlaylistRequest req) {
        return ResponseEntity.status(201).body(playlistService.create(userId, req));
    }
}

