package com.example.MatchService.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "Innings")
public class Innings {
    @Id
    private int inningsId;
    private int inningsNo;
    private int runs;
    private int wickets;
    private List<Overs> overs;
    public int getInningsNo() {
        return inningsNo;
    }
    public void setInningsNo(int inningsNo) {
        this.inningsNo = inningsNo;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public List<Overs> getOvers() {
        return overs;
    }

    public void setOvers(List<Overs> overs) {
        this.overs = overs;
    }

    public int getInningsId() {
        return inningsId;
    }

    public void setInningsId(int inningsId) {
        this.inningsId = inningsId;
    }



    @Override
    public String toString() {
        return "Innings{" +
                "inningsId=" + inningsId +
                ", inningsNo=" + inningsNo +
                ", runs=" + runs +
                ", wickets=" + wickets +
                ", overs=" + overs +
                '}';
    }
}
