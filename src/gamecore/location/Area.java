package gamecore.location;

import gamecore.Dice;
import gamecore.Reference;

import java.util.ArrayList;
import java.util.List;

public final class Area {

    private List<Encounter> encounters;

    int experienceLevel;

    public Area(int level) {

	this.encounters = new ArrayList<Encounter>();
	this.experienceLevel = Reference.WHAT_EXPERIENCE(level);
    }
    
    public int getLevel() {
	return this.experienceLevel;
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
    
    public Encounter getRandomEncounter() {
	
	while (true) {
	    for (Encounter encounter : encounters) {
		if (encounter.getLikelihood() >= Dice.D100.roll()) {
		    return encounter;
		}
	    }
	}
    }
    
}