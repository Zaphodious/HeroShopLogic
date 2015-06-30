package gamecore.item;

import gamecore.Reference;
import gamecore.adventure.CombatUsable;
import gamecore.entity.Entity;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public class Weapon extends Item implements CombatUsable {

    private int durability;
    public int salt;
    public boolean isOffensive;

    public Weapon(String name, int power, int durability, int salt) {
	super(name, 1, 1, false, ItemType.WEAPON, power);
	this.durability = durability;
	this.salt = (salt == 0) ? Reference.rand.nextInt() : salt;
	this.combatUsable = true;
	this.isOffensive = true;
    }

    public Weapon(String name, int power, int durability) {
	this(name, power, durability, 0);
    }

    public int getDurability() {
	return durability;
    }

    @Override
    public int hashCode() {
	return super.hashCode() + salt;
    }

    @Override
    public int use(Entity entity) {
	return entity.hurt(this.potency);
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
    public boolean isOffensive() {
	return isOffensive;
    }
}
