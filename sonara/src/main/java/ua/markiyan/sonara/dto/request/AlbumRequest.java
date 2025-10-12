package ua.markiyan.sonara.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record AlbumRequest(
        @NotBlank @Size(min = 2, max = 255) String title,
        @PastOrPresent LocalDate releaseDate, // ISO-формат yyyy-MM-dd
        @Size(max = 255) String coverUrl,
        @NotNull Long artistId
) {}
