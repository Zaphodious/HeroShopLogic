package gamecore;

public enum ItemType {

    FOOD("food"),
    WEAPON("weapon"),
    ARMOR("armor"),
    MATERIAL("material");
    
    
    
    private String name;
    
    ItemType(String name) {
	this.setName(name);
    }

    public String getName() {
	return name;
    }

    private void setName(String name) {
	this.name = name;
    }
    
    
    
}
