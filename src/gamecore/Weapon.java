package gamecore;

/**
 * Created by achyt_000 on 6/24/2015.
 */
public class Weapon extends Item {

    private int durability;
    public int salt;

    public Weapon(String name, int power, int durability, int salt) {
	super(name, 1, 1, false, ItemType.WEAPON, power);
	this.durability = durability;
	this.salt = (salt == 0) ? Reference.rand.nextInt() : salt;
    }

    public Weapon(String name, int power, int durability) {
	this(name, power, durability, 0);
    }

    public int getDurability() {
	return durability;
    }

    @Override
    public int hashCode() {
	return super.hashCode() + salt;
    }
    
    

    @Override
    public boolean use(Entity entity) {
	int damageDone = entity.hurt(this.getPotency());
	
	return (damageDone == 0) ? false:true;
    }

    @Override
    public String toString() {
	return name + ": " + "power=" + potency + ", durability=" + durability;
    }
}
