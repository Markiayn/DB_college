package ua.markiyan.sonara.controller;


import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import org.springframework.test.context.ActiveProfiles;
import ua.markiyan.sonara.dto.request.AlbumRequest;
import ua.markiyan.sonara.dto.response.AlbumResponse;
import ua.markiyan.sonara.entity.Artist;
import ua.markiyan.sonara.repository.ArtistRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // use embedded/test DB
class AlbumControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Autowired
    ArtistRepository artistRepo;

    String baseUrl;
    private Long existingArtistId;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api/albums";
        Artist artist = artistRepo.findByNameIgnoreCase("Test Artist").orElseGet(() ->
                artistRepo.save(Artist.builder()
                        .name("Test Artist")
                        .country("UA")
                        .startYear(2020)       // use a past year to avoid validation issues
                        .bio("Test bio")
                        .build())
        );
        this.existingArtistId = artist.getId();
    }

    @Test
    void create_returns201_andBody() {

        AlbumRequest req = new AlbumRequest(
                "Test album",
                LocalDate.parse("2024-01-01"),
                "https://example.com/covers/test.jpg",
                existingArtistId
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));

        ResponseEntity<AlbumResponse> resp = rest.exchange(
                baseUrl,
                HttpMethod.POST,
                new HttpEntity<>(req, headers),
                AlbumResponse.class
        );

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().id()).isNotNull();
        assertThat(resp.getBody().title()).isEqualTo("Test album");
        assertThat(resp.getBody().releaseDate()).isEqualTo(LocalDate.parse("2024-01-01"));
        assertThat(resp.getBody().coverUrl()).isEqualTo("https://example.com/covers/test.jpg");
        assertThat(resp.getBody().artistId()).isEqualTo(existingArtistId);
    }

    @Test
    void create_whenInvalidRequest_returns400() {
        // Невалідно: title = " " (NotBlank), releaseDate у майбутньому (PastOrPresent), artistId = null (NotNull)
        AlbumRequest bad = new AlbumRequest(
                " ",
                LocalDate.now().plusDays(1),
                null,
                null
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON)); // be explicit about accept

        ResponseEntity<String> resp = rest.exchange(
                baseUrl,
                HttpMethod.POST,
                new HttpEntity<>(bad, headers),
                String.class
        );

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(resp.getBody()).isNotBlank(); // optional: ensure validation payload returned
    }
}
