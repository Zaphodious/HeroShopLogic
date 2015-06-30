package gamecore.adventure;

public enum CombatCommandType {
    
    ATTACK_WITH_WEAPON("attack with weapon"),
    USE_SKILL("use skill"),
    USE_ITEM("use item"),
    RUN("run away");
    
    String name;
    
    CombatCommandType(String name) {
	this.name = name;
    }
    
}
