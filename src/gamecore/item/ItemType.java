package gamecore.item;

public enum ItemType {

    FOOD('%',"food"),
    WEAPON('^',"weapon"),
    ARMOR('>',"armor"),
    MATERIAL('~',"material");
    
    
    
    private char symbol;
    private String name;
    
    ItemType(char symbol, String name) {
	this.name = name;
	this.symbol = symbol;
    }

    public String getName() {
	return name;
    }

    public char getSymbol() {
        return symbol;
    }

    
    
    
}
