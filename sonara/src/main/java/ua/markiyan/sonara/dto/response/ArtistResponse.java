package ua.markiyan.sonara.dto.response;

import java.util.List;

public record ArtistResponse (
        Long id,
        String name,
        String country,
        Integer startYear,
        String bio
){}
