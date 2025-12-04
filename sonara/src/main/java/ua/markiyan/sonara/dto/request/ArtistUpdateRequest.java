package ua.markiyan.sonara.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record ArtistUpdateRequest(
        @Size(min = 2, max = 100) String name,
        @Size(max = 80) String country,
        @Min(1900) @Max(2026) Integer startYear,
        @Size(min = 2, max = 2000) String bio
) {}

