package com.example.MatchService.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Overs")
public class Overs {
    @Id
    private int oversId;
    private int oversNo;
    private List<BallInfo> balls;

    public int getOversNo() {
        return oversNo;
    }

    public List<BallInfo> getBalls() {
        return balls;
    }

    public void setBalls(List<BallInfo> balls) {
        this.balls = balls;
    }

    public void setOversNo(int oversNo) {
        this.oversNo = oversNo;
    }

    public int getOversId() {
        return oversId;
    }

    public void setOversId(int oversId) {
        this.oversId = oversId;
    }
}
