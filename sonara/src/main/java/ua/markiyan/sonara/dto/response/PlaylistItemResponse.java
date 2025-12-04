package ua.markiyan.sonara.dto.response;

public record PlaylistItemResponse(
        Long id,
        Long playlistId,
        Long trackId,
        Integer position
) {}

