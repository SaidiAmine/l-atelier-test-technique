package com.org.latelier.services;

import com.org.latelier.com.org.latelier.models.entities.PlayerBuilder;
import com.org.latelier.models.Country;
import com.org.latelier.models.PlayerData;
import com.org.latelier.models.api.response.TennisStats;
import com.org.latelier.models.entities.TennisPlayerEntity;
import com.org.latelier.repository.TennisPlayerRepository;
import com.org.latelier.services.implementations.TennisPlayersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TennisPlayersServiceTest {

    @Mock
    private TennisPlayerRepository repository;

    @InjectMocks
    private TennisPlayersServiceImpl service;

    @Test
    void should_call_service_one_time_and_return_correct_value() {
        // Given
        TennisPlayerEntity player = PlayerBuilder.one().id(1L).build();
        when(repository.findById(1L)).thenReturn(Optional.of(player));

        // When
        TennisPlayerEntity result = service.findTennisPlayerById(1L);

        // Then
        verify(repository, times(1)).findById(1L);
        assertEquals(1L, result.getId());
        assertEquals("Rafael", result.getFirstName());
    }

    @Test
    void should_return_ordered_elements_of_TennisPlayerEntity() {
        // Given
        TennisPlayerEntity player1 = PlayerBuilder.one().id(1L)
                .firstName("Rafael")
                .data(new PlayerData(1, 2500, 80000, 183, 27, Arrays.asList(1, 1, 1, 1, 1)))
                .build();
        TennisPlayerEntity player2 = PlayerBuilder.one().id(2L)
                .firstName("Federer")
                .data(new PlayerData(2, 2500, 80000, 183, 27, Arrays.asList(1, 1, 1, 1, 1)))
                .build();
        when(repository.findAll()).thenReturn(Arrays.asList(player1, player2));

        // When
        List<TennisPlayerEntity> result = service.findAllTennisPlayersOrdered();

        // Then
        assertThat(result).containsExactly(player1, player2);
        verify(repository, times(1)).findAll();

    }

    @Test
    void should_return_correct_tennis_player_stats() {
        // Given
        TennisPlayerEntity player1 = PlayerBuilder.one().id(1L)
                .firstName("Ons")
                .data(new PlayerData(1, 2500, 80000, 183, 27, Arrays.asList(1, 1, 1, 1, 1)))
                .country(new Country("tunisie.png", "TUN"))
                .build();
        when(repository.findAll()).thenReturn(Collections.singletonList(player1));

        // When
        TennisStats result = service.assembleTennisStats();

        // Then
        verify(repository, times(1)).findAll();
        assertThat(result).extracting("heightMedian", "averageIMC", "countryRatio.countryCode", "countryRatio.countryScore")
                .containsExactly(183.0f, 23.888441f, "TUN", 5);
    }
}