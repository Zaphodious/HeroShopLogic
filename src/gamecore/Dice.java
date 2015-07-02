package gamecore;

import java.util.Random;

public enum Dice {

    D10(10),
    D6(6),
    D3(3),
    SALT(999999999);
    
    int sides;
    private static Random rand = new Random();
    
    Dice(int sides) {
	this.sides = sides;
    }
    
    public int roll() {
	return rand.nextInt(sides)+1;
    }
    
    
    
}
