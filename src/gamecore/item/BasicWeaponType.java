package gamecore.item;

import gamecore.Dice;
import gamecore.Reference;
import gamecore.adventure.MessageType;
import gamecore.adventure.UseMessage;
import gamecore.entity.Attribute;

public enum BasicWeaponType implements ItemSubtype {

    SWORD(Attribute.ATK_STRENGTH),
    BATTLE_AXE(Attribute.ATK_STRENGTH) {
	@Override
	public UseMessage activate(UseEvent useEvent) {
	    if (BasicWeaponType.doesThisHit(useEvent, this.attackUsing)) {
		Weapon weapon = (Weapon) useEvent.getItemUsed();
		int damageDone = 0;
		damageDone += useEvent.getTarget().hurt(useEvent.getUser(), weapon, (Dice.D12.roll() + weapon.potency), Reference.GET_INVERSE_ATTRIBUTE(attackUsing));

		return UseMessage.newInstance(MessageType.OPPONENT_DAMAGE_TAKEN, "Ka-Thunk! " + useEvent.getUser().getName() + "'s Battle Axe hit the " + useEvent.getTarget().getName() + " for " + damageDone);

	    }
	    return UseMessage.newInstance(MessageType.ATTACK_MISS, useEvent.getUser().getName() + " missed " + useEvent.getTarget().getName());

	}
    },
    FLAIL(Attribute.ATK_STRENGTH) {
	@Override
	public UseMessage activate(UseEvent useEvent) {
	    if (BasicWeaponType.doesThisHit(useEvent, this.attackUsing)) {
		Weapon weapon = (Weapon) useEvent.getItemUsed();
		int damageDone = 0;
		int timesHit = Dice.D6.roll();
		for (int i = 0; i < timesHit; i++) {
		    damageDone += useEvent.getTarget().hurt(useEvent.getUser(), weapon, Dice.D3.roll() + weapon.potency, Reference.GET_INVERSE_ATTRIBUTE(attackUsing));
		}
		return UseMessage.newInstance(MessageType.OPPONENT_DAMAGE_TAKEN, "Whap, whap, whap! " + useEvent.getUser().getName() + "'s flail hit the " + useEvent.getTarget().getName() + " for " + damageDone);

	    }
	    return UseMessage.newInstance(MessageType.ATTACK_MISS, useEvent.getUser().getName() + " missed " + useEvent.getTarget().getName());

	}
    },
    BOW(Attribute.ATK_DEXTERITY) {
	@Override
	public UseMessage activate(UseEvent useEvent) {
	    if (BasicWeaponType.doesThisHit(useEvent, this.attackUsing)) {
		Weapon weapon = (Weapon) useEvent.getItemUsed();
		int damageDone = useEvent.getTarget().hurt(useEvent.getUser(), weapon, Dice.D2.roll() + Dice.D2.roll() + Dice.D2.roll() + weapon.potency, Reference.GET_INVERSE_ATTRIBUTE(attackUsing));
		return UseMessage.newInstance(MessageType.OPPONENT_DAMAGE_TAKEN, "Thwang! " + useEvent.getUser().getName() + " hit " + useEvent.getTarget().getName() + " with an arrow for " + damageDone);
	    }
	    return UseMessage.newInstance(MessageType.ATTACK_MISS, useEvent.getUser().getName() + " missed " + useEvent.getTarget().getName());

	}
    },
    SHIV(Attribute.ATK_DEXTERITY) {
	@Override
	public UseMessage activate(UseEvent useEvent) {
	    if (BasicWeaponType.doesThisHit(useEvent, this.attackUsing)) {
		Weapon weapon = (Weapon) useEvent.getItemUsed();
		int damageDone = useEvent.getTarget().hurt(useEvent.getUser(), weapon, Dice.D2.roll() + weapon.potency, Reference.GET_INVERSE_ATTRIBUTE(attackUsing));
		return UseMessage.newInstance(MessageType.OPPONENT_DAMAGE_TAKEN, "Thwang! " + useEvent.getUser().getName() + " hit " + useEvent.getTarget().getName() + " with an arrow for " + damageDone);
	    }
	    return UseMessage.newInstance(MessageType.ATTACK_MISS, useEvent.getUser().getName() + " missed " + useEvent.getTarget().getName());

	}
    };

    protected Attribute attackUsing;

    private BasicWeaponType(Attribute attackUsing) {
	this.attackUsing = attackUsing;
    }

    @Override
    public UseMessage activate(UseEvent useEvent) {
	if (BasicWeaponType.doesThisHit(useEvent, this.attackUsing)) {
	    Weapon weapon = (Weapon) useEvent.getItemUsed();
	    int damageDone = useEvent.getTarget().hurt(useEvent.getUser(), weapon, Dice.D6.roll() + weapon.potency, Reference.GET_INVERSE_ATTRIBUTE(attackUsing));
	    return UseMessage.newInstance(MessageType.OPPONENT_DAMAGE_TAKEN, "Pow! " + useEvent.getUser().getName() + " hit " + useEvent.getTarget().getName() + " for " + damageDone);
	}
	return UseMessage.newInstance(MessageType.ATTACK_MISS, useEvent.getUser().getName() + " missed " + useEvent.getTarget().getName());

    }

    @Override
    public Attribute getRelevantAttribute() {
	return this.attackUsing;
    }

    private static boolean doesThisHit(UseEvent event, Attribute attackUsing) {
	int toCheckAgainst = event.getTarget().getAttribute((attackUsing)) + 5;
	int roll = Dice.D10.roll() + event.getUser().getAttribute(attackUsing);
	return (roll > toCheckAgainst);
    }

    
    String name;
    
    BasicWeaponType(String name) {
	this.name = name;
    }

    
    
    
}
