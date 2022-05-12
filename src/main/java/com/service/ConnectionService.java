package com.service;

import com.model.Ranking;
import com.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ConnectionService {

    @Autowired
    private RestTemplate restTemplate;

    public Team getData(Team team, String URL, String urlParam){
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL);
        return restTemplate.getForObject(builder.buildAndExpand(urlParam).toUri(),team.getClass());
    }

    public Ranking getRanking(Ranking ranking, String URL, String urlParam){
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL);
        return restTemplate.getForObject(builder.buildAndExpand(urlParam).toUri(),ranking.getClass());
    }
}
