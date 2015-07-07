package gamecore.shop;

import gamecore.Inventory;

public final class Storefront {

    private static Storefront singletonInstance;
    
    private Inventory showroom;
    private Inventory backroom;
    
    private Storefront() {
	this.showroom = new Inventory(1000);
	this.backroom = new Inventory(100);
    }
    
    public boolean acceptItems(Inventory inventory) {
	return backroom.moveInventoryToThis(inventory);
    }
    
    
    
    public Inventory getBackroom() {
        return backroom;
    }

    /**
     * There is only ever one Storefront. This method gets it.
     * @return The singleton instance of Storefront.
     */
    public static Storefront getInstance() {
	if (singletonInstance == null) {
	    singletonInstance = new Storefront();
	}
	return singletonInstance;
    }
    
}
