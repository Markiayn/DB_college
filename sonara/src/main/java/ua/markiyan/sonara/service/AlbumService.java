package ua.markiyan.sonara.service;

import ua.markiyan.sonara.dto.request.AlbumRequest;
import ua.markiyan.sonara.dto.response.AlbumResponse;

import java.util.List;

public interface AlbumService {
    AlbumResponse create(AlbumRequest request);
    AlbumResponse get(Long id);
    List<AlbumResponse> listByArtist(Long artistId);
}
