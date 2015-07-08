package gamecore.shop;

import gamecore.Dice;
import gamecore.Inventory;
import gamecore.ZaphUtil;
import gamecore.item.Item;

import java.util.List;
import java.util.Map;

class DisplayCase {

    private List<Pedestal> CaseContents;
    Inventory stuffSold;
    int checkInterval;
    int plusChanceInterval;

    DisplayCase() {
	CaseContents = ZaphUtil.newList();
	stuffSold = new Inventory(999999999);
	int checkInterval = 10;
	int plusChanceInterval = 30;
    }

    void addItem(Item item, int amount) {
	this.CaseContents.add(new Pedestal(item, amount));
    }
    
    void addInventory(Inventory inventory) {
	Map<Item,Integer> inventoryContents = inventory.getItemMap();
	
	for (Item item:inventoryContents.keySet()) {
	    this.addItem(item, inventoryContents.get(item));
	}
    }

    Inventory getStuffSoldSinceLastCheck() {
	Inventory toReturn = stuffSold;
	stuffSold = new Inventory();
	return toReturn;
    }
    
    boolean runSaleCycles () {
	boolean hasSoldAnything = false;
	List<Pedestal> toRemove = ZaphUtil.newList();
	for (Pedestal ped : CaseContents) {
	    int timesToCheck = ZaphUtil.secondsPassed(ped.timeStamp)/checkInterval;
	    
	    if (timesToCheck > 0) {
		int totalBonus = ZaphUtil.secondsPassed(ped.timeStamp)/plusChanceInterval;
		int realChance = (ped.item.getSaleDifficulty()/ped.amount) + totalBonus;
		if (realChance > Dice.D100.roll()) {
		    hasSoldAnything = true;
		    stuffSold.addItem(ped.item, ped.amount);
		    toRemove.add(ped);
		}
	    }
	    
	}
	
	CaseContents.removeAll(toRemove);
	
	return hasSoldAnything;
	
    }
    
    private class Pedestal {
	Item item;
	int amount;
	int timeStamp;

	Pedestal(Item item, int amount) {
	    this.item = item;
	    this.amount = amount;
	    this.timeStamp = ZaphUtil.getSecondsTimeStamp();
	}

    }
}
