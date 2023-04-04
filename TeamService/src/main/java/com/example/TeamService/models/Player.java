package com.example.TeamService.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Player")
public class Player {

    @Transient
    public final static String PLAYER_SEQUENCE="lastPlayerId";
    @Id
    private int playerId;

    private String playerName;

    private Boolean isBatsman;

    private Boolean isBowler;


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Boolean getIsBatsman() {
        return isBatsman;
    }

    public void setIsBatsman(Boolean batsman) {
        isBatsman = batsman;
    }

    public Boolean getIsBowler() {
        return isBowler;
    }

    public void setIsBowler(Boolean bowler) {
        isBowler = bowler;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
