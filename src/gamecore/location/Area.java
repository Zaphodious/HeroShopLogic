package gamecore.location;

import gamecore.Reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Area {

    private List<Encounter> encounters;

    int experienceLevel;

    public Area(int level) {

	this.encounters = new ArrayList<Encounter>();
	this.experienceLevel = Reference.WHAT_EXPERIENCE(level);
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