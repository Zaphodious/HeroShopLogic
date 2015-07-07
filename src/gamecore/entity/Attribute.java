package gamecore.entity;

/**
 * Each Entity has a set of attributes, as defined here.
 * 
 * @author Alex Chythlook
 *
 */
public enum Attribute {
    /**
     * The amount of Gold that an entity is currently carrying.
     */
    GOLD("gold"),
    /**
     * An Entity's experience total is used to determine their level. See
     * Reference.java for more details.
     */
    EXPERIENCE("experience"),
    /**
     * The maximum amount of health that an entity can have.
     */
    MAX_HEALTH("max_health"),
    /**
     * The amount of health that an entity has at any point in time. Mainly used
     * in combat. Once this goes to 0, something bad should happen to the
     * entity.
     */
    CURRENT_HEALTH("current_health"),
    /**
     * The maximum amount of hunger that an entity can have.
     */
    MAX_HUNGER("max_hunger"),
    /**
     * The amount of hunger that an entity has at any point in time. Once this
     * reaches the maximum, if the entity is a player, they may not adventure
     * again until they have eaten.
     */
    CURRENT_HUNGER("current_hunger"),
    /**
     * The maximum amount of magic points that an entity can have.
     */
    MAX_MAGIC_POINTS("max_magic_points"),
    /**
     * The current amount of magic points that an entity has at any point in
     * time. Used as a resource to pay for abilities. If an ability costs more
     * magic points then an entity has, they should not be able to use that
     * ability.
     */
    CURRENT_MAGIC_POINTS("current_magic_points"),
    /**
     * The current amount of "luck" points that an entity has at any point in
     * time. Uncapped. These are used to accomplish wonderous feats and
     * miraculous happenings.
     */
    ACTION_POINTS("action_points"),
    /**
     * An Entity's relative accuracy with strength-based weapons like swords and
     * axes. Used in combat math to calculate if a strength-based weapon hits or
     * not.
     */
    ATK_STRENGTH("atk_strength"),
    /**
     * An Entity's relative accuracy with dexterity-based weapons like bows and
     * daggers. Used in combat math to calculate if a dexterity-based weapon
     * hits or not.
     */
    ATK_DEXTERITY("atk_dexterity"),
    /**
     * An Entity's relative accuracy with intelligence-based weapons like staves
     * and wands. Used in combat math to calculate if an intelligence-based
     * weapon hits or not.
     */
    ATK_INTELLIGENCE("atk_intelligence"),
    /**
     * If an entity is attacked with a strength-based weapon, combat math uses
     * this to determine if the entity is hit.
     */
    DEF_STRENGTH("def_strength"),
    /**
     * If an entity is attacked with a dexterity-based weapon, combat math uses
     * this to determine if the entity is hit.
     */
    DEF_DEXTERITY("def_dexterity"),
    /**
     * If an entity is attacked with a intelligence-based weapon, combat math
     * uses this to determine if the entity is hit.
     */
    DEF_INTELLIGENCE("def_intelligence"),
    /**
     * Used to determine how likeable an entity is by non-combat encounters and
     * other merchants. Also used in combat to determine the effectiveness of
     * some skills.
     */
    CHARISMA("charisma"),
    /**
     * Used to determine if and what an entity picks up on in non-combat
     * encounters. A wise entity can more easily detect a lie or find alternate
     * solutions to problems. Also used to determine how well the player governs
     * their shop.
     */
    WISDOM("wisdom");

    private String name;

    Attribute(String name) {
	this.name = name;
    }

    /**
     * Used to make it easier to determine the identities of certain numbers in
     * testing.
     * 
     * @return The name of an attribute.
     */
    public String getName() {
	return name;
    }

}
