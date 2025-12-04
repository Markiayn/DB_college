package ua.markiyan.sonara.service;

import ua.markiyan.sonara.dto.request.PlaylistRequest;
import ua.markiyan.sonara.dto.response.PlaylistResponse;

import java.util.List;

public interface PlaylistService {
    PlaylistResponse create(Long userId, PlaylistRequest req);
    List<PlaylistResponse> listByUser(Long userId);
    PlaylistResponse get(Long id);
    void delete(Long id);
}

