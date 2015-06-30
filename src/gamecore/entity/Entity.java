package gamecore.entity;

import gamecore.Inventory;
import gamecore.Reference;
import gamecore.adventure.CombatUsable;
import gamecore.item.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public abstract class Entity {

    protected String name;

    protected Map<Attribute, Integer> stats;

    protected Weapon weapon;

    protected Inventory inventory;

    protected boolean invulnerable;

    protected int salt;

    protected EntityType type;

    protected Entity(String name, Map<Attribute, Integer> stats, EntityType type) {
	this(name, stats, type, 0);
    }

    protected Entity(String name, Map<Attribute, Integer> stats, EntityType type, int salt) {
	this.name = name;
	this.stats = stats;
	this.invulnerable = false;
	this.weapon = Reference.DEFAULT_WEAPON;
	inventory = new Inventory((stats.get(Attribute.ATK_STRENGTH) + stats.get(Attribute.DEF_STRENGTH)) / 2);
	this.type = type;
	this.salt = (salt == 0) ? Reference.rand.nextInt() : salt;
    }

    protected Entity(String name, EntityType type) {
	this(name, StatMaker.makeAttributeMap(true), type);
    }

    protected Entity(String name, int experience, EntityType type) {
	this(name, StatMaker.makeAttributeMap(true, experience), type);
    }

    public char getSymbol() {
	return this.type.getSymbol();
    }

    public boolean addAttribute(Attribute attribute, int value) {
	this.stats.put(attribute, value);
	return true;
    }

    public int getAttribute(Attribute attribute) {
	return stats.get(attribute);
    }

    public boolean changeAttribute(Attribute attribute, int value) {
	/*if (this.stats.containsValue(attribute)) {*/
	    this.stats.put(attribute, this.stats.get(attribute) + value);
	    return true;
	/*}
	return false;*/

    }

    public Map<Attribute, Integer> getStats() {
	return stats;
    }

    public boolean isInvulnerable() {
	return invulnerable;
    }

    public void setInvulnerable(boolean invulnerable) {
	this.invulnerable = invulnerable;
    }

    public boolean feed(int hungerToAdd) {
	if (this.getAttribute(Attribute.MAX_HUNGER) < hungerToAdd + this.getAttribute(Attribute.CURRENT_HUNGER)) {
	    this.changeAttribute(Attribute.CURRENT_HUNGER, hungerToAdd);
	    return true;
	}

	return false;
    }

    public boolean heal(int healthToAdd) {
	if (this.getAttribute(Attribute.MAX_HEALTH) < healthToAdd + this.getAttribute(Attribute.CURRENT_HEALTH)) {
	    this.changeAttribute(Attribute.CURRENT_HEALTH, healthToAdd);
	    return true;
	}

	return false;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void equipWeapon(Weapon weapon) {
	this.putCurrentWeaponInInventory();
	this.weapon = weapon;
	this.getInventory().removeItem(weapon);
    }
    
    public void unequipWeapon() {
	this.putCurrentWeaponInInventory();
	this.weapon = Reference.DEFAULT_WEAPON;
    }
    
    private void putCurrentWeaponInInventory() {
	if (this.weapon != null && this.weapon != Reference.DEFAULT_WEAPON) {
	    inventory.addItem(this.weapon);
	}
    }

    public Inventory getInventory() {
	return inventory;
    }

    @Override
    public String toString() {
	StringBuilder thisGuy = new StringBuilder();
	thisGuy.append("Character Name: " + this.name + "\n");
	thisGuy.append("They can carry " + this.inventory.getMaxWeight() + "\n");
	thisGuy.append("Character attributes: \n");
	for (Attribute attribute : Attribute.values()) {
	    thisGuy.append(attribute.getName().toUpperCase() + ": " + this.stats.get(attribute) + "\n");
	}

	if (this.weapon != null) {
	    thisGuy.append("Weapon: " + weapon.toString());
	}

	return thisGuy.toString();
    }

    public int hurt(int amount) {

	int toReturn = amount;
	this.changeAttribute(Attribute.CURRENT_HEALTH, -amount);
	return toReturn;
    }

    public int attack(Entity entity) {

	return this.getWeapon().use(entity);
    }
    
    public int attackUsing(Entity entity, CombatUsable combatUsable) {
	return combatUsable.use(entity);
    }

    public void attack(Entity... entities) {
	for (Entity entity : entities) {
	    this.attack(entity);
	}
    }

    public Weapon getWeapon() {
	return weapon;
    }

    public void rename(String newName) {
	this.name = newName;
    }
}

class StatMaker {

    public static Map<Attribute, Integer> makeAttributeMap(boolean random, int experience, List<Attribute> buffed, List<Attribute> nerfed, boolean reward) {
	Map<Attribute, Integer> toReturn = new HashMap<Attribute, Integer>();
	if (buffed == null) {
	    buffed = new ArrayList<Attribute>();
	}
	if (nerfed == null) {
	    nerfed = new ArrayList<Attribute>();
	}
	if (experience < 100) {
	    experience = 100;
	}
	int level = Reference.WHAT_LEVEL(experience);

	for (Attribute attribute : Attribute.values()) {
	    int stat = (random) ? Reference.rand.nextInt(7) : 7;
	    if (reward && buffed.contains(attribute)) {
		stat = level;
	    } else {
		if (buffed.contains(attribute)) {
		    stat += level * 3;
		} else if (nerfed.contains(attribute)) {
		    stat += level / 2;
		} else if (attribute == Attribute.EXPERIENCE) {
		    stat = experience;
		} else if (attribute == Attribute.CURRENT_HEALTH) {
		    stat = toReturn.get(Attribute.MAX_HEALTH);
		} else if (attribute == Attribute.CURRENT_HUNGER) {
		    stat = toReturn.get(Attribute.MAX_HUNGER);
		} else if (attribute == Attribute.CURRENT_MAGIC_POINTS) {
		    stat = toReturn.get(Attribute.MAX_MAGIC_POINTS);
		} else {
		    stat += level;
		}
	    }
	    toReturn.put(attribute, stat);
	}
	return toReturn;
    }

    public static Map<Attribute, Integer> makeAttributeMap(boolean random, int experience, Attribute[] buffed, Attribute[] nerfed) {
	return makeAttributeMap(random, experience, Arrays.asList(buffed), Arrays.asList(nerfed), false);
    }

    public static Map<Attribute, Integer> makeAttributeMap(boolean random, int experience) {
	return makeAttributeMap(random, experience, new ArrayList<Attribute>(), new ArrayList<Attribute>(), false);
    }
    
    public static Map<Attribute, Integer> makeAttributeMap(boolean random) {
	return makeAttributeMap(random, 100, new ArrayList<Attribute>(), new ArrayList<Attribute>(), false);
    }
    
    public static Map<Attribute, Integer> getAttributeReward(int experience, Attribute... toDrop) {
	return makeAttributeMap(true, experience, Arrays.asList(toDrop), new ArrayList<Attribute>(), true);
    }

}