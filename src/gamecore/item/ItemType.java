package gamecore.item;
/**
 * Each item has a type. This type is used to determine which class is instantiated by the ItemBuilder.
 * @author Alex Chythlook
 *
 */
public interface ItemType {
    /**
     * Instantiates an item. Used by ItemBuilder to determine the class of item instantiated.
     * @param builder The instance of ItemBuilder that is calling this method.
     * @return A new item instance.
     */
    Item newItemInstance(ItemBuilder builder);
}
