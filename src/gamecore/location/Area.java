package gamecore.location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Area {

    private List<Encounter> encounters;

    int experienceLevel;

    public Area(int experienceLevel) {

	this.encounters = new ArrayList<Encounter>();
	this.experienceLevel = experienceLevel;
    }
    
    public Area(int experienceLevel, Encounter... encounters) {
	this(experienceLevel);
    }
    
    public void addEncounter(Encounter encounter) {
	this.encounters.add(encounter);
    }
    
    public void addEncounters(Encounter... encounters) {
	for (Encounter encounter : encounters) {
	    this.addEncounter(encounter);
	}
    }
    
}