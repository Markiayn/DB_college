package ua.markiyan.sonara.dto.request;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record AlbumUpdateRequest(
        @Size(min = 1, max = 500) String title,
        LocalDate releaseDate,
        String coverUrl
) {}

