package gamecore.location;

import gamecore.Inventory;
import gamecore.ItemDrop;
import gamecore.ZaphUtil;
import gamecore.entity.Entity;

import java.util.Arrays;
import java.util.List;

public final class Encounter {
    private String name;
    private boolean isCombat;
    private boolean rewardsWithExperience;
    private boolean rewardsWithItems;
    private Entity entityToFight;
    private List<ItemDrop> itemReward;
    private int experienceReward;
    private String image;

    private int likelihood;

    public static class Builder {
	private String name;
	private boolean isCombat = false;
	private boolean rewardsWithExperience = false;
	private boolean rewardsWithItems = false;
	private Entity entityToFight = null;
	private List<ItemDrop> itemReward = null;
	private int expereinceReward = 0;
	private String image = null;

	private int likelihood = 5;

	public Builder(String name) {
	    this.name = name;
	}

	public Builder itemsToDrop(ItemDrop... drops) {
	    itemReward = ZaphUtil.newList();
	    itemReward.addAll(Arrays.asList(drops));
	    this.rewardsWithItems = true;
	    return this;
	}

	public Builder setExperienceReward(int experience) {
	    this.expereinceReward = experience;
	    this.rewardsWithExperience = true;
	    return this;
	}

	public Builder setEntityToFight(Entity entity) {
	    this.entityToFight = entity;
	    this.isCombat = true;
	    return this;
	}

	public Builder setEncounterImage(String image) {
	    this.image = image;
	    return this;
	}

	public Builder setLikelihood(int likelihood) throws IllegalArgumentException {
	    if (likelihood > 0 && likelihood < 100) {
		this.likelihood = likelihood;
	    } else {
		throw new IllegalArgumentException("Encounter likelihood must be between 1 (absurdly rare) and 100 (extremely common)");
	    }

	    return this;
	}

	public Encounter build() {
	    return new Encounter(this);
	}
    }

    private Encounter(Builder builder) {
	this.isCombat = builder.isCombat;
	this.rewardsWithExperience = builder.rewardsWithExperience;
	this.rewardsWithItems = builder.rewardsWithItems;
	this.entityToFight = builder.entityToFight;
	this.itemReward = builder.itemReward;
	this.image = builder.image;
	this.name = builder.name;
	this.likelihood = builder.likelihood;
	this.experienceReward = builder.expereinceReward;
    }

    public boolean isCombat() {
	return isCombat;
    }

    public void setCombat(boolean isCombat) {
	this.isCombat = isCombat;
    }

    public boolean RewardsWithStats() {
	return rewardsWithExperience;
    }

    public boolean RewardsWithItems() {
	return rewardsWithItems;
    }

    public Entity getEntityToFight() {
	return entityToFight;
    }

    public Inventory getItemReward() {
	Inventory toReturn = new Inventory();
	for (ItemDrop drop : this.itemReward) {
	    if (drop.doesDrop()) {
		toReturn.addItem(drop.getItem(), drop.getDropAmount());
	    }
	}
	return toReturn;
    }

    public String getImage() {
	return image;
    }

    public void setImage(String image) {
	this.image = image;
    }

    public String getDisplayLine() {
	return name;
    }

    public int getLikelihood() {
	return likelihood;
    }

    public int getExperienceReward() {
	return this.experienceReward;
    }

    @Override
    public String toString() {
	return "Encounter [isCombat=" + isCombat + ",\n rewardsWithStats=" + rewardsWithExperience + ",\n  rewardsWithItems=" + rewardsWithItems + ",\n  entityToFight=" + entityToFight + ",\n  itemReward=" + itemReward + ",\n  image=" + image + ",\n  displayLine=" + name + ",\n  likelihood=" + likelihood + "]";
    }

}
