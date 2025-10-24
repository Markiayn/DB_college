package ua.markiyan.sonara.dto.request;

import jakarta.validation.constraints.*;

public record ArtistRequest(
        @NotBlank @Size(min = 2, max = 100) String name,
        @Size(max = 80) String country,
        @NotNull @Min(1900) @Max(2026) Integer startYear,
        @NotBlank @Size(min = 2, max = 2000) String bio
) {}
