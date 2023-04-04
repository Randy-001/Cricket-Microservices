package com.example.MatchService.repository;

import com.example.MatchService.models.Match;
import com.example.MatchService.models.PlayerIdentity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends MongoRepository<Match,Integer> {
    @Aggregation(pipeline = {
            "{'$match':{'_id':?1}}",
            "{'$unwind':{path: '$playingTeams'}}",
            "{'$unwind':{path: '$playingTeams.teamPlayer'}}",
            "{'$match': {'playingTeams.teamPlayer.player._id': ?0}}",
            "{'$project': {'playingTeams': 1}}",
    })

        //@Query(value = "{'_id': ?1,'playingTeams.teamPlayer.player._id': ?0}",fields = "{'playingTeams.teamPlayer': 1}")
    PlayerIdentity findPlayer(int playerId, int matchId);
}
