package fr.ztez59.regionprotector.api;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import fr.ztez59.regionprotector.api.logger.ILogger;
import fr.ztez59.regionprotector.api.managers.IRegistrations;
import org.bukkit.plugin.java.JavaPlugin;

public interface IPlugin {

    ILogger getILogger();

    IRegistrations getIRegistrations();

    WorldEditPlugin getWorldEdit();

    JavaPlugin getPlugin();
}
