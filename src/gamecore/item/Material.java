package gamecore.item;

/**
 * This class is used for items used only for crafting/selling. It provides no
 * functionality other then that specified in the Item interface.
 * 
 * @author Alex Chythlook
 *
 */
public final class Material implements Item {

    private String ID;
    private String name;
    private ItemType type;
    private int potency;
    private int weight;
    private boolean isStackable;
    protected int saleValue;
    protected int saleDifficulty;
    protected Rarity rarity;

    public Material(ItemBuilder builder) {
	this.name = builder.getName();
	this.type = builder.getType();
	this.potency = builder.getPotency();
	this.weight = builder.getWeight();
	this.isStackable = builder.isStackable();
	this.saleValue = builder.getSaleValue();
	this.saleDifficulty = builder.getSaleDifficulty();
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public ItemType getType() {
	return type;
    }

    @Override
    public int getPotency() {
	return potency;
    }

    @Override
    public int getWeight() {
	return weight;
    }

    @Override
    public boolean isStackable() {
	return isStackable;
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

    @Override
    public String toString() {
	return "Material [name=" + name + "]";
    }

    @Override
    public String getID() {
	return this.ID;
    }

    @Override
    public int hashCode() {
	return this.ID.hashCode();
    }
    
    

}
