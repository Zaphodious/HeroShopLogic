package gamecore;

import gamecore.item.Item;
import gamecore.item.ItemType;

import java.util.List;
import java.util.Map;

public class Inventory {

    private Map<Item, Integer> contents;

    long maxWeight;
    long currentWeight;

    public Inventory(long maxWeight) {
	contents = ZaphUtil.newMap();
	this.maxWeight = maxWeight;
    }

    public Inventory() {
	this(25);
    }

    public Inventory(Item... items) {
	contents = ZaphUtil.newMap();
	for (Item item : items) {
	    this.currentWeight += item.getWeight();
	    this.addItem(item);
	}
    }

    public boolean addItem(Item item, int amount) {
	int previousAmount = 0;
	if ((item.getWeight() * amount) + this.currentWeight > this.maxWeight) {
	    return false;
	}
	if (contents.containsKey(item) && item.isStackable()) {

	    previousAmount = contents.get(item);
	}
	contents.put(item, amount + previousAmount);

	this.currentWeight += item.getWeight();
	return true;
    }

    public boolean addItem(Item item) {
	return this.addItem(item, 1);
    }

    public Item[] getItems(ItemType type) {
	Item[] toReturn = new Item[this.numberOfItems(type)];
	int counter = 0;
	for (Item item : contents.keySet()) {
	    if (item.getType() == type) {
		toReturn[counter] = item;
		counter++;
	    }

	}
	return toReturn;
    }

    public Map<Item, Integer> getItemMap() {
	Map<Item, Integer> toReturn = ZaphUtil.newMap();

	for (Item item : this.contents.keySet()) {
	    toReturn.put(item, contents.get(item));
	}

	return toReturn;
    }

    public Item[] getItems() {
	Item[] toReturn = new Item[this.numberOfItems()];
	int counter = 0;
	for (Item item : contents.keySet()) {
	    toReturn[counter] = item;
	    counter++;
	}
	return toReturn;
    }

    public int numberOfItems() {
	return contents.size();
    }

    public int numberOfItems(ItemType type) {
	int toReturn = 0;
	for (Item item : contents.keySet()) {
	    if (item.getType() == type) {
		toReturn++;
	    }
	}
	return toReturn;
    }
    
    public int getItemQuantity(Item item) {
	
	return (this.contents.containsKey(item)) ? this.contents.get(item) : 0;
    }

    public int removeItem(Item item, int amount) {
	int numberItemsRemoved = 0;
	for (int i = 0; i < amount; i--) {
	    numberItemsRemoved += (this.removeItem(item)) ? 1 : 0;
	}
	return numberItemsRemoved;
    }

    public boolean removeItem(Item item) {

	if (this.contents.containsKey(item)) {

	    int oldValue = contents.get(item);
	    contents.put(item, oldValue - 1);
	    this.currentWeight -= item.getWeight();
	    contents.remove(item);

	}
	return false;

    }

    public boolean addOtherInventory(Inventory otherInventory) {

	if (this.currentWeight + otherInventory.currentWeight > this.getMaxWeight()) {
	    return false;
	}

	for (Item item : otherInventory.contents.keySet()) {
	    this.addItem(item, otherInventory.contents.get(item));
	}
	return true;
    }

    public boolean moveInventoryToThis(Inventory otherInventory) {
	if (!this.addOtherInventory(otherInventory)) {
	    List<Item> toDelete = ZaphUtil.newList();
	    for (Item item : otherInventory.contents.keySet()) {
		if (this.addItem(item, otherInventory.contents.get(item))) {
		    toDelete.add(item);
		}

	    }

	    for (Item item : toDelete) {
		otherInventory.contents.remove(item);
	    }
	    return false;
	}
	otherInventory.contents.clear();
	return true;
    }

    public long getMaxWeight() {
	return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
	this.maxWeight = maxWeight;
    }

    public long getCurrentWeight() {
	return currentWeight;
    }
    
    public int getCurrentValue() {
	int toReturn = 0;
	for (Item item : this.contents.keySet()) {
	    toReturn += item.getSaleValue() * this.contents.get(item);
	}
	return toReturn;
    }

    @Override
    public String toString() {
	StringBuilder toReturn = new StringBuilder();
	toReturn.append("maxWeight=" + maxWeight + "\n");
	toReturn.append("currentWeight=" + currentWeight + "\n");

	for (Item item : contents.keySet()) {
	    toReturn.append(item.toString() + "\n");
	}

	return toReturn.toString();
    }

}
