package gamecore;

import gamecore.entity.Entity;
import gamecore.location.Group;

public abstract class Scene {

    boolean isCombat;
    protected Group party;
    protected Group enemies;
    protected int round;

    public Scene(boolean isCombat, Group party, Group enemies) {
	this.isCombat = isCombat;
	this.party = party;
	this.enemies = enemies;
	this.round = 1;
    }

    public abstract void moveForward();

    public abstract boolean isOver();

    public boolean isCombat() {
	return isCombat;
    }

    public int getRound() {
	return round;
    }

    public Entity[] getParty() {
	return party.getEntities();
    }

    public Entity[] getEnemies() {
	return enemies.getEntities();
    }

    public Entity[] getAllEntities() {
	return Reference.concat(party.getEntities(), enemies.getEntities());
    }

}
