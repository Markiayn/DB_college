package ua.markiyan.sonara.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.markiyan.sonara.entity.Artist;
import org.springframework.data.jpa.repository.EntityGraph;




public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);

    @EntityGraph(attributePaths = "albums")
    Optional<Artist> findWithAlbumsById(Long id);
}
