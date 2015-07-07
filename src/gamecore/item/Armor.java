package gamecore.item;

import gamecore.entity.Entity;

/**
 * Currently half-implemented, Armor will be used in combat to determine how
 * much damage a successful attack on its wearer does.
 * 
 * @author Alex Chythlook
 *
 */

public class Armor extends AbstractItem implements Usable {

    private UseTag useTag;

    protected Armor(ItemBuilder builder) {
	super(builder);
	this.useTag = builder.getUseTag();
    }

    @Override
    public int use(Entity user, Entity target) {
	return 0;
    }

    @Override
    public boolean hasUseTag(UseTag useTag) {
	return this.useTag == useTag;
    }

}
