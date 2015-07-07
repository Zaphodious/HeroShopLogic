package gamecore.entity;

import gamecore.Dice;
import gamecore.Inventory;
import gamecore.Reference;
import gamecore.ZaphUtil;
import gamecore.item.Armor;
import gamecore.item.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The EntityBuilder class is the only legal way to instantiate a new Entity
 * object. This class implements the Builder pattern as described in Effective
 * Java.
 * 
 * @author Alex Chythlook
 *
 */
public final class EntityBuilder {

    private String name = "Unnamed Entity";

    private Map<Attribute, Integer> stats;

    private boolean runStatMapMaker = true;

    private Weapon weapon = Reference.DEFAULT_WEAPON;

    private Map<ArmorSlot, Armor> armor = ZaphUtil.newMap();

    private Inventory inventory = new Inventory();

    private boolean makeNewInventory = true;

    private int salt = 0;

    private boolean rollForSalt = true;

    private EntityType type = EntityType.MONSTER;

    private List<Attribute> toBuff = ZaphUtil.newList();

    private List<Attribute> toNerf = ZaphUtil.newList();

    private int experience = 100;

    private boolean rollStats = true;

    /**
     * Each Entity starts with a String name and an EntityType type.
     * 
     * @param name
     *            The name of the Entity to be built.
     * @param type
     *            The EntityType of the Entity to be built. Used for categorical
     *            purposes.
     */
    public EntityBuilder(String name, EntityType type) {
	this.name = name;
	this.type = type;
    }

    /**
     * By default, stats will be determined at random. If specific stats are
     * desired, however, this method can be invoked.
     * 
     * @param statMap
     *            Must include one of each Attribute Enum object as a key.
     * @return The EntityBuilder instance, so that the method can be chained.
     */
    public EntityBuilder setStatMap(Map<Attribute, Integer> statMap) {
	this.stats = statMap;
	this.runStatMapMaker = false;
	return this;
    }

    /**
     * If one or more stats should be slightly higher then the others when they
     * are generated, invoke this method and pass them through. The inverse of
     * the statsToNerf function.
     * 
     * @param attributesToBuff
     *            One or more Attribute enum objects. Each Attribute passed in
     *            will be slightly stronger then it normally would be, upon stat
     *            generation.
     * @return The EntityBuilder instance, so that the method can be chained.
     */
    public EntityBuilder statsToBuff(Attribute... attributesToBuff) {
	toBuff.addAll(Arrays.asList(attributesToBuff));
	return this;
    }

    /**
     * If one or more stats should be slightly lower then the others when they
     * are generated, invoke this method and pass them through. The inverse of
     * the statsToBuff function.
     * 
     * @param attributesToNerf
     *            One or more Attribute enum objects. Each Attribute passed in
     *            will be slightly weaker then it normally would be, upon stat
     *            generation.
     * @return The EntityBuilder instance, so that the method can be chained.
     */
    public EntityBuilder statsToNerf(Attribute... attributesToNerf) {
	toNerf.addAll(Arrays.asList(attributesToNerf));
	return this;
    }

    /**
     * The Salt is used to make each individual Entity unique. By default these
     * are generated randomly. If a spcific salt is desired, this method can be
     * invoked.
     * 
     * @param salt
     *            An int used to determine the unique identity of the new
     *            Entity.
     * @return The EntityBuilder instance, so that the method can be chained.
     */
    public EntityBuilder setSalt(int salt) {
	this.salt = salt;
	this.rollForSalt = false;
	return this;
    }

    /**
     * Each Entity has an experience amount corresponding to its relative power.
     * This value is used to determine its level. By default, an Entity starts
     * with 100 experience (level 1 as of this version).<br/>
     * It is recommended that this value be changed to something equivalent to
     * the area that the Entity will be appearing in.
     * 
     * @param experience
     *            A numerical representation of the new Entity's power. See the
     *            Reference class for more information about the formula used to
     *            determine levels.
     * @return The EntityBuilder instance, so that the method can be chained.
     */
    public EntityBuilder experience(int experience) {
	this.experience = experience;
	return this;
    }

