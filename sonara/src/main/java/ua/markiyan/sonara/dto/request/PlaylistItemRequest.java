package ua.markiyan.sonara.dto.request;

import jakarta.validation.constraints.NotNull;

public record PlaylistItemRequest(
        @NotNull Long trackId,
        Integer position
) {}

