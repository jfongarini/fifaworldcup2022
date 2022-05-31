package com;

import com.view.SimulationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Fifaworldcup22Application implements CommandLineRunner {

    @Autowired
    SimulationView simulationView;

    public static void main(String[] args) {
        SpringApplication.run(Fifaworldcup22Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        simulationView.simView();
    }
}
