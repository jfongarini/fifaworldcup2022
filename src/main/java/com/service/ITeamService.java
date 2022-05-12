package com.service;

import com.model.Ranking;
import com.model.Team;
import org.springframework.stereotype.Service;

@Service
public interface ITeamService {
    public Team getDataTeam(String country);
    public Ranking getRanking(String country);
}
