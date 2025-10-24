package ua.markiyan.sonara.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.markiyan.sonara.dto.request.AlbumRequest;
import ua.markiyan.sonara.dto.response.AlbumResponse;

import ua.markiyan.sonara.dto.response.ArtistResponse;
import ua.markiyan.sonara.service.AlbumService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @PostMapping
    public ResponseEntity<AlbumResponse> create(@Valid @RequestBody AlbumRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(albumService.create(req));
    }

    @GetMapping("/{id}")
    public AlbumResponse get(@PathVariable Long id) {
        return albumService.get(id);
    }

    @GetMapping
    public Page<AlbumResponse> search(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) LocalDate releaseDate,
            Pageable pageable // автоматично підхопить page, size, sort
    ) {
        return albumService.search(title, releaseDate, pageable);
    }
}
