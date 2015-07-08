package gamecore.item;

import gamecore.Dice;
import gamecore.adventure.RoundInfoContainer;
import gamecore.entity.Attribute;
import gamecore.entity.Entity;

public enum BasicWeaponType implements ItemSubtype {

    SWORD(Attribute.ATK_STRENGTH) {
	@Override
	public int activate(Item item, Entity user, Entity target) {
	    Weapon weapon = (Weapon) item;
	    return target.hurt(new RoundInfoContainer(user, target, weapon, weapon.getPotency(), attackUsing));
	}
    },
    BATTLE_AXE(Attribute.ATK_STRENGTH) {
	@Override
	public int activate(Item item, Entity user, Entity target) {
	    Weapon weapon = (Weapon) item;
	    return target.hurt(new RoundInfoContainer(user, target, weapon, weapon.getPotency() * 2, attackUsing));
	}
    },
    FLAIL(Attribute.ATK_STRENGTH) {
	@Override
	public int activate(Item item, Entity user, Entity target) {
	    Weapon weapon = (Weapon) item;
	    int toReturn = 0;
	    for (int i = 0; i < Dice.D6.roll(); i++) {
		toReturn += target.hurt(new RoundInfoContainer(user, target, weapon, weapon.getPotency() / 3, attackUsing));
	    }
	    return toReturn;
	}
    },
    BOW(Attribute.ATK_DEXTERITY) {
	@Override
	public int activate(Item item, Entity user, Entity target) {
	    Weapon weapon = (Weapon) item;
	    return target.hurt(new RoundInfoContainer(user, target, weapon, weapon.getPotency(), attackUsing));
	}
    },
    SHIV(Attribute.ATK_DEXTERITY) {
	@Override
	public int activate(Item item, Entity user, Entity target) {
	    Weapon weapon = (Weapon) item;
	    return target.hurt(new RoundInfoContainer(user, target, weapon, weapon.getPotency(), attackUsing));
	}
    };

    Attribute attackUsing;

    private BasicWeaponType(Attribute attackUsing) {
	this.attackUsing = attackUsing;
    }

    @Override
    public abstract int activate(Item item, Entity user, Entity target);

    public Attribute getRelevantAttribute() {
	return this.attackUsing;
    }

}
