package gamecore.item;

import gamecore.adventure.CombatTag;
import gamecore.entity.Entity;

import java.util.List;

/**
 * The Potion class is used for single-use, combat-usable items that apply some
 * sort of effect to an entity. The use(user,target) method is delegated to the
 * PotionType object determined at item creation.<br/>
 * Note: Items of this type need not have "potion" in their name or art. A clump
 * of grass that heals burns, for example, can be considered a potion, as can a
 * rock that is lost when thrown at the opponent.<br/>
 * While not strictly true to the CombatUsable interface, it is permissible to
 * set a potion's UseTag such that it isn't usable in combat, if such
 * functionality is desired.
 * 
 * @author Alex Chythlook
 *
 */
public final class Potion extends AbstractItem implements CombatUsable {

    private PotionType potionType;
    private UseTag useTag;

    Potion(ItemBuilder builder) {
	super(builder);
	this.combatTags = builder.getCombatTags();
	this.useTag = builder.getUseTag();
    }

    private List<CombatTag> combatTags;

    @Override
    public int use(Entity user, Entity target) {
	return potionType.applyEffect(this, user, target);
    }

    @Override
    public boolean hasCombatTag(CombatTag tag) {
	return this.combatTags.contains(tag);
    }

    @Override
    public boolean hasUseTag(UseTag useTag) {
	// TODO Auto-generated method stub
	return this.useTag == useTag;
    }

}
