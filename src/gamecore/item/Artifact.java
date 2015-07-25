package gamecore.item;

import gamecore.adventure.CombatTag;
import gamecore.adventure.UseMessage;
import gamecore.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public final class Artifact extends AbstractItem implements CombatUsable {

    List<CombatTag> combatTags;
    ItemSubtype subtype;
    UseTag useTag;
    ItemSubtype defaultSubtype = BasicArtifactSubtype.MONKEY_FIGURE;
    
    Artifact(ItemBuilder builder) {
	super(builder);
	// this.salt = (salt == 0) ? Dice.SALT.roll() : salt;
	this.combatTags = new ArrayList<CombatTag>();
	this.combatTags.add(CombatTag.HARMS_OPPONENT);
	this.subtype = (builder.hasSetSubtype()) ? builder.getItemSubtype():this.defaultSubtype;
	this.useTag = builder.getUseTag();
    }

    @Override
    public UseMessage use(Entity user, Entity target) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean hasUseTag(UseTag useTag) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean hasCombatTag(CombatTag tag) {
	// TODO Auto-generated method stub
	return false;
    }

    
    
}
