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
	stuffSold = new Inventory(999999999999999999L);
	checkInterval = 10;
	plusChanceInterval = 30;
    }

    void addItem(Item item, int amount) {
	this.CaseContents.add(new Pedestal(item, amount));
    }

    void addInventory(Inventory inventory) {
	Map<Item, Integer> inventoryContents = inventory.getItemMap();

	for (Item item : inventoryContents.keySet()) {
	    this.addItem(item, inventoryContents.get(item));
	}
    }

    Inventory getStuffSoldSinceLastCheck() {
	Inventory toReturn = stuffSold;
	stuffSold = new Inventory();
	return toReturn;
    }

    Inventory getStuffOnSale() {
	Inventory toReturn = new Inventory(999999999999999999L);
	for (Pedestal ped : this.CaseContents) {
	    toReturn.addItem(ped.item, ped.amount);
	}
	return toReturn;
    }
    


    boolean runSaleCycles() {
	boolean hasSoldAnything = false;
	List<Pedestal> toRemove = ZaphUtil.newList();
	for (Pedestal ped : CaseContents) {
	    int secondsPassed = ZaphUtil.secondsPassed(ped.timeStamp);
	    int timesToCheck = secondsPassed / checkInterval;

	    if (timesToCheck > 0) {
		int totalBonus = secondsPassed / plusChanceInterval;
		int realChance = ped.item.getSaleDifficulty() / (((ped.amount/5)) + totalBonus + 1);
		for (int i = 0; i < timesToCheck; i++) {
		    if (realChance > Dice.D100.roll()) {
			hasSoldAnything = true;
			stuffSold.addItem(ped.item, ped.amount);
			toRemove.add(ped);
			break;
		    }
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
