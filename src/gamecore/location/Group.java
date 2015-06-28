package gamecore.location;

import gamecore.Inventory;
import gamecore.Scene;
import gamecore.entity.Entity;
import gamecore.item.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public class Group implements Encounter {

    private Map<Integer, Entity> entities;
    private Inventory toDrop;

    public Group() {
	this.entities = new HashMap<Integer, Entity>();

    }

    public void addEntity(Entity... entities) {
	for (Entity entity : entities) {
	    this.addEntity(entity);
	}
    }

    public void addEntity(Entity entity) {
	if (entities.containsKey(entity.hashCode())) {
	    entities.put(entity.hashCode(), entity);
	}
    }

    public Entity[] getEntities() {
	Entity[] toReturn = new Entity[this.entities.size()];
	int counter = 0;
	for (Entity entity : entities.values()) {
	    toReturn[counter] = entity;
	    counter++;
	}
	return toReturn;
    }

    @Override
    public char getSymbol() {
	return this.getEntities()[0].getSymbol();
    }

    public void pickUp(Encounter mapObject) {
	if (mapObject instanceof Item) {
	    this.getEntities()[0].getInventory().addItem((Item) mapObject);
	}

    }

    @Override
    public boolean canBePickedUp() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean isHostileToPlayer() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public Inventory getInventory() {
	return toDrop;
    }



}
