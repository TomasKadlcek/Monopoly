package sample.service;

public class Dice {
    public Dice() {
    }
    // Dire randomiser
    public int randomDice(){
        return (int)(Math.random()*6+1);
    }
}
