package ua.markiyan.sonara.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.markiyan.sonara.dto.request.PlaylistItemRequest;
import ua.markiyan.sonara.dto.response.PlaylistItemResponse;
import ua.markiyan.sonara.entity.Playlist;
import ua.markiyan.sonara.entity.PlaylistItem;
import ua.markiyan.sonara.entity.Track;
import ua.markiyan.sonara.exception.NotFoundException;
import ua.markiyan.sonara.repository.PlaylistItemRepository;
import ua.markiyan.sonara.repository.PlaylistRepository;
import ua.markiyan.sonara.repository.TrackRepository;
import ua.markiyan.sonara.service.PlaylistItemService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistItemServiceImpl implements PlaylistItemService {

    private final PlaylistItemRepository itemRepo;
    private final PlaylistRepository playlistRepo;
    private final TrackRepository trackRepo;

    @Override
    @Transactional
    public PlaylistItemResponse create(Long playlistId, PlaylistItemRequest req) {
        Playlist p = playlistRepo.findById(playlistId).orElseThrow(() -> new NotFoundException("Playlist %d not found".formatted(playlistId)));
        Track t = trackRepo.findById(req.trackId()).orElseThrow(() -> new NotFoundException("Track %d not found".formatted(req.trackId())));
        PlaylistItem pi = PlaylistItem.builder()
                .playlist(p)
                .track(t)
                .position(req.position() == null ? 0 : req.position())
                .build();
        pi = itemRepo.save(pi);
        return toResponse(pi);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlaylistItemResponse> list(Long playlistId) {
        if (!playlistRepo.existsById(playlistId)) throw new NotFoundException("Playlist %d not found".formatted(playlistId));
        return itemRepo.findAllByPlaylist_IdOrderByPositionAsc(playlistId).stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PlaylistItemResponse get(Long playlistId, Long itemId) {
        PlaylistItem it = itemRepo.findById(itemId).orElseThrow(() -> new NotFoundException("Item %d not found".formatted(itemId)));
        if (!it.getPlaylist().getId().equals(playlistId)) throw new NotFoundException("Item %d not found in playlist %d".formatted(itemId, playlistId));
        return toResponse(it);
    }

    @Override
    @Transactional
    public PlaylistItemResponse update(Long playlistId, Long itemId, PlaylistItemRequest req) {
        PlaylistItem it = itemRepo.findById(itemId).orElseThrow(() -> new NotFoundException("Item %d not found".formatted(itemId)));
        if (!it.getPlaylist().getId().equals(playlistId)) throw new NotFoundException("Item %d not found in playlist %d".formatted(itemId, playlistId));
        if (req.position() != null) it.setPosition(req.position());
        PlaylistItem saved = itemRepo.save(it);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long playlistId, Long itemId) {
        PlaylistItem it = itemRepo.findById(itemId).orElseThrow(() -> new NotFoundException("Item %d not found".formatted(itemId)));
        if (!it.getPlaylist().getId().equals(playlistId)) throw new NotFoundException("Item %d not found in playlist %d".formatted(itemId, playlistId));
        itemRepo.deleteById(itemId);
    }

    @Override
    @Transactional
    public void reorder(Long playlistId, java.util.List<Long> itemIds) {
        Playlist p = playlistRepo.findById(playlistId).orElseThrow(() -> new NotFoundException("Playlist %d not found".formatted(playlistId)));
        java.util.List<PlaylistItem> items = itemRepo.findAllByPlaylist_IdOrderByPositionAsc(playlistId);
        java.util.Map<Long, PlaylistItem> map = items.stream().collect(java.util.stream.Collectors.toMap(PlaylistItem::getId, x -> x));
        for (int i = 0; i < itemIds.size(); i++) {
            Long id = itemIds.get(i);
            PlaylistItem it = map.get(id);
            if (it != null) it.setPosition(i+1);
        }
        itemRepo.saveAll(map.values());
    }

    private PlaylistItemResponse toResponse(PlaylistItem pi) {
        return new PlaylistItemResponse(pi.getId(), pi.getPlaylist().getId(), pi.getTrack().getId(), pi.getPosition());
    }
}

