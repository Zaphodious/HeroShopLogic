package gamecore.item;

import gamecore.entity.Entity;

public class UseEvent {
    Entity user;
    Entity target;
    Usable itemUsed;
    int numberUsed;
    private UseEvent(Entity user, Entity target, Usable itemUsed, int numberUsed) {
	super();
	this.user = user;
	this.target = target;
	this.itemUsed = itemUsed;
	this.numberUsed = numberUsed;
    }
    
    
    
    public Entity getUser() {
        return user;
    }



    public Entity getTarget() {
        return target;
    }



    public Usable getItemUsed() {
        return itemUsed;
    }



    public int getNumberUsed() {
        return numberUsed;
    }



    public static UseEvent newInstance(Entity user, Entity target, Usable itemUsed, int numberUsed) {
	return new UseEvent(user, target, itemUsed, numberUsed);
    }
}
