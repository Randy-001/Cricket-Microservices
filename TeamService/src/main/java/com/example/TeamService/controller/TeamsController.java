package com.example.TeamService.controller;

import com.example.TeamService.models.Player;
import com.example.TeamService.models.Team;
import com.example.TeamService.service.SequenceService;
import com.example.TeamService.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/team")
public class TeamsController {

    private final SequenceService sequenceService;
    private final TeamService teamService;

    @Autowired
    public TeamsController(SequenceService sequenceService, TeamService teamService) {
        this.sequenceService = sequenceService;
        this.teamService = teamService;
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<HashMap> addTeam(@RequestBody Team team)
    {
        team.setTeamId(sequenceService.updateCounter(Team.TEAM_SEQUENCE));
        for(int i =0 ;i<11;i++)
        {
            team.getPlayers().get(i).setPlayerId(sequenceService.updateCounter(Player.PLAYER_SEQUENCE));
        }
        teamService.addTeam(team);
        HashMap<String, String> map = new HashMap<>();
        map.put("Added", "Success");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{id}")
    public Team getTeam(@PathVariable int id)
    {
        //System.out.println(securityContext.getAuthentication().getPrincipal());
        return teamService.getTeam(id);
    }
}


