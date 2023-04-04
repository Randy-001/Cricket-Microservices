package com.example.MatchService.repository;


import com.example.MatchService.models.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends MongoRepository<Sequence,String > {

}

