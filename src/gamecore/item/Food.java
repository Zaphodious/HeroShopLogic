package gamecore.item;

import gamecore.Reference;
import gamecore.entity.Entity;

public class Food extends Item {

    
    public Food(String name) {
	super(name, ItemType.FOOD);
    }

    public Food(String name, int quantity) {
	super(name, quantity, ItemType.FOOD);
    }
    
    public Food(String name, int quantity, int potency) {
	super(name, quantity, Reference.DEFAULT_MAX_STACK_SIZE, true, ItemType.FOOD, potency);
    }
    

    @Override
    public int use(Entity entity) {
	entity.feed(this.getPotency());
	return potency;
    }
    
    @Override
    public String toString() {
	return name + ": , stackSize=" + stackSize + ", maxStackSize=" + maxStackSize + "potency=" + potency;
    }

}
