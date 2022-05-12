package com.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Match> matchList = new ArrayList<>();


    public Team() {
    }

    public Team(String name, List<Match> matchList) {
        this.name = name;
        this.matchList = matchList;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }
}
