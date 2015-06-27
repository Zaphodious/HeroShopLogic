package gamecore;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public abstract class Item {

    protected String name;
    protected ItemType type;

    protected int stackSize;
    protected int maxStackSize;
    protected boolean stackable;
    protected int potency;
    protected int weight;

    protected Item(String name, ItemType type) {
	this(name, 1, Reference.DEFAULT_MAX_STACK_SIZE, true, type, 3);
    }

    protected Item(String name, int stackSize, ItemType type) {
	this(name, stackSize, Reference.DEFAULT_MAX_STACK_SIZE, true, type, 3);
    }
    

    protected Item(String name, int stackSize, int maxStackSize, boolean stackable, ItemType type, int potency) {
	this(name, stackSize, Reference.DEFAULT_MAX_STACK_SIZE, true, type, 3, 2);
    }
    
    protected Item(String name, int stackSize, int maxStackSize, boolean stackable, ItemType type, int potency, int weight) {
	this.name = name;
	this.stackSize = stackSize;
	this.maxStackSize = maxStackSize;
	this.stackable = stackable;
	this.type = type;
	this.potency = potency;
	this.weight = weight;
    }
    
    public boolean isStackable() {
	return stackable;
    }

    public int getStackSize() {
	return stackSize;
    }

    public void addToStack(int value) {
	stackSize += value;
    }

    public void addToStack() {
	addToStack(1);
    }

    public String getName() {
	return name;
    }
    
    public ItemType getType() {
	return this.type;
    }
    
    public boolean use(Entity entity) {
	return false;
    }
    
    

    public int getPotency() {
        return potency;
    }

    protected void setPotency(int potency) {
        this.potency = potency;
    }

    @Override
    public int hashCode() {

	return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
	return this.hashCode() == o.hashCode();
    }

    @Override
    public String toString() {
	return name + ": , stackSize=" + stackSize + ", maxStackSize=" + maxStackSize;
    }
    
}
