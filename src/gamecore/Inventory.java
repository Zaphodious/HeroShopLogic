package gamecore;

import gamecore.item.Item;
import gamecore.item.ItemType;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<Integer, Item> storage;

    public Inventory() {
	storage = new HashMap<Integer, Item>();
    }

    public boolean addItem(Item item) {
	if (storage.containsKey(item.hashCode()) && item.isStackable()) {
	    storage.get(item.hashCode()).addToStack(item.getStackSize());
	    ;
	} else {
	    storage.put(item.hashCode(), item);
	}
	return true;
    }

    public void addItems(Item... items) {
	for (Item item : items) {
	    this.addItem(item);
	}
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

    public boolean removeItem(Item item) {
	if (storage.containsKey(item.hashCode())) {
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
}
