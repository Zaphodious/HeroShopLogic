package gamecore;

import gamecore.adventure.CombatTag;
import gamecore.entity.Attribute;
import gamecore.item.BasicItemType;
import gamecore.item.BasicWeaponType;
import gamecore.item.ItemBuilder;
import gamecore.item.Weapon;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public class Reference {

    public static final Weapon DEFAULT_WEAPON = (Weapon) new ItemBuilder("Fists of Fury", BasicItemType.WEAPON).notStackable().setItemSubtype(BasicWeaponType.SHIV).setCombatTags(CombatTag.HARMS_OPPONENT).setPotency(1).build();

    public static final int DEFAULT_MAX_STACK_SIZE = 42000;
    private static final int XP_MULTIPLIER = 100;

    public static int WHAT_LEVEL(int experience) {
	return experience / Reference.XP_MULTIPLIER;
    }

    public static int WHAT_EXPERIENCE(int level) {
	return level * Reference.XP_MULTIPLIER;
    }

    public static int EXPERIENCE_GAIN(int victorExperience, int deadExperience) {

	int victorLevel = WHAT_LEVEL(victorExperience);
	int deadLevel = WHAT_LEVEL(deadExperience);

	if (victorLevel - deadLevel > 5) {
	    return 1;
	} else if (deadLevel < victorLevel) {
	    return 2;
	}

	return (deadLevel - victorLevel) + 3;
    }

    public static Attribute GET_INVERSE_ATTRIBUTE(Attribute attribute) {

	    switch (attribute) {
	    case ATK_DEXTERITY:
		return Attribute.DEF_DEXTERITY;
	    case ATK_INTELLIGENCE:
		return Attribute.DEF_INTELLIGENCE;
	    case ATK_STRENGTH:
		return Attribute.DEF_STRENGTH;
	    default:
		return attribute;

	    }
    }

    

}
