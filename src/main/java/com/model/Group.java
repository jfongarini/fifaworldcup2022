package com.model;

public class Group {
    private String team;
    private int mp;
    private int w;
    private int d;
    private int l;
    private int gf;
    private int ga;
    private int dif;
    private int pts;

    public Group(String team) {
        this.team = team;
        this.mp = 0;
        this.w = 0;
        this.d = 0;
        this.l = 0;
        this.gf = 0;
        this.ga = 0;
        this.dif = 0;
        this.pts = 0;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getGf() {
        return gf;
    }

    public void setGf(int gf) {
        this.gf = gf;
    }

    public int getGa() {
        return ga;
    }

    public void setGa(int ga) {
        this.ga = ga;
    }

    public int getDif() {
        return dif;
    }

    public void setDif(int dif) {
        this.dif = dif;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    @Override
    public String toString() {
        return "Group{" +
                "team=" + team +
                ", mp=" + mp +
                ", w=" + w +
                ", d=" + d +
                ", l=" + l +
                ", gf=" + gf +
                ", ga=" + ga +
                ", dif=" + dif +
                ", pts=" + pts +
                '}';
    }
}
