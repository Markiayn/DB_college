package ua.markiyan.sonara.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import ua.markiyan.sonara.dto.request.UserRequest;
import ua.markiyan.sonara.dto.response.UserResponse;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class UserControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    String baseUrl() { return "http://localhost:" + port + "/api/users"; }

    @Test
    void create_returns201_andBody() {
        UserRequest req = new UserRequest(
                ("user"+System.nanoTime()+"@mail.com"),
                "secret12",
                "John Doe "+System.nanoTime(),
                "UA"
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));

        ResponseEntity<UserResponse> resp = rest.exchange(
                baseUrl(),
                HttpMethod.POST,
                new HttpEntity<>(req, headers),
                UserResponse.class
        );

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().id()).isNotNull();
        assertThat(resp.getBody().email()).isEqualTo(req.email());
        assertThat(resp.getBody().name()).startsWith("John Doe");
        assertThat(resp.getBody().country()).isEqualTo("UA");
        assertThat(resp.getBody().status()).isNotBlank();
        assertThat(resp.getBody().createdAt()).isNotBlank();
    }

    @Test
    void create_whenInvalidRequest_returns400() {
        UserRequest bad = new UserRequest(
                "bad",
                "123",
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

