package gamecore.item;

import gamecore.Reference;
import gamecore.adventure.CombatTag;
import gamecore.entity.Entity;

import java.util.List;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public abstract class Item implements Cloneable {

    protected String name;
    protected ItemType type;

    protected int stackSize;
    protected int maxStackSize;
    protected int potency;
    protected int weight;
    
    protected boolean stackable;
    protected boolean combatUsable;

    protected Item(String name, ItemType type) {
	this(name, 1, Reference.DEFAULT_MAX_STACK_SIZE, true, type, 3);
    }

    protected Item(String name, int stackSize, ItemType type) {
	this(name, stackSize, Reference.DEFAULT_MAX_STACK_SIZE, true, type, 3);
    }

    protected Item(String name, int stackSize, int maxStackSize, boolean stackable, ItemType type, int potency) {
	this(name, stackSize, Reference.DEFAULT_MAX_STACK_SIZE, true, type, potency, 2);
    }

    protected Item(String name, int stackSize, int maxStackSize, boolean stackable, ItemType type, int potency, int weight) {
	this.name = name;
	this.stackSize = stackSize;
	this.maxStackSize = maxStackSize;
	this.stackable = stackable;
	this.type = type;
	this.potency = potency;
	this.weight = weight;
    }

    public boolean isStackable() {
	return stackable;
    }

    public int getStackSize() {
	return stackSize;
    }

    public void addToStack(int value) {
	stackSize += value;
	this.weight += (weight*value);
    }

    public void addToStack() {
	addToStack(1);
    }

    public String getName() {
	return name;
    }

    public ItemType getType() {
	return this.type;
    }

    public int use(Entity entity) {
	return 0;
    }

    public int getPotency() {
	return potency;
    }

    protected void setPotency(int potency) {
	this.potency = potency;
    }

    @Override
    public int hashCode() {

	return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
	return this.hashCode() == o.hashCode();
    }

    @Override
    public String toString() {
	return name + ": , stackSize=" + stackSize + ", maxStackSize=" + maxStackSize;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    

    public boolean isCombatUsable() {
        return combatUsable;
    }
    
    public boolean hasCombatTag(CombatTag tag) {
	return false;
    }

    public void setCombatUsable(boolean combatUsable) {
        this.combatUsable = combatUsable;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
	return super.clone();
    }

    
    
}
