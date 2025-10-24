package ua.markiyan.sonara.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import ua.markiyan.sonara.dto.request.TrackRequest;
import ua.markiyan.sonara.dto.response.TrackResponse;
import ua.markiyan.sonara.entity.Album;
import ua.markiyan.sonara.entity.Artist;
import ua.markiyan.sonara.repository.AlbumRepository;
import ua.markiyan.sonara.repository.ArtistRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TrackControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Autowired
    ArtistRepository artistRepo;
    @Autowired
    AlbumRepository albumRepo;

    private Long artistId;
    private Long albumId;

    String baseUrl() { return "http://localhost:" + port + "/api/tracks"; }

    @BeforeEach
    void setUp() {
        Artist artist = artistRepo.findByNameIgnoreCase("ArtistForTrack").orElseGet(() ->
                artistRepo.save(Artist.builder()
                        .name("ArtistForTrack")
                        .country("UA")
                        .startYear(2015)
                        .bio("bio")
                        .build())
        );
        this.artistId = artist.getId();

        Album album = albumRepo.findByTitleIgnoreCaseAndArtist_Id("AlbumForTrack", artist.getId())
                .orElseGet(() -> albumRepo.save(Album.builder()
                        .title("AlbumForTrack")
                        .releaseDate(LocalDate.parse("2020-01-01"))
                        .coverUrl(null)
                        .artist(artist)
                        .build())
                );
        this.albumId = album.getId();
    }

    @Test
    void create_returns201_andBody() {
        TrackRequest req = new TrackRequest(
                "Song "+System.nanoTime(),
                180,
                "audio-key-"+System.nanoTime(),
                true,
                albumId,
                artistId
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));

        ResponseEntity<TrackResponse> resp = rest.exchange(
                baseUrl(),
                HttpMethod.POST,
                new HttpEntity<>(req, headers),
                TrackResponse.class
        );

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().id()).isNotNull();
        assertThat(resp.getBody().title()).startsWith("Song ");
        assertThat(resp.getBody().durationSec()).isEqualTo(180);
        assertThat(resp.getBody().audioKey()).startsWith("audio-key-");
        assertThat(resp.getBody().explicitFlag()).isTrue();
        assertThat(resp.getBody().audioUrl()).startsWith("https://cloudflare.com/");
    }

    @Test
    void create_whenInvalidRequest_returns400() {
        TrackRequest bad = new TrackRequest(
                " ",
                0,
                " ",
                null,
                null,
                null
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));

        ResponseEntity<String> resp = rest.exchange(
                baseUrl(),
                HttpMethod.POST,
                new HttpEntity<>(bad, headers),
                String.class
        );

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(resp.getBody()).isNotBlank();
    }
}

