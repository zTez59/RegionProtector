package fr.ztez59.regionprotector.core.command.subcommands;

import fr.ztez59.regionprotector.api.command.ICommand;
import fr.ztez59.regionprotector.core.configs.RPConfig;
import fr.ztez59.regionprotector.api.logger.ILogger;
import fr.ztez59.regionprotector.core.api.RegionProtectorAPI;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class ReloadCommand implements ICommand {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getPermission() {
        return "rp.reload";
    }

    @Override
    public String getDescription() {
        return "Reload the plugin.";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public boolean needPlayer() {
        return false;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("rl", "restart");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        final ILogger iLogger = RegionProtectorAPI.get().getILogger();

        iLogger.startDelay();
        RPConfig.register();
        iLogger.sendDelay(sender, RPConfig.RELOAD);
        return true;
    }

}
