package fr.ztez59.regionprotector.core.tasks;

import fr.ztez59.regionprotector.api.region.RPRegionRules;
import fr.ztez59.regionprotector.api.storage.IStorage;
import fr.ztez59.regionprotector.core.api.RegionProtectorAPI;
import org.bukkit.Bukkit;

public class RegionsTask implements Runnable {

    private final IStorage IStorage;

    public RegionsTask() {
        this.IStorage = RegionProtectorAPI.get().getIRegistrations().getIStorage();
    }

    @Override
    public void run() {
        if (Bukkit.getOnlinePlayers().isEmpty() || this.IStorage.getDataList().isEmpty()) return;

        RegionProtectorAPI.get().getILogger().startDelay();

        Bukkit.getOnlinePlayers().forEach(player ->
                this.IStorage.getDataList().forEach(rpRegion -> {
                    final String playerName = player.getName();
                    final boolean inCuboid = rpRegion.inCuboid(player.getLocation());
                    final boolean contains = rpRegion.getPlayersBackup().contains(playerName);

                    if (inCuboid && !contains) {
                        if (rpRegion.hasRegionRule(RPRegionRules.TEST))
                            player.sendMessage("You are in " + rpRegion.getName() + " !");

                        rpRegion.getPlayersBackup().add(playerName);
                    } else if (!inCuboid && contains) {
                        player.sendMessage("You leave " + rpRegion.getName() + " ! ");
                        rpRegion.getPlayersBackup().remove(playerName);
                    }
                }));
        RegionProtectorAPI.get().getILogger().logDelay("%s MS");
    }


}
