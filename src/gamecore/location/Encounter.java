package gamecore.location;

import gamecore.Inventory;
import gamecore.entity.Attribute;
import gamecore.entity.Entity;
import gamecore.item.Item;

import java.util.Map;

public class Encounter {

    private boolean isCombat;
    private boolean rewardsWithStats;
    private boolean rewardsWithItems;
    private Entity entityToFight;
    private Inventory itemReward;
    private Map<Attribute, Integer> statReward;
    private String image;
    private String displayLine;
    private float likelihood;

    public Encounter(boolean isCombat, boolean rewardsWithStats, boolean rewardsWithItems, Entity entityToFight, Inventory itemReward, Map<Attribute, Integer> statReward, String image, String displayLine, float likelihood) {
	this.isCombat = isCombat;
	this.rewardsWithStats = rewardsWithStats;
	this.rewardsWithItems = rewardsWithItems;
	this.entityToFight = entityToFight;
	this.itemReward = itemReward;
	this.statReward = statReward;
	this.image = image;
	this.displayLine = displayLine;
	this.likelihood = likelihood;
    }
    
    public Encounter(Entity entityToFight, String image, String displayLine, float likelihood, Item... toDrop) {
	this(true, false, true, entityToFight, new Inventory(toDrop), null, image, displayLine, likelihood);
    }

    public boolean isCombat() {
	return isCombat;
    }

    public void setCombat(boolean isCombat) {
	this.isCombat = isCombat;
    }

    public boolean RewardsWithStats() {
	return rewardsWithStats;
    }

    public void setRewardsWithStats(boolean rewardsWithStats) {
	this.rewardsWithStats = rewardsWithStats;
    }

    public boolean RewardsWithItems() {
	return rewardsWithItems;
    }

    public void setRewardsWithItems(boolean rewardsWithItems) {
	this.rewardsWithItems = rewardsWithItems;
    }

    public Entity getEntityToFight() {
	return entityToFight;
    }

    public void setEntityToFight(Entity entityToFight) {
	this.entityToFight = entityToFight;
    }

    public Inventory getItemReward() {
	return itemReward;
    }

    public void setItemReward(Inventory itemReward) {
	this.itemReward = itemReward;
    }

    public String getImage() {
	return image;
    }

    public void setImage(String image) {
	this.image = image;
    }

    public String getDisplayLine() {
	return displayLine;
    }

    public void setDisplayLine(String displayLine) {
	this.displayLine = displayLine;
    }

    public int[] getStatReward() {
	int[] toReturn = new int[Attribute.values().length];
	for (Attribute attribute:Attribute.values()) {
	    if (this.statReward.containsKey(attribute)) {
		
	    }
	}
	return toReturn;
    }

    public float getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(float likelihood) {
        this.likelihood = likelihood;
    }

    @Override
    public String toString() {
	return "Encounter [isCombat=" + isCombat + ",\n rewardsWithStats=" + rewardsWithStats + ",\n  rewardsWithItems=" + rewardsWithItems + ",\n  entityToFight=" + entityToFight + ",\n  itemReward=" + itemReward + ",\n  statReward=" + statReward + ",\n  image=" + image + ",\n  displayLine=" + displayLine + ",\n  likelihood=" + likelihood + "]";
    }
    
    
    
}
