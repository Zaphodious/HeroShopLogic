package gamecore.item;

public enum ItemType {

    FOOD("food"),
    WEAPON("weapon"),
    ARMOR("armor"),
    MATERIAL("material"),
    POTION_HEALTH("health potion");
    
    
    
    private String name;
    
    ItemType(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }


    
    
    
}
