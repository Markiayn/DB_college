package ua.markiyan.sonara.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.markiyan.sonara.dto.request.AlbumTrackRequest;
import ua.markiyan.sonara.dto.request.TrackRequest;
import ua.markiyan.sonara.dto.response.TrackResponse;
import ua.markiyan.sonara.dto.request.ArtistAlbumTrackRequest;
import ua.markiyan.sonara.dto.request.TrackUpdateRequest;

import java.util.List;

public interface TrackService {
    TrackResponse create(TrackRequest request);      // плоский варіант (із artistId/albumId у body)
    TrackResponse create(Long albumId, AlbumTrackRequest request);
    TrackResponse get(Long id);

    // ДЛЯ /api/artists/{artistId}/albums/{albumId}/tracks...
    TrackResponse createUnderAlbum(Long artistId, Long albumId, ArtistAlbumTrackRequest req);
    List<TrackResponse> listByAlbumWithArtsit(Long artistId, Long albumId);
    java.util.List<TrackResponse> listByAlbum(Long albumId);
    TrackResponse getOne(Long artistId, Long albumId, Long trackId);

    Page<TrackResponse> search(String title, Integer durationSec, Boolean explicitFlag, Pageable pageable);
    Page<TrackResponse> searchInAlbum(Long albumId, String title, Integer durationSec, Boolean explicitFlag, Pageable pageable);

    // updates
    TrackResponse update(Long id, TrackUpdateRequest req);
    void delete(Long id);
}
