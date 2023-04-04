package com.example.TeamService.repository;

import com.example.TeamService.models.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository  extends MongoRepository<Team,Integer> {
}
