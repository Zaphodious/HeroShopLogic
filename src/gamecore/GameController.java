package gamecore;

import gamecore.entity.Attribute;
import gamecore.entity.Entity;
import gamecore.entity.EntityBuilder;
import gamecore.entity.EntityType;
import gamecore.item.Item;
import gamecore.location.Area;
import gamecore.location.Encounter;
import gamecore.shop.EmploymentPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public final class GameController {

    private Entity playerCharacter;
    private Entity sideKick;
    private List<Entity> employees;
    private Area testingArea;
    
    private EmploymentPosition testEmployee;

    private GameController() {
	playerCharacter = new EntityBuilder("Marco", EntityType.PLAYER_CHARACTER).statsToBuff(Attribute.ATK_STRENGTH).statsToNerf(Attribute.DEF_DEXTERITY).build();
	
	this.sideKick = new EntityBuilder("Zabroni", EntityType.HERO).build();
	setEmployees(new ArrayList<Entity>());
	testingArea = new Area(1);
	testEmployee = new EmploymentPosition();

	testingArea.addEncounters(new Encounter.Builder("Hark! A Goblin!").setEntityToFight(new EntityBuilder("Goblin", EntityType.MONSTER)).setExperienceReward(3),
		new Encounter.Builder("Ew, a rat").setEntityToFight(new EntityBuilder("Rat", EntityType.MONSTER)).setExperienceReward(2).setLikelihood(50));
	
    }

    public void nameThePlayer(String name) {
	playerCharacter.rename(name);
    }

    public Entity getPlayerCharacter() {
	return playerCharacter;
    }

    public void setPlayerCharacter(Entity playerCharacter) {
	this.playerCharacter = playerCharacter;
    }

    public List<Entity> getEmployees() {
	return employees;
    }

    public void setEmployees(List<Entity> employees) {
	this.employees = employees;
    }

    public Entity getSideKick() {
	return sideKick;
    }

    public void setSideKick(Entity sideKick) {
	this.sideKick = sideKick;
    }

    public void reRoll(String name) {
	this.playerCharacter = new EntityBuilder(name, EntityType.PLAYER_CHARACTER).statsToBuff(Attribute.ATK_STRENGTH).statsToNerf(Attribute.DEF_DEXTERITY).build();
    }

    public Encounter randomEncounter() {
	return testingArea.getRandomEncounter();
    }

    private static GameController gameControllerInstance;

    public static GameController getInstance() {
	if (gameControllerInstance == null) {
	    gameControllerInstance = new GameController();
	    return gameControllerInstance;
	}
	return gameControllerInstance;
    }

    public EmploymentPosition getTestEmployee() {
	return testEmployee;
    }
    
    

}
