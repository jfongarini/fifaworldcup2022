package com.controller;

import com.model.Team;
import com.service.ITeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TeamController {

    @Autowired
    private ITeamService iTeamService;

    @GetMapping("/")
    public void init(){

        Team team = iTeamService.getDataTeam("ARG");
        System.out.println(team.getName());

    }

}
