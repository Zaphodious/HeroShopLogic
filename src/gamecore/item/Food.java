package gamecore.item;

import gamecore.adventure.MessageType;
import gamecore.adventure.UseMessage;
import gamecore.entity.Entity;

public class Food extends AbstractItem implements Usable {

    protected Food(ItemBuilder builder) {
	super(builder);
	// TODO Auto-generated constructor stub
    }

    /*
     * public Food(String name) { super(name, ItemType.FOOD); }
     * 
     * public Food(String name, int quantity) { super(name, quantity,
     * ItemType.FOOD); }
     * 
     * public Food(String name, int quantity, int potency) { super(name,
     * quantity, Reference.DEFAULT_MAX_STACK_SIZE, true, ItemType.FOOD,
     * potency); }
     */

    @Override
    public String toString() {
	return name + ": , stackSize=" + "potency=" + potency;
    }

    @Override
    public UseMessage use(Entity user, Entity target) {
	target.feed(potency);
	return UseMessage.newInstance(MessageType.PLAYER_FEED, "You eat, gaining " + potency + " more energy.");
    }

    @Override
    public boolean hasUseTag(UseTag useTag) {
	// TODO Auto-generated method stub
	return false;
    }

}
