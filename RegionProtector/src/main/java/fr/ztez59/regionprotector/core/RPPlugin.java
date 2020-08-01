package fr.ztez59.regionprotector.core;


import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import fr.ztez59.regionprotector.api.IPlugin;
import fr.ztez59.regionprotector.api.logger.ILogger;
import fr.ztez59.regionprotector.api.managers.IRegistrations;
import fr.ztez59.regionprotector.core.api.RegionProtectorAPI;
import fr.ztez59.regionprotector.core.logger.RPLogger;
import fr.ztez59.regionprotector.core.managers.RPRegistrations;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RPPlugin extends JavaPlugin implements IPlugin {

    private IRegistrations IRegistrations;
    private ILogger ILogger;
    private WorldEditPlugin worldEditPlugin;

    @Override
    public void onLoad() {
        RegionProtectorAPI.setInstance(this);

        this.worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        this.ILogger = new RPLogger();
        this.IRegistrations = new RPRegistrations();
    }

    @Override
    public void onEnable() {
        this.ILogger.startDelay();
        this.IRegistrations.enable().whenComplete((v, e) -> this.ILogger.logDelay("Plugin enabled in %s ms"));
    }

    @Override
    public void onDisable() {
        this.IRegistrations.disable();
    }

    @Override
    public ILogger getILogger() {
        return this.ILogger;
    }

    @Override
    public IRegistrations getIRegistrations() {
        return this.IRegistrations;
    }

    @Override
    public WorldEditPlugin getWorldEdit() {
        return this.worldEditPlugin;
    }

    @Override
    public JavaPlugin getPlugin() {
        return this;
    }


}
