package ua.markiyan.sonara.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.markiyan.sonara.entity.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    boolean existsByTitleIgnoreCaseAndArtist_Id(String title, Long artistId);

    Optional<Album> findByTitleIgnoreCaseAndArtist_Id(String title, Long artistId);

    List<Album> findAllByArtist_Id(Long artistId);

    // Коли треба одразу з артистом (наприклад, щоб зчитати artistId без додаткового запиту)
    @EntityGraph(attributePaths = "artist")
    Optional<Album> findWithArtistById(Long id);
}
