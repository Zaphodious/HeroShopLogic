package gamecore;

import java.util.Random;
/**
 * Each Dice object represents a die with a number of sides corresponding to the number coming after "D". This follows the convention of RPGs using polyhedral dice to determine random numbers.
 * @author Alex Chythlook
 *
 */
public enum Dice {

    D10() {
	@Override
	public int roll() {
	    return rand.nextInt(10) + 1;
	}
    },
    D6() {
	@Override
	public int roll() {
	    return rand.nextInt(6) + 1;
	}
    },
    D3() {
	@Override
	public int roll() {
	    return rand.nextInt(3) + 1;
	}
    },
    D100() {
	@Override
	public int roll() {
	    return rand.nextInt(100) + 1;
	}
    };

    private static final Random rand = new Random();

    public abstract int roll();

}
