package ua.markiyan.sonara.mapper;

import ua.markiyan.sonara.dto.request.TrackRequest;
import ua.markiyan.sonara.dto.response.TrackResponse;
import ua.markiyan.sonara.entity.Track;
import ua.markiyan.sonara.entity.Album;
import ua.markiyan.sonara.entity.Artist;
import ua.markiyan.sonara.dto.request.ArtistAlbumTrackRequest;



public final class TrackMapper {
    private TrackMapper() {}

    public static Track toEntity(TrackRequest dto, Album album, Artist artist) {
        return Track.builder()
                .title(dto.title())
                .durationSec(dto.durationSec())
                .audioKey(dto.audioKey())
                .explicitFlag(dto.explicitFlag())
                .album(album)
                .artist(artist)
                .build();
    }


    public static Track toEntity(ArtistAlbumTrackRequest dto, Album album, Artist artist) {
        return Track.builder()
                .title(dto.title())
                .durationSec(dto.durationSec())
                .audioKey(dto.audioKey())
                .explicitFlag(dto.explicitFlag())
                .album(album)
                .artist(artist)
                .build();
    }


    public static TrackResponse toResponse(Track t) {
        return new TrackResponse(
                t.getId(),
                t.getTitle(),
                t.getDurationSec(),
                t.getAudioKey(),
                t.isExplicitFlag(),  // boolean getter з Lombok — isExplicitFlag()
                t.getAudioUrl()
        );
    }
}