    /**
     * stats are usually determined at random upon Entity creation. If this
     * behavior is not desired, this method can be invoked. This sets the stat
     * builder to provide the base stat amounts for each stat, modified by the
     * stats set to be buffed and nerfed.
     * 
     * @return The EntityBuilder instance, so that the method can be chained.
     */
    public EntityBuilder statsNotRandom() {
	this.rollStats = false;
	return this;
    }

    /**
     * Be default, each new Entity will be provided with an empty Inventory
     * appropriate to their stats. This method can be called if the Entity is to
     * have a specific inventory.
     * 
     * @param inventory
     *            an instance of Inventory to be added to the new Entity, rather
     *            then the default stat-appropriate one provided at build.
     * @return The EntityBuilder instance, so that the method can be chained.
     */
    public EntityBuilder setInventory(Inventory inventory) {
	this.inventory = inventory;
	this.makeNewInventory = false;
	return this;
    }

    /**
     * By default, a new Entity will be equipped with a basic, 1-power Dexterity
     * weapon. This method can be invoked if a more appropriate weapon is to be
     * held at build.
     * 
     * @param weapon
     *            The weapon for the new Entity to be holding.
     * @return The EntityBuilder instance, so that the method can be chained.
     */
    public EntityBuilder setWeapon(Weapon weapon) {
	this.weapon = weapon;
	return this;

    }

    String getName() {
	return name;
    }

    Map<Attribute, Integer> getStats() {
	return stats;
    }

    boolean RunStatMapMaker() {
	return runStatMapMaker;
    }

    Weapon getWeapon() {
	return weapon;
    }

    Map<ArmorSlot, Armor> getArmor() {
	return armor;
    }

    Inventory getInventory() {
	return inventory;
    }

    int getSalt() {
	return salt;
    }

    boolean rollForSalt() {
	return rollForSalt;
    }

    EntityType getType() {
	return type;
    }

    List<Attribute> getToBuff() {
	return toBuff;
    }

    List<Attribute> getToNerf() {
	return toNerf;
    }

    int getExperience() {
	return experience;
    }

    boolean MakeNewInventory() {
	return makeNewInventory;
    }

    boolean rollStats() {
	return rollStats;
    }

    /**
     * Crates a new Entity instance, using the parameters previously passed to
     * the EntityBuilder object.
     * 
     * @return A new instance of Entity.
     */
    public Entity build() {
	if (rollForSalt) {
	    this.salt = new Random().nextInt();
	}

	if (runStatMapMaker) {
	    this.stats = makeAttributeMap(rollStats, experience, toBuff, toNerf);
	}

	if (makeNewInventory) {
	    this.inventory = new Inventory((stats.get(Attribute.ATK_STRENGTH) + stats.get(Attribute.DEF_STRENGTH)) / 2);
	}

	return new Entity(this);

	/*
	 * switch (this.type) { case HERO: return new Hero(this); case MONSTER:
	 * return new Monster(this); case PLAYER_CHARACTER: return new
	 * Hero(this); default: return new Monster(this);
	 */

    }

    /*
     * This method is used to generate stats for a new Entity, in the event that
     * a complete map of stats wasn't passed in before build.
     */
    private Map<Attribute, Integer> makeAttributeMap(boolean random, int experience, List<Attribute> buffed, List<Attribute> nerfed) {
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
	    int stat = (random) ? Dice.D3.roll() + Dice.D3.roll() + Dice.D3.roll() : 6;
	    if (buffed.contains(attribute)) {
		System.out.println("buffed will be " + attribute.name());
		stat += 1;
	    }

	    if (nerfed.contains(attribute)) {
		System.out.println("nerfed will be " + attribute.name());
		stat -= 1;
	    }

	    if (attribute == Attribute.EXPERIENCE) {
		stat = experience;
	    } else if (attribute == Attribute.CURRENT_HEALTH) {
		stat = toReturn.get(Attribute.MAX_HEALTH);
	    } else if (attribute == Attribute.CURRENT_HUNGER) {
		stat = toReturn.get(Attribute.MAX_HUNGER);
	    } else if (attribute == Attribute.CURRENT_MAGIC_POINTS) {
		stat = toReturn.get(Attribute.MAX_MAGIC_POINTS);
	    } else {
		// stat += level;

	    }

	    toReturn.put(attribute, stat);
	}
	return toReturn;
    }

}
