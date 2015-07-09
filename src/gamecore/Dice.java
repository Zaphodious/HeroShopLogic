package gamecore;

import java.util.Random;

/**
 * Each Dice object represents a die with a number of sides corresponding to the
 * number coming after "D". This follows the convention of RPGs using polyhedral
 * dice to determine random numbers.
 * 
 * @author Alex Chythlook
 *
 */
public enum Dice {

    D10(10),
    D8(8),
    D12(12),
    D6(6),
    D4(4),
    D3(3),
    D20(20),
    D2(2),
    D100(100);

    int sides;
    private static final Random rand = new Random();
    
    Dice(int sides) {
	this.sides = sides;
    }
    
    

    public int roll() {
	return rand.nextInt(sides) + 1;
    };

}
