package gamecore.adventure;

public class CombatCommand {
    CombatCommandType type;
    String command;
    CombatUsable usable;
    public CombatCommand(CombatCommandType type, CombatUsable usable, String command) {
	super();
	this.type = type;
	this.command = command;
	this.usable = usable;
    }
    public CombatCommandType getType() {
        return type;
    }
    public String getCommand() {
        return command;
    }
    public CombatUsable getUsable() {
        return usable;
    }
    
    
    
    
}
