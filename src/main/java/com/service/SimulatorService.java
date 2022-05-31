package com.service;

import com.model.Match;
import com.model.Player;
import com.model.Team;
import com.util.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.math3.distribution.PoissonDistribution;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.apache.commons.math3.util.Precision.round;

@Service
public class SimulatorService {

    @Autowired
    ITeamService iTeamService;
    public Map<String,Double> getMatrix(Team team,List<String> goalsList){
        //sumar los goles a favor y multiplicarlos por los puntos de ranking del adversarion /1000
        //dividir por la cantidad de partidos
        //sumar los goles en contra y multiplicarlos por los puntos de ranking del pais /1000
        //dividir por la cantidad de partidos
        String rankingHomeAux = iTeamService.getRanking(Enums.Ranking.valueOf(team.getName()).getName()).getRankingPoints();
        double rankingHome = Double.parseDouble(rankingHomeAux.trim());
        Map<String,Double> matrix = new HashMap<>();
        double homeGoals = 0;
        double awayGoals = 0;
        double attackMatrix = 0;
        double defenceMatrix = 0;
        for (Match match:team.getMatchList()) {
            double rankingAway = 0;
            if (match.getHome().equals(team.getName())){
                homeGoals = Integer.parseInt(match.getResultHome());
                awayGoals = Integer.parseInt(match.getResultAway());
                for (Player playerGoal:match.getGoalsHome()) {
                    goalsList.add(playerGoal.getName());
                }
            } else {
                homeGoals = Integer.parseInt(match.getResultAway());
                awayGoals = Integer.parseInt(match.getResultHome());
                for (Player playerGoal:match.getGoalsAway()) {
                    goalsList.add(playerGoal.getName());
                }
            }
            if (awayGoals != 0){
                String rankingAwayAux = iTeamService.getRanking(Enums.Ranking.valueOf(match.getHome()).getName()).getRankingPoints();
                rankingAway = Double.parseDouble(rankingAwayAux.trim());
                defenceMatrix += awayGoals*(rankingAway*0.001);
            }
            if (homeGoals != 0) attackMatrix += homeGoals*(rankingHome*0.001);
        }
        attackMatrix = attackMatrix / team.getMatchList().size();
        defenceMatrix = defenceMatrix / team.getMatchList().size();
        matrix.put("Ranking",rankingHome);
        matrix.put("Attack",attackMatrix);
        matrix.put("Defence",defenceMatrix);

        return matrix;
    }

    public Integer getResult(Map<String,Double> matrixTeam1, Map<String,Double> matrixTeam2){

        double homeRanking = matrixTeam1.get("Ranking");
        double homeAttMatrix = matrixTeam1.get("Attack");

        double awayDefMatrix = matrixTeam2.get("Defence");

        //multiplicar los anteriores para encontrar el promedio
        double matrixAverage = homeAttMatrix * awayDefMatrix / (homeRanking*0.001);
        //calcular poisson de cada gol posible
        Integer rs = poissonDist(matrixAverage);

        return rs;
    }

    public int poissonDist(double matrixAverage){
        ArrayList<Double> poissonArray=new ArrayList<Double>();
        double totalPoissonArray = 0;
        for (int i = 0; i <= 8; i++) {
            double num = round(new PoissonDistribution(matrixAverage).probability(i),2);
            poissonArray.add(num);
            totalPoissonArray+=num;
        }
        totalPoissonArray = round(totalPoissonArray,2);

        ArrayList<Double> averageArray=new ArrayList<Double>();
        for (int i = 0; i <= 8; i++) {
            double num = round(poissonArray.get(i)*100/totalPoissonArray,1);
            averageArray.add(num);
        }

        ArrayList<Integer> probArray=new ArrayList<Integer>();
        int total = 0;
        for (int i = 0; i <= 8; i++) {
            int num = (int) (averageArray.get(i)*10);
            total+=num;
            while(num!=0){
                probArray.add(i);
                num--;
            }
        }

        Integer randomNum = ThreadLocalRandom.current().nextInt(0, total);
        Integer key = probArray.get(randomNum);

        return key;
    }

    public List<Player> goalsPlayer(List<String> goalsList, Integer result) {
        List<Player> playerList = new ArrayList<>();

        int totalGoals = goalsList.size();

        for (int i = 0; i < result; i++) {
            Player player = new Player();
            Integer randomNum = ThreadLocalRandom.current().nextInt(0, totalGoals);
            String key = goalsList.get(randomNum);
            player.setName(key);
            String goalTime = null;
            Integer randomTime = ThreadLocalRandom.current().nextInt(0, 90);
            Integer randomExtraTime = 0;
            if (randomTime == 45) {
                randomExtraTime = ThreadLocalRandom.current().nextInt(0, 5);
            }
            if (randomTime == 90) {
                randomExtraTime = ThreadLocalRandom.current().nextInt(0, 5);
            }
            if (randomExtraTime == 0) {
                if (randomTime == 0){
                    goalTime = "0"+randomTime;
                }
                goalTime = randomTime.toString();
            } else
                goalTime = randomTime + "+" + randomExtraTime;

            player.setTime(goalTime);
            playerList.add(player);
        }

        Comparator<Player> compareByName = (Player o1, Player o2) ->
                o1.getTime().compareTo( o2.getTime() );

        Collections.sort(playerList, compareByName);

        return playerList;
    }

    public Match getMatch(Team team1, Team team2){
        Match match = new Match();

        match.setHome(team1.getName());
        match.setAway(team2.getName());

        List<String> goalsHome = new ArrayList<>();
        List<String> goalsAway = new ArrayList<>();
        Map<String,Double> matrixTeam1 = getMatrix(team1,goalsHome);
        Map<String,Double> matrixTeam2 = getMatrix(team2,goalsAway);
        Integer resultHome = getResult(matrixTeam1, matrixTeam2);
        Integer resultAway = getResult(matrixTeam2, matrixTeam1);
        match.setResultHome(resultHome.toString());
        match.setResultAway(resultAway.toString());


        match.setGoalsHome(goalsPlayer(goalsHome,resultHome));
        match.setGoalsAway(goalsPlayer(goalsAway,resultAway));

        return match;
    }
}
