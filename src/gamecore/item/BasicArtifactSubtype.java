package gamecore.item;

import gamecore.adventure.UseMessage;
import gamecore.entity.Attribute;

public enum BasicArtifactSubtype implements ItemSubtype {
    
    MONKEY_FIGURE(Attribute.ATK_INTELLIGENCE)
    
 {
	@Override
	public UseMessage activate(UseEvent useEvent) {
	    return null;
	}
    };
    
    Attribute relevantAttribute;
    
    BasicArtifactSubtype(Attribute a) {
	this.relevantAttribute = a;
    }

    @Override
    public abstract UseMessage activate(UseEvent useEvent);

    @Override
    public Attribute getRelevantAttribute() {
	// TODO Auto-generated method stub
	return null;
    }

}
