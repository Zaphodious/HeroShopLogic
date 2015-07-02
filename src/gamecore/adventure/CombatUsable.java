package gamecore.adventure;

import gamecore.entity.Entity;

import java.util.List;

public interface CombatUsable {

    public int use(Entity user, Entity target);
    
    public int amountLeft();
    
    public boolean hasCombatTag(CombatTag tag);
    
}
