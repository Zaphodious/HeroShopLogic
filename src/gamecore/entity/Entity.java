package gamecore.entity;

import gamecore.Dice;
import gamecore.Inventory;
import gamecore.Reference;
import gamecore.ZaphUtil;
import gamecore.adventure.RoundInfoContainer;
import gamecore.item.Armor;
import gamecore.item.CombatUsable;
import gamecore.item.Item;
import gamecore.item.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The meat-and-potatoes of combat. Each Entity has a name, a set of attributes,
 * a weapon slot, armor slots, an EntityType, and an Inventory.<br/>
 * The only way to instantiate an Entity is through an EntityBuilder object.
 * 
 * @author Alex Chythlook
 *
 */
public final class Entity {

    private String name;

    private Map<Attribute, Integer> stats;

    private Weapon weapon;

    private Map<ArmorSlot, Armor> armor;

    private Inventory inventory;

    private int salt;

    EntityType type;

    Entity(EntityBuilder builder) {

	this.salt = builder.getSalt();
	this.stats = builder.getStats();
	this.inventory = builder.getInventory();
	this.name = builder.getName();
	this.weapon = builder.getWeapon();
	this.armor = builder.getArmor();
	this.type = builder.getType();
    }

    /**
     * Used to get the int value of a given attribute.
     * 
     * @param attribute
     *            The Attribute enum for which a value is desired.
     * @return The int value corrisponding to the supplied Attribute.
     */
    public int getAttribute(Attribute attribute) {
	return stats.get(attribute);
    }

    private boolean changeAttribute(Attribute attribute, int value) {
	this.stats.put(attribute, this.stats.get(attribute) + value);
	return true;

    }

    /**
     * Feeds the entity.
     * 
     * @param hungerToAdd
     *            The potency of the food item/magic effect used to feed the
     *            player.
     */
    public void feed(int hungerToAdd) {
	// TODO: Make it so that if the player's hunger would go below 0, it
	// goes to 0 instead.

	this.changeAttribute(Attribute.CURRENT_HUNGER, hungerToAdd);

    }

    /**
     * Heals the entity by a certain amount. Overhealing possible.
     * 
     * @param healthToAdd
     *            amount by which to heal the player
     * @return True if the entity's health was not at or above the max. False
     *         otherwise.
     */
    public boolean heal(int healthToAdd) {
	if (this.getAttribute(Attribute.MAX_HEALTH) > this.getAttribute(Attribute.CURRENT_HEALTH)) {
	    this.changeAttribute(Attribute.CURRENT_HEALTH, healthToAdd);
	    return true;
	}

	return false;
    }

    /**
     * Adds to the entity's gold count.
     * 
     * @param goldToAdd
     *            The amount of gold to give the entity.
     * @return False if the value would make the total gold count go below 0,
     *         True otherwise.
     */
    public boolean giveGold(int goldToAdd) {
	// TODO: make it so that if the gold count would go below zero, method
	// returns false immediately.
	this.changeAttribute(Attribute.GOLD, goldToAdd);
	return true;
    }

    /**
     * Restores magic points. Over-restoring possible.
     * 
     * @param magicPointsToAdd
     *            Amount of magic points to restore.
     * @return True if the entity's magic point total was not at or above the
     *         max. False otherwise.
     */
    public boolean restoreMP(int magicPointsToAdd) {
	if (this.getAttribute(Attribute.MAX_MAGIC_POINTS) > this.getAttribute(Attribute.CURRENT_MAGIC_POINTS)) {
	    this.changeAttribute(Attribute.CURRENT_MAGIC_POINTS, magicPointsToAdd);
	    return true;
	}

	return false;
    }

    /**
     * Gets the name of the player
     * 
     * @return The player's name in String form.
     */
    public String getName() {
	return name;
    }

    void reName(String name) {
	this.name = name;
    }

    /**
     * Equips the provided weapon, returning the currently-equpiped weapon to
     * the inventory.
     * 
     * @param weapon
     * @return
     */
    public boolean equipWeapon(Weapon weapon) {
	this.putCurrentWeaponInInventory();
	this.weapon = weapon;
	this.getInventory().removeItem(weapon);
	return true;
    }

    /**
     * Unequips the current weapon, returning it to the inventory.
     */
    public void unequipWeapon() {
	this.putCurrentWeaponInInventory();
	this.weapon = Reference.DEFAULT_WEAPON;
    }

    /**
     * Gets the Armor item corresponding to the provided ArmorSlot
     * 
     * @param slot
     *            the ArmorSlot Enum object for which an Armor item is desired
     * @return an Armor item corresponding to the provided ArmorSlot. Null if
     *         empty.
     */
    public Armor getArmorInSlot(ArmorSlot slot) {
	return this.armor.get(slot);
    }

