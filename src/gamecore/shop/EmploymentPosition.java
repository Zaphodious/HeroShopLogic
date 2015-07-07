package gamecore.shop;

import gamecore.Dice;
import gamecore.Inventory;
import gamecore.ItemDrop;
import gamecore.ZaphUtil;
import gamecore.entity.Entity;
import gamecore.entity.EntityBuilder;
import gamecore.entity.EntityType;
import gamecore.item.BasicItemType;
import gamecore.item.Item;
import gamecore.item.ItemBuilder;

import java.util.List;
import java.util.Map;

public class EmploymentPosition {

    String title;

    Entity employee;

    List<ItemDrop> possibleResults;

    int timeStamp;

    int timeBetweenDrops;

    Inventory collected;

    public EmploymentPosition() {
	title = "worker";
	employee = new EntityBuilder("Karlos", EntityType.HERO).build();
	timeStamp = ZaphUtil.getSecondsTimeStamp();
	timeBetweenDrops = 20;
	possibleResults = ZaphUtil.newList();
	collected = new Inventory();

	possibleResults.add(new ItemDrop(new ItemBuilder("Test Item 100", BasicItemType.MATERIAL).setWeight(0).build(), 100));
	possibleResults.add(new ItemDrop(new ItemBuilder("Test Item 80", BasicItemType.MATERIAL).setWeight(0).build(), 80));
	possibleResults.add(new ItemDrop(new ItemBuilder("Test Item 40", BasicItemType.MATERIAL).setWeight(0).build(), 40));
	possibleResults.add(new ItemDrop(new ItemBuilder("Test Item 20", BasicItemType.MATERIAL).setWeight(0).build(), 20).setAmountToDrop(Dice.D3));

    }

    public boolean dropOff() {
	return Storefront.getInstance().acceptItems(this.collected);
    }
	

    private void cycleThroughPossibleResults() {
	for (int i = 0; i < this.howManyChances(); i++) {
	    for (ItemDrop possibleItem : possibleResults) {
		if (possibleItem.doesDrop()) {
		    collected.addItem(possibleItem.getItem(), possibleItem.getDropAmount());
		}
	    }
	}

    }

    public void addPossibleResult(Item item, int chanceToDrop) {
	this.possibleResults.add(new ItemDrop(item, chanceToDrop));
    }

    public void addPossibleResult(Item item, int chanceToDrop, Dice toRoll) {
	this.possibleResults.add(new ItemDrop(item, chanceToDrop).setAmountToDrop(toRoll));
    }

    public int howManyChances() {
	return ZaphUtil.secondsPassed(timeStamp) / timeBetweenDrops;
    }

    public int secondsUntilNextChance() {
	return this.timeBetweenDrops - (ZaphUtil.secondsPassed(timeStamp) % timeBetweenDrops);
    }

    private void resetChances() {
	this.timeStamp = ZaphUtil.getSecondsTimeStamp() - (ZaphUtil.secondsPassed(timeStamp) % timeBetweenDrops);
    }

    public int collectItems() {
	if (howManyChances() >= 1) {
	    this.cycleThroughPossibleResults();
	    int collectedItems = this.howManyChances();
	    resetChances();
	    
	    return collectedItems;
	}
	return 0;
    }

    public Map<Item,Integer> getItemsCollectedSoFar() {
	return this.collected.getItemMap();
    }

    public List<ItemDrop> getPossibleResults() {
	List<ItemDrop> toReturn = ZaphUtil.newList();
	toReturn.addAll(possibleResults);
	return toReturn;
    }

}
