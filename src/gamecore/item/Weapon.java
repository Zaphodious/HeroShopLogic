package gamecore.item;

import gamecore.adventure.CombatTag;
import gamecore.adventure.UseMessage;
import gamecore.entity.Attribute;
import gamecore.entity.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Items of this class can be equiped to an Entity's weapon slot, and delegate
 * the use function to a WeaponType object.
 * 
 * @author Alex Chythlook
 *
 */
public final class Weapon extends AbstractItem implements CombatUsable {

    private final ItemSubtype defaultSubtype = BasicWeaponType.SWORD;
    private int salt;
    private List<CombatTag> combatTags;
    private ItemSubtype subtype;
    private UseTag useTag;

    /**
     * A weapon is only to be built using an ItemBuilder object. As such, it's
     * constructor is set to package-private.
     * 
     * @param builder
     *            The instance of ItemBuilder that is building the item.
     */
    Weapon(ItemBuilder builder) {
	super(builder);
	this.salt = builder.getSalt();
	// this.salt = (salt == 0) ? Dice.SALT.roll() : salt;
	this.combatTags = new ArrayList<CombatTag>();
	this.combatTags.add(CombatTag.HARMS_OPPONENT);
	this.subtype = (builder.hasSetSubtype()) ? builder.getItemSubtype():this.defaultSubtype;
	this.useTag = builder.getUseTag();
    }

    /*
     * public Weapon(String name, int power, int durability, Attribute
     * attackUsing) { this(name, power, durability, 0, attackUsing); }
     */

    /**
     * This implementation of hashCode() takes the hash of the weapons's name
     * string and adds to it a "salt" int, randomly generated at the time of
     * this object's creation. This is so that each weapon is as unique as
     * possible, making sure that Inventory can store multiple instances of
     * otherwise-identical weapons (as weapons are not stackable).
     */
    @Override
    public int hashCode() {
	return super.hashCode() + salt;
    }

    /**
     * Each weapon uses one of the ATK_ attributes to determine its accuracy.
     * Said attribute is stored in the WeaponType object, and called from there.
     * 
     * @return The Attribute used by the weapon to determine if each attack was
     *         successful.
     */
    public Attribute getAttackUsing() {
	return subtype.getRelevantAttribute();
    }

    /**
     * This implementation of use() delates to the Weapon's WeaponType.
     */
    @Override
    public UseMessage use(Entity user, Entity target) {
	return this.subtype.activate(UseEvent.newInstance(user, target, this, 1));
    }

    @Override
    public String toString() {
	return name + ": " + "power=" + potency;
    }

    @Override
    public boolean hasCombatTag(CombatTag tag) {
	// TODO Auto-generated method stub
	return this.combatTags.contains(tag);
    }

    @Override
    public boolean hasUseTag(UseTag useTag) {
	// TODO Auto-generated method stub
	return this.useTag == useTag;
    }

}
