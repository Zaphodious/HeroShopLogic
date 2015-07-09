package gamecore.location;

import gamecore.ZaphUtil;

import java.util.List;

public abstract class AbstractLocation implements Location {

    private List<Area> areas;
    private String name;

    public AbstractLocation(String name) {
	this.areas = ZaphUtil.newList();
	this.name = name;
    }

    @Override
    public List<Area> getAreas() {
	return areas;
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public void addArea(Area area) {
	areas.add(area);
    }

}
