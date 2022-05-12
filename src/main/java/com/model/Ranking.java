package com.model;

public class Ranking {
    private String name;
    private String image;
    private String rankingPos;
    private String rankingPoints;

    public Ranking() {
    }

    public Ranking(String name, String rankingPos, String rankingPoints, String image) {
        this.name = name;
        this.rankingPos = rankingPos;
        this.rankingPoints = rankingPoints;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRankingPos() {
        return rankingPos;
    }

    public void setRankingPos(String rankingPos) {
        this.rankingPos = rankingPos;
    }

    public String getRankingPoints() {
        return rankingPoints;
    }

    public void setRankingPoints(String rankingPoints) {
        this.rankingPoints = rankingPoints;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
