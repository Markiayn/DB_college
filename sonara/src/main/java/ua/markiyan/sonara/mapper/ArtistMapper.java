package ua.markiyan.sonara.mapper;

import ua.markiyan.sonara.dto.response.ArtistResponse;
import ua.markiyan.sonara.dto.request.ArtistRequest;
import ua.markiyan.sonara.entity.Artist;

import java.util.List;

public final class ArtistMapper {
    private ArtistMapper() {}

    public static Artist toEntity(ArtistRequest dto) {
        return Artist.builder()
                .name(dto.name())
                .country(dto.country())
                .startYear(dto.startYear())
                .bio(dto.bio())
                .build();
    }

    public static ArtistResponse toResponse(Artist e) {
        List<ua.markiyan.sonara.dto.response.AlbumResponse> albums =
                e.getAlbums() == null ? List.of()
                        : e.getAlbums().stream()
                        .map(AlbumMapper::toResponse)
                        .toList();

        return new ArtistResponse(
                e.getId(),
                e.getName(),
                e.getCountry(),
                e.getStartYear(),
                e.getBio(),
                albums
        );
    }
}
