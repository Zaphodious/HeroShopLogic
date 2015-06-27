package gamecore;

public class Material extends Item {
    
    public Material(String name) {
	super(name, ItemType.MATERIAL);
	// TODO Auto-generated constructor stub
    }
    
    public Material(String name, int stackSize) {
	super(name, stackSize, ItemType.MATERIAL);
	// TODO Auto-generated constructor stub
    }

}
