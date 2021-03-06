package gamecore.item;

import gamecore.adventure.CombatTag;
import gamecore.adventure.UseMessage;
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

    private final ItemSubtype defaultSubtype = BasicPotionType.HEALTH;
    private ItemSubtype subtype;
    private UseTag useTag;

    Potion(ItemBuilder builder) {
	super(builder);
	this.combatTags = builder.getCombatTags();
	this.useTag = builder.getUseTag();
	this.subtype = (builder.hasSetSubtype()) ? builder.getItemSubtype() : this.defaultSubtype ;
    }

    private List<CombatTag> combatTags;

    @Override
    public UseMessage use(Entity user, Entity target) {
	return subtype.activate(UseEvent.newInstance(user, target, this, 1));
	
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
