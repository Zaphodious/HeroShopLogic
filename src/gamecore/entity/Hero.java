package gamecore.entity;

import gamecore.location.Icon;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public class Hero extends Entity {

    public Hero(String unlocalizedName, EntityType type, int... newStats) {
	super(unlocalizedName, type, newStats);
    }

    public Hero(String unlocalizedName, EntityType type) {
	super(unlocalizedName, type);
    }

    @Override
    public String toString() {
	return super.toString();
    }
}
