package fr.ztez59.regionprotector.core.regions;

import fr.ztez59.regionprotector.api.region.ILocation;

public class RPLocation implements fr.ztez59.regionprotector.api.region.ILocation {

    private String world;
    private int x, y, z;

    public RPLocation() { }

    public RPLocation(ILocation ILocation){
        this.world = ILocation.getWorld();
        this.x = ILocation.getX();
        this.y = ILocation.getY();
        this.z = ILocation.getZ();
    }

    public RPLocation(String world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String getWorld() {
        return world;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

}
