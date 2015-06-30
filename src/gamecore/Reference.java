package gamecore;

import gamecore.entity.Entity;
import gamecore.item.Weapon;

import java.util.Random;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public class Reference {

    public static int DEFAULT_MAX_STACK_SIZE = 42000;
    public static Random rand = new Random();
    public static Weapon DEFAULT_WEAPON	= new Weapon("Fists Of Fury", 1, -1);

    public static int WHAT_LEVEL(int experience) {
	return experience / 100;
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

}
