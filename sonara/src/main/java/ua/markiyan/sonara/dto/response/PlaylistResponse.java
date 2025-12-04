package ua.markiyan.sonara.dto.response;

public record PlaylistResponse(
        Long id,
        Long userId,
        String title,
        Boolean isPublic
) {}

