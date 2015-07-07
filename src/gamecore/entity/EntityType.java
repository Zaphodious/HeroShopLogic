package gamecore.entity;

/**
 * Identifying tags for Entities.
 * 
 * @author Alex Chythlook
 *
 */
public enum EntityType {

    /**
     * The player character. Only the player character should have this tag.
     */
    PLAYER_CHARACTER("pc"),
    /**
     * Entities encountered that are not hostile to the player.
     */
    HERO("hero"),
    /**
     * Entities encounter that are hostile to the player.
     */
    MONSTER("monster");

    private String name;

    EntityType(String name) {
	this.name = name;
    }

    /**
     * For testing purposes, this makes it somewhat easier to output the type of
     * Entity to the console.
     * 
     * @return The name of the EntityType as a String.
     */
    public String getName() {
	return name;
    }

}
