package com.org.latelier.models;

import java.util.List;

public class PlayersResponseEntity {
    List<TennisPlayer> players;

    public List<TennisPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<TennisPlayer> players) {
        this.players = players;
    }
}
