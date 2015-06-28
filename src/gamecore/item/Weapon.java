package gamecore.item;

import gamecore.Reference;
import gamecore.entity.Entity;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public class Weapon extends Item {

    private int durability;
    public int salt;

    public Weapon(String name, int power, int durability, int salt) {
	super(name, 1, 1, false, ItemType.WEAPON, power);
	this.durability = durability;
	this.salt = (salt == 0) ? Reference.rand.nextInt() : salt;
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
    
    private boolean sliceNDice(Entity entity, int damageToDeal) {
	return (entity.hurt(damageToDeal) == 0) ? false:true;
    }

    @Override
    public boolean use(Entity entity) {
	return this.sliceNDice(entity, this.getPotency());
    }
    
    @Override
    public void use(Entity... entities) {
	int damageToDeal = this.getPotency() / entities.length;
	for (Entity entity : entities){
	    this.sliceNDice(entity, damageToDeal);
	}
    }

    @Override
    public String toString() {
	return name + ": " + "power=" + potency + ", durability=" + durability;
    }
}
