package ua.markiyan.sonara.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlaylistRequest(
        @NotBlank @Size(min = 1, max = 255) String title,
        Boolean isPublic
) {}

