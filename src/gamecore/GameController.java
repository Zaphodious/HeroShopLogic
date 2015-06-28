package gamecore;

import gamecore.entity.EntityType;
import gamecore.entity.Hero;
import gamecore.entity.Monster;
import gamecore.location.Area;
import gamecore.location.Group;

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
	setSideKick(new Hero("Zabroni",EntityType.SIDEKICK));
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


    
}
