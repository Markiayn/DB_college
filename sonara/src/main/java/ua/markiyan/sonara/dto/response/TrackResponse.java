package ua.markiyan.sonara.dto.response;

public record TrackResponse (
        Long id,
        String title,
        Integer durationSec,
        String audioKey,
        boolean explicitFlag,
        String audioUrl
        // TODO: Album & Artist
){}
