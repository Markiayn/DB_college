package ua.markiyan.sonara.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.markiyan.sonara.entity.Album;
import ua.markiyan.sonara.entity.Track;



public interface TrackRepository extends JpaRepository<Track, Long> {

    Optional<Track> findByTitleIgnoreCase(String title);
    boolean existsByTitleIgnoreCaseAndArtist_Id(String title, Long artistId);

    List<Track> findByAlbum_Id(Long albumId);
    Page<Track> findByAlbum_Id(Long albumId, Pageable pageable);
    Optional<Track> findByIdAndAlbum_Id(Long trackId, Long albumId);

    Page<Track> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Track> findByDurationSec(Integer durationSec, Pageable pageable);
    Page<Track> findByExplicitFlag(Boolean explicitFlag, Pageable pageable);

    Page<Track> findByTitleContainingIgnoreCaseAndDurationSec(String title, Integer durationSec, Pageable pageable);
    Page<Track> findByTitleContainingIgnoreCaseAndExplicitFlag(String title, Boolean explicitFlag, Pageable pageable);
    Page<Track> findByDurationSecAndExplicitFlag(Integer durationSec, Boolean explicitFlag, Pageable pageable);

    Page<Track> findByTitleContainingIgnoreCaseAndDurationSecAndExplicitFlag(String title, Integer durationSec, Boolean explicitFlag, Pageable pageable);

    // --- Альбом-скоп комбінації ---
    Page<Track> findByAlbum_IdAndTitleContainingIgnoreCase(Long albumId, String title, Pageable pageable);
    Page<Track> findByAlbum_IdAndDurationSec(Long albumId, Integer durationSec, Pageable pageable);
    Page<Track> findByAlbum_IdAndExplicitFlag(Long albumId, Boolean explicitFlag, Pageable pageable);
    Page<Track> findByAlbum_IdAndTitleContainingIgnoreCaseAndDurationSec(Long albumId, String title, Integer durationSec, Pageable pageable);
    Page<Track> findByAlbum_IdAndTitleContainingIgnoreCaseAndExplicitFlag(Long albumId, String title, Boolean explicitFlag, Pageable pageable);
    Page<Track> findByAlbum_IdAndDurationSecAndExplicitFlag(Long albumId, Integer durationSec, Boolean explicitFlag, Pageable pageable);
    Page<Track> findByAlbum_IdAndTitleContainingIgnoreCaseAndDurationSecAndExplicitFlag(Long albumId, String title, Integer durationSec, Boolean explicitFlag, Pageable pageable);
}


