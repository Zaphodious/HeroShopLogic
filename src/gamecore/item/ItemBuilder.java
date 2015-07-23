package gamecore.item;

import gamecore.ZaphUtil;
import gamecore.adventure.CombatTag;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class is the only way to instantiate a new Item. It implements the
 * Builder pattern as described in Effective Java. </p> In order to instantiate
 * an Item, first create a new ItemBuilder(name,ItemType), then modify it using
 * the optional setters. Finally, call build() to generate the new item
 * instance.</p> Note: Each setter returns the parent instance of ItemBuilder,
 * so that item creation can be achieved all on one line.
 * 
 * @author Alex Chythlook
 *
 */
public final class ItemBuilder {

    private String ID = null;

    private String name;
    private ItemType type;

    private int potency = 1;
    private int weight = 2;

    private boolean stackable = true;
    private boolean combatUsable = false;

    private int salt = 0;
    private boolean rollSalt = true;
    private List<CombatTag> combatTags = ZaphUtil.newList();
    private UseTag useTag = UseTag.USABLE_OUT_OF_COMBAT;
    private int saleValue = 10;
    private int saleDifficulty = 50;
    private Rarity rarity = Rarity.COMMON;
    private ItemSubtype subtype;
    private boolean hasSetSubtype = false;

    /**
     * Item creation starts here. Each item needs a name and an ItemType.
     * 
     * @param name
     *            The item's name. Preferably a unique one.
     * @param type
     *            The new item's type. ItemType's newItemInstance(ItemBuilder
     *            builder) function will be called on build() to determine the
     *            class of the new item.
     */
    public ItemBuilder(String name, ItemType type) {
	this.name = name;
	this.type = type;
    }

    /**
     * An ID is automatically generated at build time, but may be passed in
     * using this method instead. IDs are reset after every build.
     * 
     * @param ID
     *            The string to be used as the next built item's ID
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    public ItemBuilder setID(String ID) {
	this.ID = ID;
	return this;
    }

    /**
     * Just in case you wish to re-use an ItemBuilder to create a new item (that
     * is similar to one already created, or else a new ItemBuilder should be
     * used), a Name setter has been provided.
     * 
     * @param name
     *            The new name for the new item.
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    public ItemBuilder setName(String name) {
	this.name = name;
	return this;
    }

    /**
     * In case an instance of ItemBuilder is to be re-used to create a new item
     * (that is similar to one already created, or else a new ItemBuilder should
     * be created), an ItemType setter has been provided.
     * 
     * @param type
     *            The new type for the new item.
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    public ItemBuilder setType(ItemType type) {
	this.type = type;
	return this;
    }

    /**
     * Sets the potency for the new item. Potency is used by the item to
     * determine its effectiveness when used.</br> Note: Default potency is 1.
     * 
     * @param potency
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    public ItemBuilder setPotency(int potency) {
	this.potency = potency;
	return this;
    }

    /**
     * Sets the weight of the new item. Default is 2, which is appropriate for
     * most smaller items. For weapons and large items, it is suggested that
     * this be changed to something more appropriate. For items of negligible
     * weight, this value should be 0.
     * 
     * @param weight
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    public ItemBuilder setWeight(int weight) {
	this.weight = weight;
	return this;
    }

    /**
     * Calling this sets the item to be non-stackable.
     * 
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    public ItemBuilder notStackable() {
	this.stackable = false;
	return this;
    }

    /**
     * Calling this sets the item to be stackable, which is the default. This
     * item is provided in case an ItemBuilder object is to be re-used.
     * 
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    public ItemBuilder setStackable() {
	this.stackable = true;
	return this;
    }

    /**
     * This setter is intended for use on loading a saved state. As salts are
     * generated randomly for classes that use them, it could cause problems if
     * a new salt were rolled each time a save was loaded.</p> If this function
     * isn't called, a new salt will be rolled upon Item creation if the class
     * requires it.
     * 
     * @param salt
     *            The salt as stored in the save file.
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    public ItemBuilder setSalt(int salt) {
	this.salt = salt;
	this.rollSalt = false;
	return this;
    }

    /**
     * If the item is to be used in combat, it should include combat tags.
     * Default is no combat tags included, so any intended in-combat
     * functionality should be set here.
     * 
     * @param combatTags
     *            any number of individual combat tags.
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */

    public ItemBuilder setCombatTags(CombatTag... combatTags) {
	this.combatTags = Arrays.asList(combatTags);
	return this;
    }

