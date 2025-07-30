package fr.pierrickviret.javaquest;

import java.util.Random;

public class Dice {
    public Integer getRoll(){
        Random rand = new Random();
        return rand.nextInt(1, 7);
    }
}
