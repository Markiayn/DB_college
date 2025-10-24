package ua.markiyan.sonara.dto.response;

import java.time.LocalDate;

public record AlbumResponse(
        Long id,
        String title,
        LocalDate releaseDate,
        String coverUrl,
        Long artistId
) {}
