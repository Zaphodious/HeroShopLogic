package gamecore;

import gamecore.entity.Attribute;
import gamecore.entity.Entity;
import gamecore.item.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public class Reference {

    public static final int DEFAULT_MAX_STACK_SIZE = 42000;
    public static final Weapon DEFAULT_WEAPON = new Weapon("Fists Of Fury", 1, -1, Attribute.ATK_DEXTERITY);
    private static final int xpMultiplier = 100;

    public static int WHAT_LEVEL(int experience) {
	return experience / Reference.xpMultiplier;
    }

    public static int WHAT_EXPERIENCE(int level) {
	return level * Reference.xpMultiplier;
    }

    public static int EXPERIENCE_GAIN(int victorExperience, int deadExperience) {

	int victorLevel = WHAT_LEVEL(victorExperience);
	int deadLevel = WHAT_LEVEL(deadExperience);

	if (victorLevel - deadLevel > 5) {
	    return 1;
	} else if (deadLevel < victorLevel) {
	    return 2;
	}

	return (deadLevel - victorLevel) + 3;
    }

    public static Entity[] concat(Entity[] a, Entity[] b) {
	int aLen = a.length;
	int bLen = b.length;
	Entity[] c = new Entity[aLen + bLen];
	System.arraycopy(a, 0, c, 0, aLen);
	System.arraycopy(b, 0, c, aLen, bLen);
	return c;
    }

    public static int STAT_ROLL() {
	/*
	 * This doesn't do anything in the rest of the program. I feel
	 * proud of it, however, so it's remaining in until I put together my
	 * own personal lib.
	 */

	int toReturn = 0;
	
	int[] rolls = { Dice.D3.roll(), Dice.D3.roll(), Dice.D3.roll(), Dice.D3.roll() };
	for (int i = 0; i < rolls.length; i++) {
	    List<Boolean> isGreaterThen = new ArrayList<Boolean>();
	    for (int j = 0; j < rolls.length; j++) {
		// System.out.println("checking to see if " + rolls[i] +
		// " is greater then " + rolls[j]);
		isGreaterThen.add(j, rolls[i] > rolls[j]);
	    }
	    if (!isGreaterThen.contains(true)) {
		System.out.print(rolls[i] + " was the lowest out of [" + rolls[0] + ", " + rolls[1] + ", " + rolls[2] + ", " + rolls[3] + "], ");
		rolls[i] = 0;
	    }

	    toReturn += rolls[i];
	}

	System.out.println("and the end result was " + toReturn);
	return toReturn;
    }

}
