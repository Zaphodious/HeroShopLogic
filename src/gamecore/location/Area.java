package gamecore.location;

import gamecore.Dice;
import gamecore.Reference;

import java.util.ArrayList;
import java.util.List;

public final class Area {

    private List<Encounter.Builder> encounters;

    int experienceLevel;

    public Area(int level) {

	this.encounters = new ArrayList<Encounter.Builder>();
	this.experienceLevel = Reference.WHAT_EXPERIENCE(level);
    }

    public int getLevel() {
	return this.experienceLevel;
    }

    public Area(int experienceLevel, Encounter... encounters) {
	this(experienceLevel);
    }

    public void addEncounter(Encounter.Builder encounterBuilder) {
	this.encounters.add(encounterBuilder);
    }

    public void addEncounters(Encounter.Builder... encounters) {
	for (Encounter.Builder encounter : encounters) {
	    this.addEncounter(encounter);
	}
    }

    public Encounter getRandomEncounter() {

	while (true) {
	    for (Encounter.Builder encounter : encounters) {
		if (encounter.getLikelihood() >= Dice.D100.roll()) {
		    return encounter.build();
		}
	    }
	}
    }

}