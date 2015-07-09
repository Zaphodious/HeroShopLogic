package gamecore.item;

import gamecore.adventure.MessageType;
import gamecore.adventure.UseMessage;
import gamecore.entity.Attribute;

public enum BasicPotionType implements ItemSubtype {

    HEALTH() {
	@Override
	public UseMessage activate(UseEvent useEvent) {
	    Potion potion = (Potion) useEvent.getItemUsed();
	    useEvent.getTarget().heal(potion.getPotency());
	    return UseMessage.newInstance(MessageType.PLAYER_STAT_INCREASE, "You are healed for " + potion.getPotency());
	}
    };

    @Override
    public abstract UseMessage activate(UseEvent useEvent);

    @Override
    public Attribute getRelevantAttribute() {
	return Attribute.ATK_INTELLIGENCE;
    }


}
