package com.example.MatchService.service;

import com.example.MatchService.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class InningsService {
    public HashMap<String,Object> play(PlayingTeam teamA, PlayingTeam teamB, Innings innings, Match m, boolean batFirst, int target) {
        List<Overs> oversInfo = innings.getOvers();
//        List<TeamPlayer> tp = teamA.getTeamPlayer().stream().map(x-> {
//            x.getPlayer().setPlayerName(x.getPlayer().getPlayerName());
//            return x;
//        }).collect(Collectors.toList());
        int uptoRuns=0;
        int g=0,h=0;
        for(int i = 0; i< m.getOvers()&&innings.getWickets()<10; i++)
        {
            System.out.println("Innings One and overs. .................");
            oversInfo.add(new Overs());
            ArrayList<BallInfo> ballInfo = new ArrayList<>();
            for(int j=0;j<6&&innings.getWickets()<10;j++)
            {
                ballInfo.add(new BallInfo());
                ballInfo.get(j).setBatsman(teamA.getTeamPlayer().get(h));
                ballInfo.get(j).setBowler(teamB.getTeamPlayer().get(5+(i%6)));
                ballInfo.get(j).setBallNo(j+1);
                int r = (int) (Math.random()*8);
                if(r==7){
                    ballInfo.get(j).setWicket(1);
                    teamA.getTeamPlayer().get(h).setRuns(uptoRuns);
                    int w = teamB.getTeamPlayer().get(5+(i%6)).getWickets();
                    w++;
                    teamB.getTeamPlayer().get(5+(i%6)).setWickets(w);
                    h++;
                    uptoRuns=0;
                }
                else{
                    g+=r;
                    uptoRuns+=r;
                    ballInfo.get(j).setRun(r);

                }


                if(!batFirst && g>target) break;
            }
            oversInfo.get(i).setBalls(ballInfo);
            oversInfo.get(i).setOversNo(i+1);
            if(!batFirst && g>target) break;
        }
        teamA.getTeamPlayer().get(h).setRuns(uptoRuns);
        innings.setRuns(g);
        innings.setWickets(h);
        innings.setOvers(oversInfo);
        HashMap<String,Object> map = new HashMap<>();
        map.put("innings",innings);
        map.put("teamA",teamA);
        map.put("teamB",teamB);
        return map;
    }
}
