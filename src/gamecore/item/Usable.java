package gamecore.item;

import gamecore.entity.Entity;

public interface Usable {

    /**
     * Each usable thing has a name.
     * 
     * @return The name of this Usable object.
     */
    public String getName();

    /**
     * Called when the item is to be used.
     * 
     * @param user
     *            The Entity using the item.
     * @param target
     *            The desired target of the item. Can be any entity, including
     *            the user and the opponent.
     * @return An integer representative of the item's result. For example: a
     *         weapon would return the damage it has done to the target, while a
     *         health potion would return the amount of health the target has
     *         recovered.
     */
    public int use(Entity user, Entity target);

    /**
     * Use tags are used to determine the context in which this Usable object
     * can be used.
     * 
     * @param useTag
     *            the UseTag to check against.
     * @return True if the Item holds the given UseTag, false if it does not.
     */
    public boolean hasUseTag(UseTag useTag);
}
