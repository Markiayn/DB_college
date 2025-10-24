package ua.markiyan.sonara.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import ua.markiyan.sonara.dto.request.ArtistRequest;
import ua.markiyan.sonara.dto.response.ArtistResponse;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ArtistControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    String baseUrl() { return "http://localhost:" + port + "/api/artists"; }

    @Test
    void create_returns201_andBody() {
        ArtistRequest req = new ArtistRequest(
                "Artist "+System.nanoTime(),
                "UA",
                2010,
                "Some bio"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));

        ResponseEntity<ArtistResponse> resp = rest.exchange(
                baseUrl(),
                HttpMethod.POST,
                new HttpEntity<>(req, headers),
                ArtistResponse.class
        );

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().id()).isNotNull();
        assertThat(resp.getBody().name()).isEqualTo(req.name());
        assertThat(resp.getBody().country()).isEqualTo("UA");
        assertThat(resp.getBody().startYear()).isEqualTo(2010);
        assertThat(resp.getBody().bio()).isEqualTo("Some bio");
    }

    @Test
    void create_whenInvalidRequest_returns400() {
        ArtistRequest bad = new ArtistRequest(
                " ", // NotBlank
                null,
                1800, // < 1900
                "b"
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

