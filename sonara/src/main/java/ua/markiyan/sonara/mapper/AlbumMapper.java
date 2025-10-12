package ua.markiyan.sonara.mapper;

import ua.markiyan.sonara.dto.request.AlbumRequest;
import ua.markiyan.sonara.dto.response.AlbumResponse;
import ua.markiyan.sonara.entity.Album;
import ua.markiyan.sonara.entity.Artist;

public final class AlbumMapper {
    private AlbumMapper() {}

    // Створення entity з DTO + готового Artist
    public static Album toEntity(AlbumRequest dto, Artist artist) {
        return Album.builder()
                .title(dto.title())
                .releaseDate(dto.releaseDate())
                .coverUrl(dto.coverUrl())
                .artist(artist)
                .build();
    }

    // Маппінг entity -> DTO
    public static AlbumResponse toResponse(Album e) {
        return new AlbumResponse(
                e.getId(),
                e.getTitle(),
                e.getReleaseDate(),
                e.getCoverUrl(),
                e.getArtist() != null ? e.getArtist().getId() : null
        );
    }
}
