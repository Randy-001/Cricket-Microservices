package com.example.MatchService.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PlayingTeamIdentity")
public class PlayingTeamIdentity {
    @Id
     private int playingTeamId;
     private TeamPlayer teamPlayer;

    public int getPlayingTeamId() {
        return playingTeamId;
    }

    public void setPlayingTeamId(int playingTeamId) {
        this.playingTeamId = playingTeamId;
    }

    public TeamPlayer getTeamPlayer() {
        return teamPlayer;
    }

    public void setTeamPlayer(TeamPlayer teamPlayer) {
        this.teamPlayer = teamPlayer;
    }
}
