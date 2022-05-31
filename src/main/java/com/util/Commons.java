package com.util;

import com.model.Match;

public class Commons {

    public void matchPrintFormatter(Match match,Integer resHome,Integer resAway){
        System.out.println("---------------------------------------------------");
        System.out.format("%1s %-20s %1s %1s %1s %20s %1s",
                "-",match.getHome(), resHome, "-", resAway, match.getAway(), "-");
        System.out.println();
        System.out.println("---------------------------------------------------");
    }

    public void groupFormatter(){
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%4s %1s %1s %1s %1s %2s %2s %2s %1s", "NAME", "MP", "W", "D", "L", "GF", "GA", "+/-", "PTS");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        System.out.format("%4s %1s %1s %1s %1s %2s %2s %2s %1s",
                "-",match.getHome(), resHome, "-", resAway, match.getAway(), "-");
        System.out.println();

        System.out.println("---------------------------------------------------");
    }
}
