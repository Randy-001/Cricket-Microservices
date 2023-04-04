package com.example.MatchService.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Document(collection = "BallInfo")
public class BallInfo {
    @Id
    private int ballId;
    private int ballNo;
    private int run;
    private int wicket;
    private TeamPlayer batsman;
    private TeamPlayer bowler;

    public int getBallNo() {
        return ballNo;
    }

    public void setBallNo(int ballNo) {
        this.ballNo = ballNo;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public int getWicket() {
        return wicket;
    }

    public void setWicket(int wicket) {
        this.wicket = wicket;
    }


    public TeamPlayer getBatsman() {
        return batsman;
    }

    public void setBatsman(TeamPlayer batsman) {
        this.batsman = batsman;
    }

    public TeamPlayer getBowler() {
        return bowler;
    }

    public void setBowler(TeamPlayer bowler) {
        this.bowler = bowler;
    }

    public int getBallId() {
        return ballId;
    }

    public void setBallId(int ballId) {
        this.ballId = ballId;
    }
}
