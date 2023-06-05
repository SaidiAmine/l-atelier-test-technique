package com.org.latelier.services;

import com.org.latelier.models.api.response.TennisStats;
import com.org.latelier.models.entities.TennisPlayerEntity;

import java.util.List;

public interface TennisPlayersService {
    void saveTennisPlayers();

    List<TennisPlayerEntity> findAllTennisPlayersOrdered();

    TennisPlayerEntity findTennisPlayerById(Long id);

    TennisStats assembleTennisStats();

}
