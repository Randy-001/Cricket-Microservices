package com.example.MatchService.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "Match")
//@Table(schema = "postgres")
public class Match {
    @Transient
    public final static String MATCH_SEQUENCE="lastMatchId";
    @Id
    private int matchId;
    @DBRef
    private List<Team> teams;
    private Scoreboard scoreBoard;
    private List<Innings> innings;
    private List<PlayingTeam> playingTeams;
    private int overs;
    private int batting;


    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }


    public int getBatting() {
        return batting;
    }

    public void setBatting(int batting) {
        this.batting = batting;
    }

    public List<Innings> getInnings() {
        return innings;
    }

    public void setInnings(List<Innings> innings) {
        this.innings = innings;
    }

//@JsonIgnore
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<PlayingTeam> getPlayingTeams() {
        return playingTeams;
    }

    public void setPlayingTeams(List<PlayingTeam> playingTeams) {
        this.playingTeams = playingTeams;
    }

    public Scoreboard getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(Scoreboard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return matchId == match.matchId && overs == match.overs && batting == match.batting && Objects.equals(teams, match.teams) && Objects.equals(scoreBoard, match.scoreBoard) && Objects.equals(innings, match.innings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, teams, scoreBoard, innings, overs, batting);
    }
}
