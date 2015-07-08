package gamecore.item;

import gamecore.entity.Attribute;
import gamecore.entity.Entity;

public enum BasicPotionType implements ItemSubtype {

    HEALTH() {
	@Override
	public int activate(Item item, Entity user, Entity target) {
	    Potion potion = (Potion) item;
	    target.heal(potion.getPotency());
	    return potion.potency;
	}

	
    };

    public abstract int activate(Item item, Entity user, Entity target);
    
    @Override
	public Attribute getRelevantAttribute() {
	    return null;
	}

}
