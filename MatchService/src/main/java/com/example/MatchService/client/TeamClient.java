package com.example.MatchService.client;


import com.example.MatchService.models.Team;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient("team-service")
public interface TeamClient {
    @GetMapping("/team/{id}")
    @LoadBalanced
    Team getTeam(@PathVariable("id") int id,@RequestHeader(value="Authorization",required = true) String jwt);
}
