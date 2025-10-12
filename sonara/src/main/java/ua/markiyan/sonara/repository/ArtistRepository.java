package ua.markiyan.sonara.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.markiyan.sonara.entity.Artist;


public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
