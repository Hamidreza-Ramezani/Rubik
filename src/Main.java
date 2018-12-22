/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hamid
 */
public class Main {

    public static void main(String[] args) {
        Rubik rubik  = new Rubik();
        SimulatedAnnealing SA = new SimulatedAnnealing(0.003);
        SA.solve(rubik, SimulatedAnnealingStrategy.LINEAR_TEMPERATURE, false);

    }

}
