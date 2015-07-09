package gamecore.adventure;

import gamecore.Inventory;
import gamecore.entity.Attribute;
import gamecore.entity.Entity;
import gamecore.item.CombatUsable;
import gamecore.item.Item;
import gamecore.location.Encounter;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private boolean ongoing;
    private Entity playerCharacter;
    private Encounter encounter;
    private List<CombatCommand> currentCommands;

    public Scene(Entity playerCharacter, Encounter encounter) {
	super();
	this.playerCharacter = playerCharacter;
	this.encounter = encounter;
	this.currentCommands = null;
	this.ongoing = true;
    }

    public Entity getPlayerCharacter() {
	return playerCharacter;
    }

    public void setPlayerCharacter(Entity playerCharacter) {
	this.playerCharacter = playerCharacter;
    }

    public Encounter getEncounter() {
	return encounter;
    }

    public void setEncounter(Encounter encounter) {
	this.encounter = encounter;
    }

    public boolean stillOngoing() {
	return ongoing;
    }

    public List<CombatCommand> getCommands() {
	List<CombatCommand> toReturn = new ArrayList<CombatCommand>();
	for (CombatCommandType type : CombatCommandType.values()) {
	    switch (type) {
	    case ATTACK_WITH_WEAPON:
		toReturn.addAll(this.getWeaponCommand());
		break;
	    case RUN:
		toReturn.addAll(this.getRunCommand());
		break;
	    case USE_ITEM:
		toReturn.addAll(this.getItemCommands());
		break;
	    case USE_SKILL:
		toReturn.addAll(this.getSkillCommands());
		break;
	    default:
		toReturn.addAll(this.getWeaponCommand());
		break;
	    }
	}
	this.currentCommands = toReturn;
	return toReturn;

    }

    private List<CombatCommand> getSkillCommands() {
	return new ArrayList<CombatCommand>();
    }

    private List<CombatCommand> getItemCommands() {
	List<CombatCommand> toReturn = new ArrayList<CombatCommand>();
	Inventory playerInventory = this.playerCharacter.getInventory();
	for (Item item : playerInventory.getItems()) {
	    if (item instanceof CombatUsable) {
		CombatUsable combatItem = (CombatUsable) item;
		if (combatItem.hasCombatTag(CombatTag.USEABLE_FROM_INVENTORY)) {
		    toReturn.add(new CombatCommand(CombatCommandType.USE_ITEM, combatItem, "Use " + item.getName()));
		}
	    }
	}
	return toReturn;
    }

    private List<CombatCommand> getRunCommand() {
	return new ArrayList<CombatCommand>();
    }

    private List<CombatCommand> getWeaponCommand() {
	List<CombatCommand> toReturn = new ArrayList<CombatCommand>();

	toReturn.add(new CombatCommand(CombatCommandType.ATTACK_WITH_WEAPON, playerCharacter.getWeapon(), playerCharacter.getWeapon().getName()));

	return toReturn;
    }

    public RoundResult advanceRound(int commandIndex) {

	if (this.currentCommands == null) {
	    this.currentCommands = this.getCommands();
	}

	RoundResult toReturn = new RoundResult();
	
	if (this.currentCommands.get(commandIndex).getUsable().hasCombatTag(CombatTag.HARMS_OPPONENT)) {

	    toReturn.addMessage(playerCharacter.attackUsing(encounter.getEntityToFight(), this.currentCommands.get(commandIndex).getUsable()));
	    
	}

	if (this.currentCommands.get(commandIndex).getUsable().hasCombatTag(CombatTag.HEALS_PLAYER)) {
	    toReturn.addMessage(playerCharacter.attackUsing(this.getPlayerCharacter(), this.currentCommands.get(commandIndex).getUsable()));
	}
	boolean enemyAlive = (this.encounter.getEntityToFight().getAttribute(Attribute.CURRENT_HEALTH) > 0);
	if (enemyAlive) {
	    toReturn.addMessage(this.encounter.getEntityToFight().attack(playerCharacter));
	}
	
	
	boolean playerAlive = (this.playerCharacter.getAttribute(Attribute.CURRENT_HEALTH) > 0);
	this.ongoing = enemyAlive && playerAlive;
	if (!enemyAlive) {
	    toReturn.addMessage(MessageType.WIN_MESSAGE, "Contrats! You won!");
	} else if (!playerAlive) {
	    toReturn.addMessage(MessageType.LOSE_MESSAGE, "You lost. You crawl back to your hovel.");
	}
	return toReturn;
    }

}