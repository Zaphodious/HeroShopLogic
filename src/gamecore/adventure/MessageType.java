package gamecore.adventure;

public enum MessageType {

    FIGHTING_NAME("name of what you're fighting"),
    ATTACK_MISS("the attack missed"),
    OPPONENT_DAMAGE_TAKEN("damage to opponent"),
    OPPONENT_STAT_INCREASE("opponent stat increase"),
    OPPONENT_STAT_DECREASE("opponent stat decrease"),
    PLAYER_DAMAGE_TAKEN("dameage to player"),
    PLAYER_STAT_INCREASE("player stat increase"),
    PLAYER_STAT_DECREASE("player stat decrease"),
    WEAPON_MESSAGE("weapon message"),
    ITEM_GAIN("item drop"),
    GOLD_GAIN("gold drop"),
    EXPERIENCE_GAIN("experience drop"),
    WIN_MESSAGE("you won"),
    LOSE_MESSAGE("you lose");

    private String name;

    MessageType(String name) {
	this.name = name;
    }

    public String toString() {
	return this.name;
    }

}
