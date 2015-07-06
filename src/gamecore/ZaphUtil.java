package gamecore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ZaphUtil {
    private ZaphUtil(){}
    
    public static <K,V> Map<K,V> newMap() {
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
}
