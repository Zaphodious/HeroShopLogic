package gamecore.adventure;

public enum CombatTag {

    HARMS_OPPONENT("harms the opponent"),
    HEALS_PLAYER("can be used to heal"),
    USEABLE_FROM_INVENTORY("can be used from the inventory"),
    ENDS_COMBAT_ON_USE("ends combat on use");
    
    
   String name;
   
   CombatTag(String name) {
       this.name = name;
   }
    
}
