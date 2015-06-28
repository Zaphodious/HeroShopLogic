package gamecore.location;

import java.util.ArrayList;
import java.util.List;

public class Area {

    private List<Encounter> encounters;

    int experienceLevel;

    public Area(int experienceLevel) {

	this.encounters = new ArrayList<Encounter>();
	this.experienceLevel = experienceLevel;
    }
    
    public void addMapObject(Encounter mapObject) {
	this.encounters.add(mapObject);
    }
    
}