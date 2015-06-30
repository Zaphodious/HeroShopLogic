package gamecore.adventure;

import gamecore.entity.Entity;

public interface CombatUsable {

    public int use(Entity entity);
    
    public int amountLeft();
    
    public boolean isOffensive();
    
}
