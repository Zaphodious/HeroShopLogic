package gamecore.location;

public class Icon {

    char symbol;
    Encounter mapObject;
    
    public Icon(Encounter mapObject, char symbol) {
	this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
    
    
    public Encounter getEncounter() {
	return mapObject;
    }
    
    
}
