package com.example.MatchService.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Document(collection = "PlayingTeam")
public class PlayingTeam {
    @Transient
    public final static String PLAYING_TEAM_SEQUENCE="lastPlayedPlayingTeamId";
    @Id
    private int playingTeamId;
    private List<TeamPlayer> teamPlayer;
    public int getPlayingTeamId() {
        return playingTeamId;
    }

    public void setPlayingTeamId(int playingTeamId) {
        this.playingTeamId = playingTeamId;
    }

    public List<TeamPlayer> getTeamPlayer() {
        return teamPlayer;
    }

    public void setTeamPlayer(List<TeamPlayer> teamPlayer) {

        this.teamPlayer = teamPlayer;
    }

}
