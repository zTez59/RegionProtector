package fr.ztez59.regionprotector.core.regions;

import fr.ztez59.regionprotector.api.region.ILocation;
import fr.ztez59.regionprotector.api.region.IRegion;
import fr.ztez59.regionprotector.api.region.IRegions;
import fr.ztez59.regionprotector.api.storage.IStorage;
import fr.ztez59.regionprotector.core.api.RegionProtectorAPI;

import java.util.ArrayList;
import java.util.List;

public class RPRegions implements IRegions {

    private IStorage IStorage;

    public RPRegions() {
        this.IStorage = RegionProtectorAPI.get().getIRegistrations().getIStorage();
    }

    @Override
    public void addRegion(String name, ILocation first, ILocation second) {
        this.IStorage.store(new RPRegion(name, new ArrayList<>(), new ArrayList<>(), new RPLocation(first), new RPLocation(second)));
    }

    @Override
    public void removeRegion(String name) {
        this.IStorage.delete(name);
    }

    @Override
    public boolean hasRegion(String name) {
        return this.IStorage.getDataList().stream().anyMatch(rpRegion -> rpRegion.getName().equals(name));
    }

    @Override
    public IRegion getRegion(String name) {
        return this.IStorage.getDataList().stream().filter(rpRegion -> rpRegion.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<IRegion> getRegions() {
        return this.IStorage.getDataList();
    }

}
