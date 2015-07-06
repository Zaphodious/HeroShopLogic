package gamecore.item;



public enum BasicItemType implements ItemType {

    FOOD("food") {
	@Override
	public Item newItemInstance(ItemBuilder builder) {
	    // TODO Auto-generated method stub
	    return new Food(builder);
	}
    },
    WEAPON("weapon") {
	@Override
	public Item newItemInstance(ItemBuilder builder) {
	    // TODO Auto-generated method stub
	    return new Weapon(builder);
	}
    },
    ARMOR("armor") {
	@Override
	public Item newItemInstance(ItemBuilder builder) {
	    // TODO Auto-generated method stub
	    return new Armor(builder);
	}
    },
    MATERIAL("material") {
	@Override
	public Item newItemInstance(ItemBuilder builder) {
	    // TODO Auto-generated method stub
	    return new Material(builder);
	}
    },
    POTION("potion") {
	@Override
	public Item newItemInstance(ItemBuilder builder) {
	    // TODO Auto-generated method stub
	    return new Potion(builder);
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
