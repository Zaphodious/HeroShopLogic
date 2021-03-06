package gamecore;

import gamecore.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ZaphUtil {
    private ZaphUtil() {
    }

    public static <K, V> Map<K, V> newMap() {
	return new HashMap<K, V>();
    }

    public static <K> List<K> newList() {
	return new ArrayList<K>();
    }

    public static int secondsPassed(int startTime) {
	return getSecondsTimeStamp() - startTime;
    }

    public static int getSecondsTimeStamp() {
	return (int) TimeUnit.NANOSECONDS.toSeconds(System.nanoTime());
    }

    public static boolean isWithinCentRange(int toCheck) {
	if (toCheck > 0 && toCheck < 100) {
	    return true;
	} else {
	    if (toCheck < 1) {
		throw new IllegalArgumentException("Sorry, the value that was provided was less then 1 (which means that it's so rare that it will never happen.)");
	    } else {
		throw new IllegalArgumentException("Sorry, the value that was provided was greater then 100 (which means that it's so common that it basically makes up the universe!)");
	    }
	}
    }

    public static Entity[] concat(Entity[] a, Entity[] b) {
	int aLen = a.length;
	int bLen = b.length;
	Entity[] c = new Entity[aLen + bLen];
	System.arraycopy(a, 0, c, 0, aLen);
	System.arraycopy(b, 0, c, aLen, bLen);
	return c;
    }

    public static int STAT_ROLL(int diceSides) {
	/*
	 * This doesn't do anything in the rest of the program at the moment. I
	 * feel proud of the logic, however, so it's remaining in my util file.
	 */
	Random rand = new Random();
	int toReturn = 0;

	/*
	 * How this works- this first rolls four random values within a range,
	 * after which it compares each one to each other if the lowest hasn't
	 * been found yet. A given value can be less then another arbitrary
	 * value in the array without being the lowest, but a value cannot be
	 * *greater* then another value in the array while also being the lowest
	 * value. So, we check to see if the number is higher then any other
	 * number. If it is, then it's not the lowest. If it's higher then no
	 * other number, then we replace it in the array with 0, and after that
	 * we add the number to the value that we're going to return.
	 */
	int[] rolls = { (rand.nextInt(diceSides) + 1), (rand.nextInt(diceSides) + 1), (rand.nextInt(diceSides) + 1), (rand.nextInt(diceSides) + 1) };
	boolean hasFoundTheLowest = false;
	for (int i = 0; i < rolls.length; i++) {
	    boolean isHigherThenSomethingElse = false;
	    if (!hasFoundTheLowest) {
		for (int j = 0; j < rolls.length; j++) {
		    if (rolls[i] > rolls[j]) {
			isHigherThenSomethingElse = true;
			break;
		    }

		}
		if (!isHigherThenSomethingElse) {
		    System.out.print(rolls[i] + " was the lowest out of [" + rolls[0] + ", " + rolls[1] + ", " + rolls[2] + ", " + rolls[3] + "], ");
		    rolls[i] = 0;
		    hasFoundTheLowest = true;
		}
	    }

	    toReturn += rolls[i];
	}

	System.out.println("and the end result was " + toReturn);
	return toReturn;
    }
}
