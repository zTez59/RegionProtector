package fr.ztez59.regionprotector.core.regions;

import fr.ztez59.regionprotector.api.region.ILocation;
import fr.ztez59.regionprotector.api.region.IRegion;
import fr.ztez59.regionprotector.api.region.RPRegionRules;
import org.apache.commons.lang.math.IntRange;
import org.bukkit.Location;

import java.util.List;

public class RPRegion implements IRegion {

    private String name;
    private List<RPRegionRules> regionRules;
    private List<String> playersBackup;
    private RPLocation firstLocation, secondLocation;

    public RPRegion() {
    }


    public RPRegion(String name, List<RPRegionRules> regionRules, List<String> playersBackup, RPLocation firstLocation, RPLocation secondLocation) {
        this.name = name;
        this.regionRules = regionRules;
        this.playersBackup = playersBackup;
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
    }

    @Override
    public void addRegionRule(RPRegionRules regionRules) {
        this.regionRules.add(regionRules);
    }

    @Override
    public void removeRegionRule(RPRegionRules regionRules) {
        this.regionRules.remove(regionRules);
    }

    @Override
    public boolean hasRegionRule(RPRegionRules regionRules) {
        return this.regionRules.contains(regionRules);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<RPRegionRules> getRegionRules() {
        return this.regionRules;
    }

    @Override
    public List<String> getPlayersBackup() {
        return this.playersBackup;
    }

    @Override
    public ILocation getFirstLocation() {
        return this.firstLocation;
    }

    @Override
    public ILocation getSecondLocation() {
        return this.secondLocation;
    }

    @Override
    public boolean inCuboid(Location origin) {
        return origin.getWorld().getName().equals(this.getFirstLocation().getWorld()) && new IntRange(this.getFirstLocation().getX(), this.getSecondLocation().getX()).containsDouble(origin.getBlockX())
                && new IntRange(this.getFirstLocation().getY(), this.getSecondLocation().getY()).containsDouble(origin.getBlockY())
                && new IntRange(this.getFirstLocation().getZ(), this.getSecondLocation().getZ()).containsDouble(origin.getBlockZ());
    }
}
