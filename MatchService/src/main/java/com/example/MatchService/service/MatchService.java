package com.example.MatchService.service;

import com.example.MatchService.client.TeamClient;
import com.example.MatchService.models.*;
import com.example.MatchService.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final InningsService inningsservice;
    private  final SequenceService sequenceService;

    private final TeamClient teamClient;

    @Autowired
    public MatchService(MatchRepository matchRepository,InningsService inningsservice,SequenceService sequenceService,TeamClient teamClient) {
        this.matchRepository = matchRepository;
        this.inningsservice = inningsservice;
        this.sequenceService=sequenceService;
        this.teamClient=teamClient;
    }


    public Match add(Match match,String jwt) {
        List<PlayingTeam> pl = new ArrayList<>();
        for(int i=0;i<2;i++)
        {
            pl.add(new PlayingTeam());
            List<TeamPlayer> tp = new ArrayList<>();
            List<Player> p = this.teamClient.getTeam(match.getTeams().get(i).getTeamId(),jwt).getPlayers();
            for(int j=0;j<11;j++)
            {
                tp.add(new TeamPlayer());
                tp.get(j).setPlayer(p.get(j));
                tp.get(j).setTeamPlayerId(this.sequenceService.updateCounter(TeamPlayer.TEAM_PLAYER_SEQUENCE));
            }
            pl.get(i).setTeamPlayer(tp);
            pl.get(i).setPlayingTeamId(this.sequenceService.updateCounter(PlayingTeam.PLAYING_TEAM_SEQUENCE));
        }
        match.setPlayingTeams(pl);
        return this.matchRepository.save(match);

    }

//    public List<Match> get() {
//        return this.matchRepository.findAll();
//    }
//
//
//
    public HashMap<String,String> toss(int id) {
        Match m = this.matchRepository.findById(id).orElse(null);
        HashMap<String,String> map = new HashMap<>();
        int toss = (int)(Math.random()*2);
        if(toss==0)
        {
            m.setBatting(m.getPlayingTeams().get(0).getPlayingTeamId());
            this.matchRepository.save(m);
            map.put("Bat First",m.getTeams().get(0).getTeamName());
        }
        else{
            m.setBatting(m.getPlayingTeams().get(0).getPlayingTeamId());
            this.matchRepository.save(m);
            map.put("Bat First",m.getTeams().get(1).getTeamName());
        }
        return map;


    }

    public Match startGame(int id) {
        Match m=this.matchRepository.findById(id).orElse(null);
        PlayingTeam teamA=m.getPlayingTeams().get(0).getPlayingTeamId() == m.getBatting() ? m.getPlayingTeams().get(0) : m.getPlayingTeams().get(1);
        PlayingTeam teamB=m.getPlayingTeams().get(0).getPlayingTeamId() != m.getBatting() ? m.getPlayingTeams().get(0) : m.getPlayingTeams().get(1);
        ArrayList<Innings> i = new ArrayList<>();
        boolean b= true;
        int target=0;
        for(int j=0;j<2;j++)
        {
            i.add(new Innings());
            i.get(j).setInningsNo(j+1);
            i.get(j).setOvers(new ArrayList<Overs>());
            HashMap<String,Object> map = this.inningsservice.play(teamA,teamB,i.get(j),m,b,target);
            i.set(j,(Innings) map.get("innings"));
            b=false;
            teamA=(PlayingTeam) map.get("teamA");
            teamB=(PlayingTeam) map.get("teamB");
            target=i.get(j).getRuns();
            PlayingTeam temp=teamA;
            teamA=teamB;
            teamB=temp;

        }
        System.out.println("Come on ..................................");
        List<PlayingTeam> plTeam = new ArrayList<>();
        plTeam.add(teamA);
        plTeam.add(teamB);
        m.setPlayingTeams(plTeam);
        m.setScoreBoard(determineWinner(i,teamA,teamB));
        m.setInnings(i);
        return this.matchRepository.save(m);
    }

    public Scoreboard determineWinner(List<Innings> innings, PlayingTeam teamA, PlayingTeam teamB)
    {
        Scoreboard sc = new Scoreboard();
        sc.setTarget(innings.get(0).getRuns());
        if(innings.get(0).getRuns()>innings.get(1).getRuns())
        {
            sc.setWinner(teamA);
        }
        else if(innings.get(1).getRuns()>innings.get(0).getRuns())
        {
            sc.setWinner(teamB);
        }
        else{
            sc.setDraw(true);
        }
        return sc;
    }

    public Match getMatcById(int id) {
        return this.matchRepository.findById(id).orElse(null);
    }

    public PlayerIdentity getPlayerInfo(int playerId, int matchId) {
        return this.matchRepository.findPlayer(playerId,matchId);

    }
}
