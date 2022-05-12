package com;
import com.controller.TeamController;
import com.model.Team;
import com.service.ConnectionService;
import com.service.ITeamService;
import com.service.SimulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.math3.distribution.*;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.commons.math3.util.Precision.round;

@SpringBootApplication
public class Fifaworldcup22Application implements CommandLineRunner {

    @Autowired
    ITeamService iTeamService;

    @Autowired
    SimulatorService simulatorService;

    private static Team team;
    private static String sim;

    public static void main(String[] args) {
        SpringApplication.run(Fifaworldcup22Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        team = iTeamService.getDataTeam("ARG");
        sim = simulatorService.getName(team);



        double cer = round(new PoissonDistribution(2.480).probability(0),2);
        double uno = round(new PoissonDistribution(2.480).probability(1),2);
        double dos = round(new PoissonDistribution(2.480).probability(2),2);
        double tre = round(new PoissonDistribution(2.480).probability(3),2);
        double cua = round(new PoissonDistribution(2.480).probability(4),2);
        double cin = round(new PoissonDistribution(2.480).probability(5),2);
        double sei = round(new PoissonDistribution(2.480).probability(6),2);
        double sie = round(new PoissonDistribution(2.480).probability(7),2);
        double och = round(new PoissonDistribution(2.480).probability(8),2);
        double sum = round(cer+uno+dos+tre+cua+cin+sei+sie+och,2);

        double cer2 = round(cer*100/sum,1);
        double uno2 = round(uno*100/sum,1);
        double dos2 = round(dos*100/sum,1);
        double tre2 = round(tre*100/sum,1);
        double cua2 = round(cua*100/sum,1);
        double cin2 = round(cin*100/sum,1);
        double sei2 = round(sei*100/sum,1);
        double sie2 = round(sie*100/sum,1);
        double och2 = round(och*100/sum,1);
        double sum2 = round(cer2+uno2+dos2+tre2+cua2+cin2+sei2+sie2+och2,1);
/*
        System.out.println("0: "+cer2);
        System.out.println("1: "+uno2);
        System.out.println("2: "+dos2);
        System.out.println("3: "+tre2);
        System.out.println("4: "+cua2);
        System.out.println("5: "+cin2);
        System.out.println("6: "+sei2);
        System.out.println("7: "+sie2);
        System.out.println("8: "+och2);
        System.out.println("Sum: "+sum2);
*/
        ArrayList<Integer> probsArray=new ArrayList<Integer>();

        int count0= (int) (cer2*10);
        int total = 0;
        total+=count0;
        while(count0!=0){
            probsArray.add(0);
            count0--;
        }
        int count1=(int) (uno2*10);
        total+=count1;
        while(count1!=0){
            probsArray.add(1);
            count1--;
        }
        int count2=(int) (dos2*10);
        total+=count2;
        while(count2!=0){
            probsArray.add(2);
            count2--;
        }
        int count3=(int) (tre2*10);
        total+=count3;
        while(count3!=0){
            probsArray.add(3);
            count3--;
        }
        int count4=(int) (cua2*10);
        total+=count4;
        while(count4!=0){
            probsArray.add(4);
            count4--;
        }
        int count5=(int) (cin2*10);
        total+=count5;
        while(count5!=0){
            probsArray.add(5);
            count5--;
        }
        int count6=(int) (sei2*10);
        total+=count6;
        while(count6!=0){
            probsArray.add(6);
            count6--;
        }
        int count7=(int) (sie2*10);
        total+=count7;
        while(count7!=0){
            probsArray.add(7);
            count7--;
        }
        int count8=(int) (och2*10);
        total+=count8;
        while(count8!=0){
            probsArray.add(8);
            count8--;
        }

        //   System.out.println("total is "+total);

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0,0);
        map.put(1,0);
        map.put(2,0);
        map.put(3,0);
        map.put(4,0);
        map.put(5,0);
        map.put(6,0);
        map.put(7,0);
        map.put(8,0);
        Integer randomNum = 0;

        for (int i = 1; i <= 10; i++) {
            // random del total
            randomNum = ThreadLocalRandom.current().nextInt(0, total);
            // numero del 0 al 8
            Integer key = probsArray.get(randomNum);
            Integer num = map.get(key);
            num = num + 1;
            map.put(key, num);
        }

        int maxValue = 0;
        int actualValue = 0;
        int maxKey = 0;
        for (int y = 0; y <= 8; y++) {
            actualValue = map.get(y);
            if (actualValue > maxValue) {
                maxValue = actualValue;
                maxKey = y;
            }
        }
        System.out.println(map);
        System.out.println("Final result: " + maxKey);

        System.out.println(sim);

        System.out.println(simulatorService.getHola2());

    }
}
