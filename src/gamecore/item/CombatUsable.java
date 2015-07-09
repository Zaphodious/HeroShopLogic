package gamecore.item;

import gamecore.adventure.CombatTag;

/**
 * Provides both type and behavior for things usable in combat (items,
 * abilities, etc). See Usable for more information on usable objects.
 * 
 * @author Alex Chythlook
 *
 */
public interface CombatUsable extends Usable {

    /**
     * Used to check the intended behavior before a CombatUsable object is used.
     * 
     * @param tag
     *            CombatTag to check against
     * @return If the CombatUsable object has the queried tag, returns true,
     *         else false.
     */
    public boolean hasCombatTag(CombatTag tag);
    

}
