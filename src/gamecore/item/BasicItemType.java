package gamecore.item;

public enum BasicItemType implements ItemType {

    FOOD("food") {
	@Override
	public Item newItemInstance(ItemBuilder builder) {
	    return new Food(builder);
	}
    },
    WEAPON("weapon") {
	@Override
	public Item newItemInstance(ItemBuilder builder) {
	    return new Weapon(builder);
	}
    },
    ARMOR("armor") {
	@Override
	public Item newItemInstance(ItemBuilder builder) {
	    return new Armor(builder);
	}
    },
    MATERIAL("material") {
	@Override
	public Item newItemInstance(ItemBuilder builder) {
	    return new Material(builder);
	}
    },
    POTION("potion") {
	@Override
	public Item newItemInstance(ItemBuilder builder) {
	    return new Potion(builder);
	}
    },
    ARTIFACT("artifact") {
	@Override
	public Item newItemInstance(ItemBuilder builder) {
	    return new Artifact(builder);
	}
    };

    private String name;

    BasicItemType(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

}
