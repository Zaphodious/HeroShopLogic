package gamecore.shop;

import gamecore.ItemDrop;
import gamecore.ZaphUtil;
import gamecore.entity.Entity;
import gamecore.entity.EntityBuilder;
import gamecore.entity.EntityType;

import java.util.List;

public class EmploymentPosition {
    
    String title;

    Entity employee;

    List<ItemDrop> possibleResults;
    
    int timeStamp;
    
    int timeBetweenDrops;
    
    public EmploymentPosition() {
	title = "worker";
	employee = new EntityBuilder("Karlos",EntityType.HERO).build();
	timeStamp = ZaphUtil.getSecondsTimeStamp();
	timeBetweenDrops = 20;
	possibleResults = ZaphUtil.newList();
    }
    
    public int howManyChances() {
	return  ZaphUtil.secondsPassed(timeStamp)/timeBetweenDrops;
    }
    
    public int secondsUntilNextChance() {
	return this.timeBetweenDrops - (ZaphUtil.secondsPassed(timeStamp)%timeBetweenDrops);
    }
    
    private void resetChances() {
	this.timeStamp = ZaphUtil.getSecondsTimeStamp() - (ZaphUtil.secondsPassed(timeStamp)%timeBetweenDrops);
    }
    
    public int collectItems() {
	if (howManyChances() >= 1) {
	    int collectedItems = this.howManyChances();
	    resetChances();
	    
	    
	    return collectedItems;
	}
	return 0;
    }
    
}
