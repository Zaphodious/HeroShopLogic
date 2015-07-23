package gamecore.item;

/**
 * Objects implementing this interface are considered representations of
 * physical items.</br></br>
 * 
 * Items should be immutable, relying on their containing Inventory and ItemDrop
 * objects to determine changeable attributes such as stack size.</br></br>
 * 
 * If the item is to be usable, it should also implement either Usable or
 * CombatUsable.
 * 
 * @author Alex Chythlook
 *
 */
public interface Item {

    /**
     * This is a unique code for each item, computed by the Builder unless given
     * one before Item creation. This is <i>not</i> the database ID, though it
     * is stored in the database. Although intended to be human-readable, it is
     * not intended to be enough to reconstruct a given item. It is only to be
     * used as an identifier.
     * 
     * @return this Item's ID String.
     */
    public String getID();

    /**
     * Each item has a name, which should be unique to avoid confusion and
     * conflicts.
     * 
     * @return this Item's name as a String.
     */
    public String getName();

    /**
     * Each item has a type, used to determine its class and behavior (see the
     * ItemType enum for more information)
     * 
     * @return The ItemType for this item.
     */
    public ItemType getType();

    /**
     * Each item has a potency, used by each implementation to determine
     * something about the way the Item behaves.
     * 
     * @return This Item's potency as an int.
     */
    public int getPotency();

    /**
     * The Inventory uses the weight of all its contained items to determine
     * how full it is.
     * 
     * @return This Item's weight as an int.
     */
    public int getWeight();

    /**
     * The Inventory uses this to determine if the stack size for an item can be
     * more then 1.
     * 
     * @return True if the item can have a stacksize of greater then one, false
     *         if the stack size must be 1 or lower.
     */
    public boolean isStackable();

    /**
     * Each item can be sold to the NPCs that buy from the player's shop.
     * 
     * @return The value of the item in gold.
     */
    public int getSaleValue();

    /**
     * Each item has a rarity associated with it.
     * 
     * @return The Rarity object that represents how rare this item is.
     */
    public Rarity getRarity();

    /**
     * Some items are harder to sell then others.
     * 
     * @return An int value between 1 (extremely hard to sell) and 100 (sells
     *         like hotcakes) that represents this item's chances at being sold
     *         in the player's shop.
     */
    public int getSaleDifficulty();

    /**
     * For items, the hashcode should be the hashcode of the ID string.
     * 
     * @return An integer unique to the item.
     */
    public int hashCode();

}
