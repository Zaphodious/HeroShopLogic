package gamecore.item;

import gamecore.Inventory;
import gamecore.entity.Entity;
import gamecore.location.Encounter;

public class Chest implements Encounter {
    
    Inventory contents;
    

    @Override
    public char getSymbol() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public boolean canBePickedUp() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean isHostileToPlayer() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public Entity[] getEntities() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Inventory getInventory() {
	// TODO Auto-generated method stub
	return contents;
    }

    

}
