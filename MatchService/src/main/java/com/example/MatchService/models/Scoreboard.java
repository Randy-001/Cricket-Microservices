package com.example.MatchService.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Scoreboard {
    @Id
    private int scoreboardId;
    private PlayingTeam winner;
    private int target;
    private boolean isDraw;

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }

    public int getScoreboardId() {
        return scoreboardId;
    }

    public void setScoreboardId(int scoreboardId) {
        this.scoreboardId = scoreboardId;
    }

    public PlayingTeam getWinner() {
        return winner;
    }

    public void setWinner(PlayingTeam winner) {
        this.winner = winner;
    }
}
