package ua.markiyan.sonara.dto.request;

import jakarta.validation.constraints.Size;

public record TrackUpdateRequest(
        @Size(min = 1, max = 500) String title,
        Integer durationSec,
        Boolean explicitFlag,
        String audioKey,
        String audioUrl
) {}

