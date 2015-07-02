package gamecore.entity;


/**
 * Created by achyt_000 on 6/24/2015.
 */
public class Hero extends Entity {

    public Hero(String unlocalizedName, EntityType type) {
	super(unlocalizedName, type);
    }
    
    public Hero(String name, int experience, Attribute[] buffed, Attribute[] nerfed, EntityType type) {
	this(false,name,experience,buffed,nerfed,type);
    }
    
    public Hero(boolean random, String name, int experience, Attribute[] buffed, Attribute[] nerfed, EntityType type) {
	super(random,name,experience,buffed,nerfed,type);
    }

    @Override
    public String toString() {
	return super.toString();
    }
}
