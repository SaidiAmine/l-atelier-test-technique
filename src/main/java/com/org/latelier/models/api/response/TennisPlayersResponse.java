package com.org.latelier.models.api.response;

import com.org.latelier.models.TennisPlayer;

import java.util.List;

public class TennisPlayersResponse {
    List<TennisPlayer> players;

    public List<TennisPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<TennisPlayer> players) {
        this.players = players;
    }
}
