package com.service;

import com.model.Ranking;
import com.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements ITeamService {

    @Value("${url.team}")
    public String URL_TEAM;

    @Value("${url.ranking}")
    public String URL_RANKING;

    @Autowired
    ConnectionService connectionService;

    @Override
    public Team getDataTeam(String country) {
        Team team = new Team();
        return connectionService.getData(team,URL_TEAM,country);
    }

    @Override
    public Ranking getRanking(String country) {
        Ranking ranking = new Ranking();
        return connectionService.getRanking(ranking,URL_RANKING,country);
    }
}
