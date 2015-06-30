package gamecore.item;

import gamecore.Reference;
import gamecore.adventure.CombatUsable;
import gamecore.entity.Attribute;
import gamecore.entity.Entity;

public class HealthPotion extends Item implements CombatUsable {

    boolean isOffensive;
    
    public HealthPotion(String name, int stackSize, int potency) {
	super(name, stackSize, Reference.DEFAULT_MAX_STACK_SIZE, true, ItemType.POTION_HEALTH, potency, 1);
	this.combatUsable = true;
	this.isOffensive = false;
    }

    @Override
    public int use(Entity entity) {
	entity.addAttribute(Attribute.CURRENT_HEALTH, entity.getAttribute(Attribute.CURRENT_HEALTH)+this.potency);
	this.addToStack(-1);
	return potency;
    }

    @Override
    public int amountLeft() {
	// TODO Auto-generated method stub
	return this.getStackSize();
    }

    public boolean isOffensive() {
        return isOffensive;
    }
    
    

}
