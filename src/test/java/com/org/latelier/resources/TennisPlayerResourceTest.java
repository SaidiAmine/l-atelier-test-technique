package com.org.latelier.resources;

import com.org.latelier.com.org.latelier.models.entities.PlayerBuilder;
import com.org.latelier.models.Country;
import com.org.latelier.models.PlayerData;
import com.org.latelier.models.entities.TennisPlayerEntity;
import com.org.latelier.repository.TennisPlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class TennisPlayerResourceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TennisPlayerRepository repository;

    @Test
    void should_return_200_when_calling_api_with_correct_output() throws Exception {
        // Given
        TennisPlayerEntity player = PlayerBuilder
                .one().id(5).build();

        // When
        when(repository.findAll()).thenReturn(Arrays.asList(player));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/players/ordered")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Rafael"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(5));
    }

    @Test
    void should_return_200_when_calling_api_with_correct_response_entity() throws Exception {
        // Given
        TennisPlayerEntity player = PlayerBuilder
                .one().id(5).build();

        // When
        when(repository.findById(anyLong())).thenReturn(Optional.of(player));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/player/5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Rafael"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5));
    }

    @Test
    void should_return_404_with_error_message_when_entity_missing() throws Exception {
        // Given
        // When
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/player/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Couldn't find entity with id 99"));
    }

    @Test
    void should_return_200_and_correct_stats() throws Exception {
        // Given
        TennisPlayerEntity player1 = PlayerBuilder.one()
                .country(new Country("tunisie.png", "TUN"))
                .firstName("Ons")
                .lastName("Jabeur")
                .sex("F")
                .data(new PlayerData(1, 2500, 80000, 183, 27, Arrays.asList(1,1,1,1,1)))
                .build();
        TennisPlayerEntity player2 = PlayerBuilder.one()
                .country(new Country("usa.png", "usa"))
                .firstName("Bernarda")
                .lastName("Pera")
                .sex("F")
                .data(new PlayerData(2, 2000, 80000, 183, 27, Arrays.asList(1,0,0,1,1)))
                .build();

        // When
        when(repository.findAll()).thenReturn(Arrays.asList(player1, player2));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/stats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.countryRatio.countryCode").value("TUN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.countryRatio.countryScore").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.heightMedian").value(183.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.averageIMC").value(23.888441));

    }

}