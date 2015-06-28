package gamecore;

import gamecore.entity.Entity;
import gamecore.location.Group;

public abstract class Scene {
    
    boolean isCombat;
    protected Group party;
    protected Group enemies;
    

    
    public Scene(boolean isCombat, Group party, Group enemies) {
	this.isCombat = isCombat;
	this.party = party;
	this.enemies = enemies;
    }

    public abstract void moveForward();

    public abstract boolean isOver();
    
    public abstract boolean isCombat();
    
    public abstract int getRound();
    
    public Entity[] getAllEntities() {
	
	return null;
    }
    
    
}
