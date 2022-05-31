package com.view;

import com.model.Group;
import com.model.Match;
import com.model.Team;
import com.service.ITeamService;
import com.service.SimulatorService;
import com.util.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class SimulationView {

    @Autowired
    ITeamService iTeamService;

    @Autowired
    SimulatorService simulatorService;

    @Autowired
    Commons commons;

    private Map<String,Integer> scorers = new HashMap<>();

    public void simView(){
        List<String> qualifiersList = qualifiers();

        List<Group> groupA = makeGroup("QAT","ECU","SEN","NED");
        List<Group> groupB = makeGroup("ENG","IRN","USA",qualifiersList.get(0));
        List<Group> groupC = makeGroup("ARG","KSA","MEX","POL");
        List<Group> groupD = makeGroup("FRA",qualifiersList.get(1),"DEN","TUN");
        List<Group> groupE = makeGroup("SPA",qualifiersList.get(2),"GER","JAP");
        List<Group> groupF = makeGroup("BEL","CAN","MAR","CRO");
        List<Group> groupG = makeGroup("BRA","SRB","SUI","CMR");
        List<Group> groupH = makeGroup("POR","GHA","URU","KOR");

        System.out.println("***************************************************");
        System.out.println("******************  First Date  *******************");
        System.out.println("***************************************************");
        System.out.println("");
        List<Integer> result = matchFormatterGroups(groupA.get(2).getTeam(),groupA.get(3).getTeam());
        setGroupTeam(groupA.get(2),groupA.get(3),result);
        result = matchFormatterGroups(groupA.get(0).getTeam(),groupA.get(1).getTeam());
        setGroupTeam(groupA.get(0),groupA.get(1),result);

        System.out.println("");
        System.out.println("***************************************************");
        System.out.println("********************  Group A  ********************");
        groupA.sort(Comparator.comparing(Group::getPts));
        for (int i = 0; i < 4; i++){
            System.out.println(groupA.get(i).toString());
        }
        System.out.println("***************************************************");

    }

    public List<String> qualifiers(){
        List<String> qualifiers = new ArrayList<>();
        System.out.println("***************************************************");
        System.out.println("******************  Qualifiers  *******************");
        System.out.println("***************************************************");
        System.out.println("");
        String winnerPreGB = matchFormatterQualifiers("SCO","UKR","The winner will play with Wales");

        String toGroupB = matchFormatterQualifiers("WAL",winnerPreGB,"The winner will go to Group B");
        qualifiers.add(toGroupB);

        String winnerPreGD = matchFormatterQualifiers("UAE","AUS","The winner will play with Peru");

        String toGroupD = matchFormatterQualifiers("PER",winnerPreGD,"The winner will go to Group D");
        qualifiers.add(toGroupD);

        String toGroupE = matchFormatterQualifiers("CRC","NZL","The winner will go to Group E");
        qualifiers.add(toGroupE);

        return qualifiers;
    }

    public String matchFormatterQualifiers(String t1, String t2, String msg){
        System.out.println("**************************************************");
        System.out.println(msg);
        Team team1 = iTeamService.getDataTeam(t1);
        Team team2 = iTeamService.getDataTeam(t2);
        Match match = simulatorService.getMatch(team1,team2);
        Integer resHome = Integer.parseInt(match.getResultHome());
        Integer resAway = Integer.parseInt(match.getResultAway());
        commons.matchPrintFormatter(match,resHome,resAway);
        String winner = null;
        if (resHome > resAway){
            winner = t1;
        } else {
            if (resHome < resAway){
                winner = t2;
            } else {
                winner =penalties(t1,t2);
            }
        }
        return winner;
    }

    public List<Group> makeGroup(String team1, String team2,String team3, String team4){
        Group group1 = new Group(team1);
        Group group2 = new Group(team2);
        Group group3 = new Group(team3);
        Group group4 = new Group(team4);
        List<Group> group = Arrays.asList(group1,group2,group3,group4);
        return group;
    }

    public List<Integer> matchFormatterGroups(String t1, String t2){
        List<Integer> listRes = new ArrayList<>();
        Team team1 = iTeamService.getDataTeam(t1);
        Team team2 = iTeamService.getDataTeam(t2);
        Match match = simulatorService.getMatch(team1,team2);
        Integer resHome = Integer.parseInt(match.getResultHome());
        Integer resAway = Integer.parseInt(match.getResultAway());
        commons.matchPrintFormatter(match,resHome,resAway);
        Integer localPoints = 0;
        if (resHome > resAway){
            localPoints = 3;
        } else {
            if (resHome < resAway){
                localPoints = 0;
            } else {
                localPoints =1;
            }
        }
        listRes.add(localPoints);
        listRes.add(resHome);
        listRes.add(resAway);
        return listRes;
    }

    public String penalties(String home, String away){
        Integer cantGoalsHome = 0;
        Integer cantKickedHome = 0;
        Integer cantGoalsAway = 0;
        Integer cantKickedAway = 0;
        String winner = null;
        Integer totalKicked = 0;
        List<String> listHome = new ArrayList<>();
        List<String> listAway = new ArrayList<>();
        System.out.println("Penalties: ");
        while (winner == null){
            if (cantKickedHome < 5){
                Integer randomNum = ThreadLocalRandom.current().nextInt(1, 100);
                if (randomNum < 76){
                    cantGoalsHome++;
                    listHome.add("O");
                } else {
                    listHome.add("X");
                }
                cantKickedHome++;
                totalKicked++;
            }
            if (((totalKicked) >= 6) && ((totalKicked) <= 10)){
                Integer restKickHome = 5 - cantKickedHome;
                Integer restKickAway = 5 - cantKickedAway;
                if ((cantGoalsHome + restKickHome) < cantGoalsAway) winner = away;
                if ((cantGoalsAway + restKickAway) < cantGoalsHome) winner = home;
            }
            if (cantKickedAway < 5){
                Integer randomNum = ThreadLocalRandom.current().nextInt(1, 100);
                if (randomNum < 76){
                    cantGoalsAway++;
                    listAway.add("O");
                } else {
                    listAway.add("X");
                }
                cantKickedAway++;
                totalKicked++;
            }
            if (((totalKicked) >= 6) && ((totalKicked) <= 10)){
                Integer restKickHome = 5 - cantKickedHome;
                Integer restKickAway = 5 - cantKickedAway;
                if ((cantGoalsHome + restKickHome) < cantGoalsAway) winner = away;
                if ((cantGoalsAway + restKickAway) < cantGoalsHome) winner = home;
            }
            if((totalKicked) > 10){
                if (cantGoalsHome > cantGoalsAway) winner = home;
                if (cantGoalsAway > cantGoalsHome) winner = away;
            }
        }
        System.out.format("%3s %10s %2s",
                home, listHome, cantGoalsHome);
        System.out.println();
        System.out.format("%3s %10s %2s",
                away, listAway, cantGoalsAway);
        System.out.println();
        return winner;
    }

    public void setGroupTeam(Group team1, Group team2, List<Integer> result){
        team1.setMp(team1.getMp()+1);
        team2.setMp(team2.getMp()+1);
        if (result.get(0) == 3){
            team1.setW(team1.getW()+1);
            team1.setPts(team1.getPts()+3);
            team2.setL(team2.getL()+1);
        } else {
            if (result.get(0) == 0){
                team1.setL(team1.getL()+1);
                team2.setW(team2.getW()+1);
                team2.setPts(team2.getPts()+3);
            } else {
                team1.setD(team1.getD()+1);
                team1.setD(team1.getD()+1);
                team2.setPts(team2.getPts()+1);
                team2.setPts(team2.getPts()+1);
            }
        }
        team1.setGf(team1.getGf()+result.get(1));
        team2.setGf(team2.getGf()+result.get(2));
        team1.setGa(team1.getGa()+result.get(2));
        team2.setGa(team2.getGa()+result.get(1));
        team1.setDif(team1.getGf()- team1.getGa());
        team2.setDif(team2.getGf()- team2.getGa());
    }



}