    /**
     * Causes the Entity to put on a piece of armor, returning the current armor
     * to the inventory.
     * 
     * @param slot
     *            The slot into which the provided armor should be equipped.
     * @param armor
     *            The armor to equip.
     * @return True if the equip was successful. False if it was not.
     */
    public boolean equipArmor(ArmorSlot slot, Armor armor) {
	this.putCurrentArmorInInventory(slot);
	this.armor.put(slot, armor);
	this.getInventory().removeItem(armor);
	return true;
    }

    private void putCurrentArmorInInventory(ArmorSlot slot) {
	// TODO Auto-generated method stub

    }

    private void putCurrentWeaponInInventory() {
	if (this.weapon != null && this.weapon != Reference.DEFAULT_WEAPON) {
	    inventory.addItem(this.weapon, 1);
	}
    }

    /**
     * Directly accesses the Entity's Inventory
     * 
     * @return the Inventory object stored by the Entity
     */
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

    public boolean successfullyHits(RoundInfoContainer roundInfo) {
	// TODO: Change this so that each weapon determines if it hits or not,
	// instead of the player.
	int difficultyCheck = 10;
	int defenderModifier = 0;
	int attackerModifier = 0;
	int numberOfAttributesUsed = 0;
	List<Attribute> usedToCheck = new ArrayList<Attribute>();
	if (roundInfo.hasAttributes()) {
	    for (Attribute attribute : Attribute.values()) {
		if (roundInfo.hasThisAttribute(attribute)) {
		    numberOfAttributesUsed++;
		    usedToCheck.add(attribute);
		    defenderModifier += roundInfo.getValueForAttribute(attribute);
		    attackerModifier += roundInfo.getAttacker().getAttribute(attribute);
		}

		if (defenderModifier > 0 && numberOfAttributesUsed > 1) {
		    defenderModifier = defenderModifier / numberOfAttributesUsed;
		    if (attackerModifier > 0) {
			attackerModifier = attackerModifier / numberOfAttributesUsed;
		    }
		}

	    }
	}

	difficultyCheck += defenderModifier;

	return (difficultyCheck > Dice.D10.roll() + attackerModifier);
    }

    /**
     * Reduces the Entity's health by some amount, determined by using the
     * information contained in the provided RoundInfoContainer object.
     * 
     * @param damage
     *            The RoundInfoContainer object contains several bits of
     *            information that help the entity determine how much damage it
     *            takes.
     * @return The amount that the Entity was damaged, as an int.
     */
    public int hurt(RoundInfoContainer damage) {
	int toReturn = damage.getAmount();

	/*
	 * if (damage.hasAttributes()) { if (damage.hasEntity()) { for
	 * (Attribute attribute : damage.getToCheckAgainst().keySet()) { int
	 * modifier = damage.getToCheckAgainst().get(attribute) -
	 * this.getAttribute(attribute); toReturn += modifier; } } }
	 */

	if (toReturn <= 0) {
	    toReturn = 1;
	}
	this.changeAttribute(Attribute.CURRENT_HEALTH, -toReturn);
	return toReturn;
    }

    /**
     * Called when the Entity is to attack another Entity using their equipped
     * weapon.
     * 
     * @param entity
     *            The entity to be attacked.
     * @return The damage done to the attacked entity, as an int.
     */

    public int attack(Entity entity) {

	return this.getWeapon().use(this, entity);
    }

    /**
     * Called if the Entity is to attack another Entity using a CombatUsable
     * object other then their equipped weapon.
     * 
     * @param entity
     *            The entity to be attacked.
     * @param combatUsable
     *            The CombatUsable object with which the Entity is to make their
     *            attack.
     * @return A numerical representation of the attack result. Usually the
     *         damage done, but it could be different if the CombatUsable item
     *         doesn't effect the target's health.
     */
    public int attackUsing(Entity entity, CombatUsable combatUsable) {
	return combatUsable.use(this, entity);
    }

    /**
     * Gets the Weapon-class item currently equiped by the player
     * 
     * @return The Weapon currently held in the player's Weapon slot.
     */
    public Weapon getWeapon() {
	return weapon;
    }

    /**
     * Changes the Entity's name.
     * 
     * @param newName
     *            The desired new name.
     */
    public void rename(String newName) {
	this.name = newName;
    }

    /**
     * Each new instance of Entity is unique. Even with the same name, weapon,
     * etc, two Entity class objects are not to be equal unless they are,
     * indeed, the same instance.
     */
    @Override
    public int hashCode() {
	return name.hashCode() + salt;
    }

}