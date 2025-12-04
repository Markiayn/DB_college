package ua.markiyan.sonara.service;

import ua.markiyan.sonara.dto.request.PlaylistItemRequest;
import ua.markiyan.sonara.dto.response.PlaylistItemResponse;

import java.util.List;

public interface PlaylistItemService {
    PlaylistItemResponse create(Long playlistId, PlaylistItemRequest req);
    List<PlaylistItemResponse> list(Long playlistId);
    PlaylistItemResponse get(Long playlistId, Long itemId);
    PlaylistItemResponse update(Long playlistId, Long itemId, PlaylistItemRequest req);
    void delete(Long playlistId, Long itemId);
    void reorder(Long playlistId, java.util.List<Long> itemIds);
}

