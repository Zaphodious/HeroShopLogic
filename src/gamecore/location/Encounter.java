package gamecore.location;

import gamecore.Inventory;
import gamecore.entity.Entity;

public interface Encounter {

    public char getSymbol();
    
    public boolean canBePickedUp();
    
    public boolean isHostileToPlayer();
    
    public Entity[] getEntities();
    
    public Inventory getInventory();
}
