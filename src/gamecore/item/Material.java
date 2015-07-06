package gamecore.item;


/**
 * This class is used for items used only for crafting/selling. It provides no functionality other then that specified in the Item interface.
 * @author Alex Chythlook
 *
 */
public final class Material implements Item {
    
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
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public int getPotency() {
        return potency;
    }

    public int getWeight() {
        return weight;
    }

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


    


}
