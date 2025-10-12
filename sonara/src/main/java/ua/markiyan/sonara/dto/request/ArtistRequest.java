package ua.markiyan.sonara.dto.request;

import jakarta.validation.constraints.*;

public record ArtistRequest(
        @NotBlank @Size(min = 2, max = 100) String name,
        @Size(max = 80) String country,
        @PastOrPresent @NotNull @Min(1000) @Max(9999) Integer startYear,
        @NotBlank @Size(min = 2, max = 2000) String bio
) {}
