package gamecore.shop;

import gamecore.Inventory;

public final class Storefront {

    private static Storefront singletonInstance;

    private DisplayCase showcase;
    private Inventory backroom;

    private Storefront() {
	this.showcase = new DisplayCase();
	this.backroom = new Inventory(100);
    }

    public boolean acceptItems(Inventory inventory) {
	return backroom.moveInventoryToThis(inventory);
    }

    public Inventory getBackroom() {
	return backroom;
    }
    
    public boolean hasAnythingSold() {
	return this.showcase.runSaleCycles();
    }
    
    public Inventory getItemsOnSale() {
	return this.showcase.getStuffOnSale();
    }
    
    public Inventory getSoldItems() {
	return this.showcase.getStuffSoldSinceLastCheck();
    }
    
    public void transferBackroomToShowcase() {
	showcase.addInventory(backroom);
	backroom = new Inventory(100);
    }

    /**
     * There is only ever one Storefront. This method gets it.
     * 
     * @return The singleton instance of Storefront.
     */
    public static Storefront getInstance() {
	if (singletonInstance == null) {
	    singletonInstance = new Storefront();
	}
	return singletonInstance;
    }

}
