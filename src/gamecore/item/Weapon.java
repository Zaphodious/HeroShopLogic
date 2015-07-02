package gamecore.item;

import gamecore.Dice;
import gamecore.Reference;
import gamecore.adventure.CombatTag;
import gamecore.adventure.CombatUsable;
import gamecore.adventure.RoundInfoContainer;
import gamecore.entity.Attribute;
import gamecore.entity.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public class Weapon extends Item implements CombatUsable {

    private int durability;
    private int salt;
    private Attribute attackUsing;
    private List<CombatTag> combatTags;

    public Weapon(String name, int power, int durability, int salt, Attribute attackUsing) {
	super(name, 1, 1, false, ItemType.WEAPON, power);
	this.durability = durability;
	this.salt = (salt == 0) ? Dice.SALT.roll() : salt;
	this.combatUsable = true;
	this.attackUsing = attackUsing;
	this.combatTags = new ArrayList<CombatTag>();
	this.combatTags.add(CombatTag.HARMS_OPPONENT);
    }

    public Weapon(String name, int power, int durability, Attribute attackUsing) {
	this(name, power, durability, 0, attackUsing);
    }

    public int getDurability() {
	return durability;
    }

    @Override
    public int hashCode() {
	return super.hashCode() + salt;
    }
    

    public Attribute getAttackUsing() {
        return attackUsing;
    }

    @Override
    public int use(Entity user, Entity target) {
	return target.hurt(new RoundInfoContainer(user, target, this, potency, this.attackUsing));
    }



    @Override
    public String toString() {
	return name + ": " + "power=" + potency + ", durability=" + durability;
    }

    @Override
    public int amountLeft() {
	// TODO Auto-generated method stub
	return durability;
    }

    @Override
    public boolean hasCombatTag(CombatTag tag) {
	// TODO Auto-generated method stub
	return this.combatTags.contains(tag);
    }





    

    
}
