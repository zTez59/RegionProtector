package fr.ztez59.regionprotector.api.region;

import org.bukkit.Location;

import java.util.List;

public interface IRegion {

    String getName();

    List<RPRegionRules> getRegionRules();

    List<String> getPlayersBackup();

    void addRegionRule(RPRegionRules regionRules);

    void removeRegionRule(RPRegionRules regionRules);

    boolean hasRegionRule(RPRegionRules regionRules);

    ILocation getFirstLocation();

    ILocation getSecondLocation();

    boolean inCuboid(Location location);
}
