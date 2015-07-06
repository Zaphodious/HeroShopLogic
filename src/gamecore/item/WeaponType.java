package gamecore.item;

import gamecore.entity.Attribute;
import gamecore.entity.Entity;

/**
 * Each Weapon object stores a reference to a WeaponType object, which it uses to determine its behavior on use(user,target).
 * @author Alex Chythlook
 *
 */
public interface WeaponType {

    /**
     * Called when the containing Weapon's use(user,target) method is called.
     * @param weapon The weapon that has called this method.
     * @param user The user Entity of the Weapon.
     * @param target The target Entity of the weapon. Should always be the opponent.
     * @return The amount of damage done by the weapon, which is then returned by the Weapon's use function.
     */
    public int attack(Weapon weapon, Entity user, Entity target);
    
    /**
     * Combat math requires an Attribute, which it then uses to determine if an attack hits or misses. Should always be an ATK_ Attribute, except in special circumstances.
     * @return The Attribute responsible for determining a successful hit.
     */
    public Attribute getAttackUsing();
    
}
