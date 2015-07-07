package gamecore.item;

import gamecore.entity.Entity;

public enum BasicPotionType implements PotionType {

    HEALTH() {
	@Override
	public int applyEffect(Potion potion, Entity user, Entity target) {
	    target.heal(potion.getPotency());
	    return potion.potency;
	}
    };

    public abstract int applyEffect(Potion potion, Entity user, Entity target);

}
