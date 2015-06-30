package gamecore;

import gamecore.entity.EntityType;
import gamecore.entity.Hero;
import gamecore.entity.Monster;
import gamecore.item.Weapon;
import gamecore.location.Encounter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public class GameController {

    private Hero playerCharacter;
    private Hero sideKick;
    private List<Hero> employees;

    public GameController() {
	playerCharacter = new Hero("Marco", EntityType.PLAYER_CHARACTER);
	setSideKick(new Hero("Zabroni", EntityType.SIDEKICK));
	setEmployees(new ArrayList<Hero>());
    }

    public void nameThePlayer(String name) {
	playerCharacter.rename(name);
    }

    public Hero getPlayerCharacter() {
	return playerCharacter;
    }

    public void setPlayerCharacter(Hero playerCharacter) {
	this.playerCharacter = playerCharacter;
    }

    public List<Hero> getEmployees() {
	return employees;
    }

    public void setEmployees(List<Hero> employees) {
	this.employees = employees;
    }

    public Hero getSideKick() {
	return sideKick;
    }

    public void setSideKick(Hero sideKick) {
	this.sideKick = sideKick;
    }

    public void reRoll(String name) {
	this.playerCharacter = new Hero(name, EntityType.PLAYER_CHARACTER);
    }
    
    public Encounter randomEncounter() {
	Monster goblin = new Monster("Goblin", 100, EntityType.GOBLIN);
	goblin.equipWeapon(new Weapon("Axe", 2, 100));
	Encounter toReturn = new Encounter(goblin, null, null, 0, new Weapon("Hatchet", 2, 100));
	toReturn.setDisplayLine("You're fighting a Goblin! Rawr!");
	return toReturn;
    }

}
