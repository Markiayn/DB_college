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
import ua.markiyan.sonara.dto.request.ArtistAlbumTrackRequest;
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
class ArtistAlbumTracksControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Autowired
    ArtistRepository artistRepo;
    @Autowired
    AlbumRepository albumRepo;

    Long artistId;
    Long albumId;

    String baseUrl() { return "http://localhost:" + port + "/api/artists/"+artistId+"/albums/"+albumId+"/tracks"; }

    @BeforeEach
    void setUp() {
        Artist artist = artistRepo.findByNameIgnoreCase("ArtistAT").orElseGet(() ->
                artistRepo.save(Artist.builder()
                        .name("ArtistAT")
                        .country("UA")
                        .startYear(2016)
                        .bio("bio")
                        .build())
        );
        this.artistId = artist.getId();

        Album album = albumRepo.findByTitleIgnoreCaseAndArtist_Id("AlbumAT", artist.getId())
                .orElseGet(() -> albumRepo.save(Album.builder()
                        .title("AlbumAT")
                        .releaseDate(LocalDate.parse("2020-05-01"))
                        .artist(artist)
                        .build())
                );
        this.albumId = album.getId();
    }

    @Test
    void create_returns201_andBody() {
        ArtistAlbumTrackRequest req = new ArtistAlbumTrackRequest(
                "AAT Song "+System.nanoTime(),
                210,
                "aat-audio-"+System.nanoTime(),
                true
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
        assertThat(resp.getBody().title()).startsWith("AAT Song ");
        assertThat(resp.getBody().audioUrl()).startsWith("https://cloudflare.com/");
    }

    @Test
    void create_whenInvalidRequest_returns400() {
        ArtistAlbumTrackRequest bad = new ArtistAlbumTrackRequest(
                " ",
                0,
                " ",
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

