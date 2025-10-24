package ua.markiyan.sonara.repository;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import ua.markiyan.sonara.entity.Album;
import ua.markiyan.sonara.entity.Artist;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
class AlbumRepositoryTest {

    @Autowired private AlbumRepository albumRepository;
    @Autowired private TestEntityManager em;

    private Artist artistA;
    private Artist artistB;
    private Album a1;
    private Album a2;
    private Album b1;

    @BeforeEach
    void setUp() {
        artistA = persistArtist("Muse");
        artistB = persistArtist("Radiohead");

        a1 = persistAlbum(artistA, "Absolution", LocalDate.of(2003, 9, 15));
        a2 = persistAlbum(artistA, "Origin of Symmetry", LocalDate.of(2001, 6, 18));
        b1 = persistAlbum(artistB, "OK Computer", LocalDate.of(1997, 5, 21));
        em.flush(); // щоб згенерувались ID
    }

    private Artist persistArtist(String name) {
        Artist ar = new Artist();
        ar.setName(name);
        return em.persist(ar);
    }

    private Album persistAlbum(Artist artist, String title, LocalDate date) {
        Album al = new Album();
        al.setArtist(artist);
        al.setTitle(title);
        al.setReleaseDate(date);
        return em.persist(al);
    }

    @Test
    void existsByTitleIgnoreCaseAndArtist_Id() {
        assertTrue(albumRepository.existsByTitleIgnoreCaseAndArtist_Id("absolution", artistA.getId()));
        assertFalse(albumRepository.existsByTitleIgnoreCaseAndArtist_Id("absolution", artistB.getId()));
        assertFalse(albumRepository.existsByTitleIgnoreCaseAndArtist_Id("non-existent", artistA.getId()));
    }

    @Test
    void findByTitleIgnoreCaseAndArtist_Id() {
        var found = albumRepository.findByTitleIgnoreCaseAndArtist_Id("origin of symmetry", artistA.getId());
        assertTrue(found.isPresent());
        assertEquals(a2.getId(), found.get().getId());

        assertTrue(albumRepository.findByTitleIgnoreCaseAndArtist_Id("OK COMPUTER", artistA.getId()).isEmpty());
    }

    @Test
    void findAllByArtist_Id() {
        List<Album> listA = albumRepository.findAllByArtist_Id(artistA.getId());
        assertEquals(2, listA.size());
        assertTrue(listA.stream().anyMatch(al -> al.getTitle().equals("Absolution")));
        assertTrue(listA.stream().anyMatch(al -> al.getTitle().equals("Origin of Symmetry")));

        List<Album> listB = albumRepository.findAllByArtist_Id(artistB.getId());
        assertEquals(1, listB.size());
        assertEquals("OK Computer", listB.get(0).getTitle());
    }

    @Test
    void findWithArtistById() {
        var loaded = albumRepository.findWithArtistById(a1.getId()).orElseThrow();
        assertNotNull(loaded.getArtist());
        assertEquals(artistA.getId(), loaded.getArtist().getId());
        // Перевіримо, що граф підвантажив artist:
        assertTrue(Hibernate.isInitialized(loaded.getArtist()));
    }

    @Test
    void findByIdAndArtist_Id() {
        assertTrue(albumRepository.findByIdAndArtist_Id(a1.getId(), artistA.getId()).isPresent());
        assertTrue(albumRepository.findByIdAndArtist_Id(a1.getId(), artistB.getId()).isEmpty());
    }

    @Test
    void findByTitleContainingIgnoreCase() {
        Page<Album> page = albumRepository.findByTitleContainingIgnoreCase("sym", PageRequest.of(0, 10));
        assertEquals(1, page.getTotalElements());
        assertEquals(a2.getId(), page.getContent().get(0).getId());
    }

    @Test
    void findByReleaseDate() {
        Page<Album> page = albumRepository.findByReleaseDate(LocalDate.of(1997, 5, 21), PageRequest.of(0, 10));
        assertEquals(1, page.getTotalElements());
        assertEquals(b1.getId(), page.getContent().get(0).getId());
    }

    @Test
    void findByTitleContainingIgnoreCaseAndReleaseDate() {
        Page<Album> page = albumRepository.findByTitleContainingIgnoreCaseAndReleaseDate(
                "computer", LocalDate.of(1997, 5, 21), PageRequest.of(0, 10));
        assertEquals(1, page.getTotalElements());
        assertEquals(b1.getId(), page.getContent().get(0).getId());

        // негативний кейс
        assertEquals(0, albumRepository.findByTitleContainingIgnoreCaseAndReleaseDate(
                "computer", LocalDate.of(2001, 6, 18), PageRequest.of(0, 10)).getTotalElements());
    }
}
