package gamecore.item;

/**
 * Each object that implements the Useable interface has a UseTag.</br> This tag
 * is intended to be used by context-dependant parts of the program to determine
 * if an item can, in fact, be used.</br> Important: This tag does not prevent
 * the use of an item by itself. It must be checked by the part of the program
 * providing the context.
 * 
 * @author Alex Chythlook
 *
 */
public enum UseTag {

    /**
     * A usable with this tag can only be used in combat.
     */
    USABLE_IN_COMBAT(),
    /**
     * A usable with this tag can only be used out of combat.
     */
    USABLE_OUT_OF_COMBAT(),
    /**
     * A usable with this tag can be used anywhere, including in contexts not
     * implemented as of this doc's writing.
     */
    USABLE_ANYWHERE();

}
