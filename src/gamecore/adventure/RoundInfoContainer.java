package gamecore.adventure;

import gamecore.entity.Attribute;
import gamecore.entity.Entity;
import gamecore.item.Item;

import java.util.HashMap;
import java.util.Map;

public final class RoundInfoContainer {

    Entity user;
    Entity defender;
    Item itemUsed;
    Map<Attribute, Integer> toCheckAgainst;
    int amount;

    private RoundInfoContainer(Entity user, Entity defender, Item itemUsed, int amount, Attribute... attributesUsed) {
	this.user = user;
	this.defender = defender;
	this.itemUsed = itemUsed;
	this.toCheckAgainst = AttributeMapMaker.makeCheckMap(defender, attributesUsed);
	this.amount = amount;
    }
    
    public static RoundInfoContainer newInstance(Entity attacker, Entity defender, Item itemUsed, int amount, Attribute... attributesUsed) {
	return new RoundInfoContainer(attacker, defender, itemUsed, amount, attributesUsed);
    }

    public boolean hasEntity() {
	return (this.user != null);
    }

    public boolean hasItem() {
	return (this.itemUsed != null);
    }

    public boolean hasAttributes() {
	return (this.toCheckAgainst.isEmpty());
    }

    public Entity getUser() {
	return user;
    }

    public Entity getDefender() {
	return defender;
    }

    public Item getItemUsed() {
	return itemUsed;
    }

    public Map<Attribute, Integer> getToCheckAgainst() {
	return toCheckAgainst;
    }

    public boolean hasThisAttribute(Attribute attribute) {
	return toCheckAgainst.containsKey(attribute);
    }

    public int getValueForAttribute(Attribute attribute) {
	return toCheckAgainst.get(attribute);
    }

    public int getAmount() {
	return amount;
    }

}

class AttributeMapMaker {
    public static Map<Attribute, Integer> makeCheckMap(Entity defender, Attribute... attributesUsed) {
	Map<Attribute, Integer> toReturn = new HashMap<Attribute, Integer>();
	if (attributesUsed == null) {
	    return toReturn;
	}
	for (Attribute attribute : attributesUsed) {
	    switch (attribute) {
	    case ACTION_POINTS:
		break;
	    case ATK_DEXTERITY:
		toReturn.put(Attribute.DEF_DEXTERITY, defender.getAttribute(attribute));
		break;
	    case ATK_INTELLIGENCE:
		toReturn.put(Attribute.DEF_INTELLIGENCE, defender.getAttribute(attribute));
		break;
	    case ATK_STRENGTH:
		toReturn.put(Attribute.DEF_STRENGTH, defender.getAttribute(attribute));
		break;
	    default:
		toReturn.put(attribute, defender.getAttribute(attribute));
		break;

	    }
	}
	return toReturn;
    }
}