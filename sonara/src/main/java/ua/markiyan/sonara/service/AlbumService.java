package ua.markiyan.sonara.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.markiyan.sonara.dto.request.AlbumRequest;
import ua.markiyan.sonara.dto.request.ArtistAlbumRequest;
import ua.markiyan.sonara.dto.request.AlbumUpdateRequest;
import ua.markiyan.sonara.dto.response.AlbumResponse;
import java.time.LocalDate;
import java.util.List;

public interface AlbumService {
    AlbumResponse create(AlbumRequest request);
    AlbumResponse get(Long id);
    List<AlbumResponse> listByArtist(Long artistId);
    AlbumResponse getUnderArtist(Long artistId, Long albumId);

    AlbumResponse createUnderArtist(Long artistId, ArtistAlbumRequest req);

    Page<AlbumResponse> search(String title, LocalDate releaseDate, Pageable pageable);

    // updates
    AlbumResponse update(Long id, AlbumUpdateRequest req);
    void delete(Long id);
}
