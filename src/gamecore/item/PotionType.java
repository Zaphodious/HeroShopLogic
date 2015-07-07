package gamecore.item;

import gamecore.entity.Entity;

/**
 * Each Potion.class object contains a reference to a PotionType object, which
 * is used to determine the behavior of the Potion's use(user,target)
 * function.</p> Suggested implementation is as an Enum connected to the Area
 * from which this potion drops.
 * 
 * @author Alex Chythlook
 *
 */
public interface PotionType {

    /**
     * This method is invoked by the containing Potion.class when it's
     * use(user,target) method is invoked.</p> See the Usable interface for more
     * information, as this method is designed to be used by its use method.
     * 
     * @param potion
     *            The instance of Potion.class that is invoking this method
     * @param user
     *            The Entity that is using this item.
     * @param target
     *            The intended target. Can be any Entity, including the User and
     *            the Opponent.
     * @return An integer representative of the item's result. For example, if
     *         this Potion is a health potion, it returns the amount of health
     *         it has restored.
     */
    public int applyEffect(Potion potion, Entity user, Entity target);

}