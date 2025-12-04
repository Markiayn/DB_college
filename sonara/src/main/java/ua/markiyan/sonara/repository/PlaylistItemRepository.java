package ua.markiyan.sonara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.markiyan.sonara.entity.PlaylistItem;

import java.util.List;

public interface PlaylistItemRepository extends JpaRepository<PlaylistItem, Long> {
    List<PlaylistItem> findAllByPlaylist_IdOrderByPositionAsc(Long playlistId);
}

