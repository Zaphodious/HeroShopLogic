package gamecore.location;

import java.util.List;

public interface Location {

    
    public List<Area> getAreas();
    
    public String getName();
    
    public void addArea(Area area);
}
