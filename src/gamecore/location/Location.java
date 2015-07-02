package gamecore.location;

import java.util.ArrayList;
import java.util.List;

public abstract class Location {

    private List<Area> areas;
    private String name;
    
    public Location(String name) {
	super();
	this.areas = new ArrayList<Area>();
	this.name = name;
    }
    public List<Area> getAreas() {
        return areas;
    }
    public String getName() {
        return name;
    }
    
    public void addArea(Area area) {
	areas.add(area);
    }
    
    
}
