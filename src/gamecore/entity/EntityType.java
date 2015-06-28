package gamecore.entity;

public enum EntityType {

    PLAYER_CHARACTER('@', "pc"),
    SIDEKICK('$', "sidekick"),
    GOBLIN('g', "goblin");
    
    private char symbol;
    private String name;
    
    EntityType(char symbol, String name) {
	this.symbol = symbol;
	this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
    
    
}
