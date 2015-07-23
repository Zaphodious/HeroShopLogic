package gamecore.item;

import gamecore.adventure.UseMessage;
import gamecore.entity.Attribute;

public interface ItemSubtype {

    public UseMessage activate(UseEvent useEvent);
    
    public Attribute getRelevantAttribute();
    
    
}
