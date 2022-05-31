package com.model;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private String date;
    private String home;
    private String away;
    private String resultHome;
    private String resultAway;
    private List<Player> goalsHome = new ArrayList<>();
    private List<Player> goalsAway = new ArrayList<>();

    public Match() {
    }

    public Match(String date, String home, String away, String resultHome,String resultAway, List<Player> goalsHome, List<Player> goalsAway) {
        this.date = date;
        this.home = home;
        this.away = away;
        this.resultHome = resultHome;
        this.resultAway = resultAway;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getResultHome() {
        return resultHome;
    }

    public void setResultHome(String resultHome) {
        this.resultHome = resultHome;
    }

    public String getResultAway() {
        return resultAway;
    }

    public void setResultAway(String resultAway) {
        this.resultAway = resultAway;
    }

    public List<Player> getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(List<Player> goalsHome) {
        this.goalsHome = goalsHome;
    }

    public List<Player> getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(List<Player> goalsAway) {
        this.goalsAway = goalsAway;
    }

    @Override
    public String toString() {
        return "Match{" +
                "date='" + date + '\'' +
                ", home='" + home + '\'' +
                ", away='" + away + '\'' +
                ", resultHome='" + resultHome + '\'' +
                ", resultAway='" + resultAway + '\'' +
                ", goalsHome=" + goalsHome.toString() +
                ", goalsAway=" + goalsAway.toString() +
                '}';
    }
}
