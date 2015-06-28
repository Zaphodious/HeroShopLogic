package gamecore.location;

import gamecore.Scene;

public interface Encounter {

    public char getSymbol();
    
    public boolean canBePickedUp();
    
    public Scene getEvent();
}
