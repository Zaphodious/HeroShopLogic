package gamecore;

import gamecore.item.Item;
import gamecore.item.ItemType;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<Integer, Item> storage;

    int maxWeight;
    int currentWeight;

    public Inventory(int maxWeight) {
	storage = new HashMap<Integer, Item>();
	this.maxWeight = maxWeight;
    }
    
    public Inventory(Item... items) {
	this();
	int weight = 0;
	for (Item item:items) {
	    weight += item.getWeight();
	}
	weight *= 3;
	this.setMaxWeight(weight);
	this.addItems(items);
	
    }

    public Inventory() {
	this(25);
    }

    public boolean addItem(Item item) {

	if (item.getWeight() + this.currentWeight > this.maxWeight) {
	    return false;
	}
	if (storage.containsKey(item.hashCode()) && item.isStackable()) {
	    storage.get(item.hashCode()).addToStack(item.getStackSize());

	    ;
	} else {
	    storage.put(item.hashCode(), item);
	}
	this.currentWeight += item.getWeight();
	return true;
    }

    public void addItems(Item... items) {
	for (Item item : items) {
	    this.addItem(item);
	}
    }

    public Item[] getItems(ItemType type) {
	Item[] toReturn = new Item[this.numberOfItems(type)];
	int counter = 0;
	for (Item item : storage.values()) {
	    if (item.getType() == type) {
		toReturn[counter] = item;
		counter++;
	    }

	}
	return toReturn;
    }

    public Item[] getItems() {
	Item[] toReturn = new Item[this.numberOfItems()];
	int counter = 0;
	for (Item item : storage.values()) {
	    toReturn[counter] = item;
	    counter++;
	}
	return toReturn;
    }

    public int numberOfItems() {
	return storage.size();
    }

    public int numberOfItems(ItemType type) {
	int toReturn = 0;
	for (Item item : storage.values()) {
	    if (item.getType() == type) {
		toReturn++;
	    }
	}
	return toReturn;
    }

    public boolean removeItem(Item item) {
	if (storage.containsKey(item.hashCode())) {
	    this.currentWeight -= item.getWeight();
	    storage.remove(item.hashCode());
	    return true;
	} else {
	    return false;
	}
    }

    public void removeItems(Item... items) {
	for (Item item : items) {
	    this.removeItem(item);
	}
    }

    public void addOtherInventory(Inventory otherInventory) {
	this.addItems(otherInventory.getItems());
    }

    public int getMaxWeight() {
	return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
	this.maxWeight = maxWeight;
    }

    public int getCurrentWeight() {
	return currentWeight;
    }

    @Override
    public String toString() {
	StringBuilder toReturn = new StringBuilder();
	toReturn.append("maxWeight=" + maxWeight + "\n");
	toReturn.append("currentWeight=" + currentWeight + "\n");
	
	for (Item item:storage.values()) {
	    toReturn.append(item.toString() + "\n");
	}
	
	return toReturn.toString();
    }
    
    
}
