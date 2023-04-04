package com.example.MatchService.controller;

import com.example.MatchService.models.Match;
import com.example.MatchService.models.PlayerIdentity;
import com.example.MatchService.service.MatchService;
import com.example.MatchService.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;
    private final SequenceService sequenceService;


    public MatchController(MatchService matchService, SequenceService sequenceService) {
        this.matchService = matchService;
        this.sequenceService = sequenceService;
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/add")
    public Match addMatch(@RequestBody Match match, HttpServletRequest httpServletRequest)
    {
        match.setMatchId(sequenceService.updateCounter(Match.MATCH_SEQUENCE));
        return matchService.add(match,httpServletRequest.getHeader("Authorization"));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/toss")
    public HashMap<String,String> toss(@RequestParam("matchId") int id)
    {
        return this.matchService.toss(id);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/start")
    public Match start(@RequestParam("matchId") int id)
    {
        return this.matchService.startGame(id);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping
    public Match getMatchById(@RequestParam("matchId") int id)
    {
        return this.matchService.getMatcById(id);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/player")
    public PlayerIdentity getPlayerInfo(@RequestParam("playerId") int playerId, @RequestParam("matchId") int matchId)
    {
        return this.matchService.getPlayerInfo(playerId,matchId);
    }

}
