package gamecore.entity;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public enum Attribute {
    GOLD("gold"),
    EXPERIENCE("experience"),
    MAX_HEALTH("max_health"),
    CURRENT_HEALTH("current_health"),
    MAX_HUNGER("max_hunger"),
    CURRENT_HUNGER("current_hunger"),
    MAX_MAGIC_POINTS("max_magic_points"),
    CURRENT_MAGIC_POINTS("current_magic_points"),
    ACTION_POINTS("action_points"),
    ATK_STRENGTH("atk_strength"),
    ATK_DEXTERITY("atk_dexterity"),
    ATK_INTELLIGENCE("atk_intelligence"),
    DEF_STRENGTH("def_strength"),
    DEF_DEXTERITY("def_dexterity"),
    DEF_INTELLIGENCE("def_intelligence"),
    CHARISMA("charisma"),
    WISDOM("wisdom");

    private String name;

    Attribute(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }


    
}
