package io.github.raniagus.featureoverride.controller;

import io.github.raniagus.featureoverride.configuration.FeatureConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {FeatureConfiguration.class, FeatureController.class})
class QueryControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testQueryObjectsBy() throws Exception {
        Map<String, String> requestBody = Map.of(
                "platform", "android",
                "version", "15.0.0",
                "os_version", "12"
        );

        mockMvc.perform(post("/features")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.faq").value(true))
                .andExpect(jsonPath("$.gallery").value(false))
                .andExpect(jsonPath("$.swap_camera").value(true))
                .andExpect(jsonPath("$.take_photo").value(true))
                .andExpect(jsonPath("$.record_video").value(false))
                .andExpect(jsonPath("$.send_audio").value(true));
    }
}