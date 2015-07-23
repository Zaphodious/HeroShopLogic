package gamecore;

import gamecore.item.Item;

import java.util.Map;

public final class DBConversionHelper {
    
    private static DBConversionHelper instance = null;

    private Map<String,Item> itemRegister;
    
    private Map<String,Item> entityRegister;
    
    private DBConversionHelper () {
	this.itemRegister = ZaphUtil.newMap();
	this.entityRegister = ZaphUtil.newMap();
	
    }
    
    /**
     * Adds an item to the item register.
     * @param item The item to be added.
     * @return The String ID for the item that was added.
     */
    public String addItem(Item item) {
	this.itemRegister.put(item.getID(), item);
	return item.getID();
    }
    
    
    public Item getItemFromID(String ID) {
	return itemRegister.get(ID);
    }
    
    
    /**
     * Use <i>only</i> to prepare for app death. After use, the registers will need to be repopulated.
     */
    public void deflate() {
	this.itemRegister.clear();
	this.entityRegister.clear();
    }
    
    public static final DBConversionHelper getInstance() {
	if (instance == null) {
	    instance = new DBConversionHelper();
	}
	
	return instance;
    }
    
    /**
     * 
     * Debug-only method, prints a list of all the IDs currently in the item reg.
     */
    public void printItemIDs() {
	System.out.println("Printing item IDs");
	for (String id : itemRegister.keySet()) {
	    System.out.println(id);
	}
    }
    
}