    /**
     * By default usable items are set to USABLE_OUT_OF_COMBAT. If an item is
     * intended to be combat-usable, an appropriate UseTag should be set to
     * allow that.
     * 
     * @param useTag
     *            a UseTag enum
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    public ItemBuilder setUseTag(UseTag useTag) {
	this.useTag = useTag;
	return this;
    }

    /**
     * By default, weapons will be set to BasicWeaponType.SWORD. This function
     * is used if a different WeaponType is desired.
     * 
     * @param type
     *            The desired WeaponType object.
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    /*
     * public ItemBuilder setWeaponType(WeaponType type) { this.weaponType =
     * type; return this; }
     */

    public ItemBuilder setItemSubtype(ItemSubtype subtype) {
	this.subtype = subtype;
	this.hasSetSubtype = true;
	return this;
    }

    /**
     * By default, potions will be set to BasicPotionType.HEALTH. This function
     * is used if a different PotionType is desired.
     * 
     * @param type
     *            The desired PotionType object.
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    /*
     * public ItemBuilder setPotionType(PotionType type) { this.potionType =
     * type; return this; }
     */

    /**
     * By default, an item will be set to Rarity.COMMON. If an alternate rarty
     * is desired, this is where to set it.
     * 
     * @param rarity
     *            An object from the Rarity enum class.
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    public ItemBuilder setItemRarity(Rarity rarity) {
	this.rarity = rarity;
	return this;
    }

    /**
     * By default, an item is worth 10 gold. If a different value is desired,
     * this is where to set it.
     * 
     * @param saleValue
     *            The new value, in gold, that the item is worth.
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    public ItemBuilder setSaleValue(int saleValue) {
	this.saleValue = saleValue;
	return this;
    }

    /**
     * By default, an item has a saleDifficulty of 50 (neither difficult or hard
     * to sell). If a new value is desired, this is where to set it.
     * 
     * @param saleDifficulty
     *            An int value between 1 (extremely hard to sell) and 100 (sells
     *            like hotcakes) that represents this item's chances at being
     *            sold in the player's shop.
     * @return This ItemBuilder instance, so that further setters can be
     *         chained.
     */
    public ItemBuilder setSaleDifficulty(int saleDifficulty) {
	if (ZaphUtil.isWithinCentRange(saleDifficulty)) {
	    this.saleDifficulty = saleDifficulty;
	}
	return this;
    }

    public String getName() {
	return name;
    }

    public ItemType getType() {
	return type;
    }

    /*
     * public PotionType getPotionType() { return potionType; }
     */

    public int getPotency() {
	return potency;
    }

    public int getWeight() {
	return weight;
    }

    public boolean isStackable() {
	return stackable;
    }

    public boolean isCombatUsable() {
	return combatUsable;
    }

    public int getSalt() {
	if (this.rollSalt) {
	    salt = new Random().nextInt();
	}
	return salt;
    }

    public List<CombatTag> getCombatTags() {

	return combatTags;
    }

    public UseTag getUseTag() {

	return this.useTag;
    }

    /*
     * WeaponType getWeaponType() { return this.weaponType; }
     */

    public ItemSubtype getItemSubtype() {
	return this.subtype;
    }

    public boolean hasSetSubtype() {
	return this.hasSetSubtype;
    }

    public int getSaleValue() {
	return saleValue;
    }

    public Rarity getRarity() {
	return rarity;
    }

    public int getSaleDifficulty() {
	return saleDifficulty;
    }

    public String getID() {
	return this.ID;
    }

    /**
     * Calling this function builds the item. The actual instantiation is
     * delegated to the ItemType object provided in ItemBuilder's constructor.
     * 
     * @return A new instance of Item, according to the paramaters set before
     *         this was called.
     */
    public Item build() {

	if (this.ID == null) {
	    String seperator = "/%/";
	    StringBuilder idBuilder = new StringBuilder();
	    idBuilder.append(getName() + seperator).append(this.type + seperator);
	    if (this.hasSetSubtype) {
		idBuilder.append(this.subtype + seperator);
	    }
	    idBuilder.append(this.combatTags.toString() + seperator);
	    idBuilder.append(this.potency + seperator).append(this.weight + seperator);

	    this.ID = idBuilder.toString();
	}

	Item toReturn = type.newItemInstance(this);
	this.ID = null;
	return toReturn;
    }

}
