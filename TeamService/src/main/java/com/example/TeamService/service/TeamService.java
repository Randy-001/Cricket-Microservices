package com.example.TeamService.service;

import com.example.TeamService.models.Team;
import com.example.TeamService.repository.TeamRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team getTeam(int id)
    {
        return this.teamRepository.findById(id).get();
    }

    public void addTeam(Team team) {

        this.teamRepository.save(team);
    }
}
