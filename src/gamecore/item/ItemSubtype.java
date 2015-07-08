package gamecore.item;

import gamecore.entity.Attribute;
import gamecore.entity.Entity;

public interface ItemSubtype {

    public int activate(Item item, Entity user, Entity target);
    
    public Attribute getRelevantAttribute();
    
}
