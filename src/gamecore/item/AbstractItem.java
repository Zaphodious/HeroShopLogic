package gamecore.item;

/**
 * This class provides basic implementation of the methods in the Item
 * interface, primarily so that classes that wish to implement it don't have to
 * write all of the implementation from scratch.
 * 
 * @author Alex Chythlook
 *
 */

public abstract class AbstractItem implements Item {

    protected String name;
    protected ItemType type;

    protected int potency;
    protected int weight;

    protected boolean stackable;
    protected boolean combatUsable;

    protected int saleValue;
    protected int saleDifficulty;

    protected Rarity rarity;

    protected AbstractItem(ItemBuilder builder) {
	this.name = builder.getName();
	this.stackable = builder.isStackable();
	this.potency = builder.getPotency();
	this.weight = builder.getWeight();
	this.type = builder.getType();
    }

    @Override
    public boolean isStackable() {
	return this.stackable;
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public ItemType getType() {
	return this.type;
    }

    @Override
    public int getPotency() {
	return potency;
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
	return name;
    }

    @Override
    public int getWeight() {
	return weight;
    }

    @Override
    public int getSaleValue() {
	return saleValue;
    }

    @Override
    public Rarity getRarity() {
	return rarity;
    }

    @Override
    public int getSaleDifficulty() {
	return saleDifficulty;
    }

}
