package com.example.MatchService.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="PlayerIdentity")
public class PlayerIdentity {

    @Id
    private int matchId;

    private PlayingTeamIdentity playingTeams;

    public PlayingTeamIdentity getPlayingTeams() {
        return playingTeams;
    }

    public void setPlayingTeams(PlayingTeamIdentity playingTeams) {
        this.playingTeams = playingTeams;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
}
