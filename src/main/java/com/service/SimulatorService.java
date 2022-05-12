package com.service;

import com.model.Match;
import com.model.Ranking;
import com.model.Team;
import com.util.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.math3.distribution.PoissonDistribution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SimulatorService {

    @Autowired
    ITeamService iTeamService;

    public Map<String,Double> getMatrix(Team team){
        //sumar los goles a favor y multiplicarlos por los puntos de ranking del adversarion /1000
        //dividir por la cantidad de partidos
        //sumar los goles en contra y multiplicarlos por los puntos de ranking del pais /1000
        //dividir por la cantidad de partidos
        String rankingHomeAux = iTeamService.getRanking(Enums.Ranking.valueOf(team.getName()).getName()).getRankingPoints();
        int rankingHome = Integer.parseInt(rankingHomeAux.trim());
        Map<String,Double> matrix = new HashMap<>();
        double homeGoals = 0;
        double awayGoals = 0;
        double attackMatrix = 0;
        double defenceMatrix = 0;
        for (Match match:team.getMatchList()) {
            int rankingAway = 0;
            String[] resSplit = match.getResult().split("-");
            if (match.getHome().equals(team.getName())){
                homeGoals = Integer.parseInt(resSplit[0].trim());
                awayGoals = Integer.parseInt(resSplit[1].trim());
            } else {
                homeGoals = Integer.parseInt(resSplit[1].trim());
                awayGoals = Integer.parseInt(resSplit[0].trim());
            }
            if (awayGoals != 0){
                String rankingAwayAux = iTeamService.getRanking(Enums.Ranking.valueOf(match.getHome()).getName()).getRankingPoints();
                rankingAway = Integer.parseInt(rankingAwayAux.trim());
                defenceMatrix += awayGoals*(rankingAway/100);
            }
            if (homeGoals != 0) attackMatrix += homeGoals*(rankingHome/100);
        }
        matrix.put("Attack",attackMatrix);
        matrix.put("Defence",defenceMatrix);

        //multiplicar los anteriores para encontrar el promedio
        double matrixAverage = attackMatrix * defenceMatrix;

        //calcular poisson de cada gol posible


        return matrix;
    }

    public String getName(Team team){
        System.out.println("Hola");
        return team.getName();
    }

    public String getHola2(){
        return "Hola2";
    }
}
