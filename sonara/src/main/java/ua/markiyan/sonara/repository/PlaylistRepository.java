package ua.markiyan.sonara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.markiyan.sonara.entity.Playlist;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findAllByUser_Id(Long userId);
}

