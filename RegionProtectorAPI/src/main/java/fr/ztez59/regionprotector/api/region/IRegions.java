package fr.ztez59.regionprotector.api.region;

import java.util.List;

public interface IRegions {

    void addRegion(String name, ILocation first, ILocation second);

    void removeRegion(String name);

    boolean hasRegion(String name);

    IRegion getRegion(String name);

    List<IRegion> getRegions();

}
