// ua.markiyan.sonara.dto.request.ArtistAlbumRequest
package ua.markiyan.sonara.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ArtistAlbumRequest(
        @NotBlank @Size(min = 2, max = 255) String title,
        @PastOrPresent LocalDate releaseDate,
        @Size(max = 255) String coverUrl
) {}
