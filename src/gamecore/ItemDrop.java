package gamecore;

import gamecore.item.Item;

/**
 * ItemDrop contains information regarding the drop rate and quantity to drop
 * for the Item class object contained within.
 * 
 * @author Alex Chythlook
 *
 */
public final class ItemDrop {

    Item itemToDrop;
    int chanceToDrop;
    Dice rollForAmount;

    public ItemDrop(Item itemToDrop, int chanceToDrop) {
	super();
	this.itemToDrop = itemToDrop;
	this.chanceToDrop = chanceToDrop;
	this.rollForAmount = null;
    }

    /**
     * By default, the amount of items to be dropped by each ItemDrop object
     * will be 1. If a great number is desired, a Dice enum object can be passed
     * in, to be used to determine the number of items to be dropped.
     * 
     * @param rollToDrop
     * @return
     */
    public ItemDrop setAmountToDrop(Dice rollToDrop) {
	this.rollForAmount = rollToDrop;
	return this;
    }

    /**
     * Called to check and see if the item drops or not.
     * 
     * @return True if the item drops, False if it doesn't drop.
     */
    public boolean doesDrop() {
	return this.chanceToDrop >= Dice.D100.roll();
    }

    /**
     * Gets the item dropped by this Object.
     * 
     * @return The Item dropped.
     */
    public Item getItem() {
	return this.itemToDrop;
    }

    /**
     * Returns the number of items dropped by this Object. Defaults to 1.
     * 
     * @return An int representing how many items were dropped by this Object.
     *         If setAmountToDrop was called previously, returns a value between
     *         1 and the number of sides the Dice object has.
     */
    public int getDropAmount() {
	if (this.rollForAmount == null) {
	    return 1;
	} else {
	    return this.rollForAmount.roll();
	}
    }

    /**
     * The static factory method that instantiates new ItemDrop objects.
     * 
     * @param itemToDrop
     *            The item that is to be dropped by this Object.
     * @param chanceToDrop
     *            The chance between 1 (absurdly rare) and 100 (guaranteed to
     *            drop) for the item to drop.
     * @return A new instance of ItemDrop.
     */
    public final ItemDrop newInstance(Item itemToDrop, int chanceToDrop) {
	if (ZaphUtil.isWithinCentRange(chanceToDrop)) {
	    this.chanceToDrop = chanceToDrop;
	}
	return new ItemDrop(itemToDrop, chanceToDrop);
    }
}
