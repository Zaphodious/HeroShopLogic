package gamecore.item;

import java.util.ArrayList;
import java.util.List;

import gamecore.Reference;
import gamecore.adventure.CombatTag;
import gamecore.adventure.CombatUsable;
import gamecore.entity.Attribute;
import gamecore.entity.Entity;

public class HealthPotion extends Item implements CombatUsable {

    private List<CombatTag> combatTags;
    
    public HealthPotion(String name, int stackSize, int potency) {
	super(name, stackSize, Reference.DEFAULT_MAX_STACK_SIZE, true, ItemType.POTION_HEALTH, potency, 1);
	this.combatUsable = true;
	this.combatTags = new ArrayList<CombatTag>();
	this.combatTags.add(CombatTag.HEALS_PLAYER);
	this.combatTags.add(CombatTag.USEABLE_FROM_INVENTORY);
    }

    @Override
    public int amountLeft() {
	// TODO Auto-generated method stub
	return this.getStackSize();
    }


    @Override
    public int use(Entity user, Entity target) {
	target.addAttribute(Attribute.CURRENT_HEALTH, target.getAttribute(Attribute.CURRENT_HEALTH)+this.potency);
	this.addToStack(-1);
	return potency;
    }

    @Override
    public boolean hasCombatTag(CombatTag tag) {
	// TODO Auto-generated method stub
	return this.combatTags.contains(tag);
    }



    
    

}
