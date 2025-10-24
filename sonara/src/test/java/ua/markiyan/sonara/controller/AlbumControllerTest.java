package ua.markiyan.sonara.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.markiyan.sonara.SonaraSpotifyCloneApplication;

@SpringBootTest(classes = SonaraSpotifyCloneApplication.class)
@AutoConfigureMockMvc
public class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAlbumControllerEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/albums"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}