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
import ua.markiyan.sonara.dto.request.ArtistAlbumRequest;
import ua.markiyan.sonara.dto.response.AlbumResponse;
import ua.markiyan.sonara.entity.Artist;
import ua.markiyan.sonara.repository.ArtistRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ArtistAlbumControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Autowired
    ArtistRepository artistRepo;

    Long artistId;

    String baseUrl() { return "http://localhost:" + port + "/api/artists/"+artistId+"/albums"; }

    @BeforeEach
    void setUp() {
        Artist artist = artistRepo.findByNameIgnoreCase("ArtistForAlbumNest").orElseGet(() ->
                artistRepo.save(Artist.builder()
                        .name("ArtistForAlbumNest")
                        .country("UA")
                        .startYear(2018)
                        .bio("bio")
                        .build())
        );
        this.artistId = artist.getId();
    }

    @Test
    void create_returns201_andBody() {
        ArtistAlbumRequest req = new ArtistAlbumRequest(
                "Nested Album "+System.nanoTime(),
                java.time.LocalDate.parse("2023-01-02"),
                null
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));

        ResponseEntity<AlbumResponse> resp = rest.exchange(
                baseUrl(),
                HttpMethod.POST,
                new HttpEntity<>(req, headers),
                AlbumResponse.class
        );

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().id()).isNotNull();
        assertThat(resp.getBody().title()).startsWith("Nested Album ");
        assertThat(resp.getBody().artistId()).isEqualTo(artistId);
    }

    @Test
    void create_whenInvalidRequest_returns400() {
        ArtistAlbumRequest bad = new ArtistAlbumRequest(
                " ",
                java.time.LocalDate.now().plusDays(1),
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

