package fr.ztez59.regionprotector.core.logger;

import fr.ztez59.regionprotector.core.configs.RPConfig;
import fr.ztez59.regionprotector.core.configs.RPVariables;
import fr.ztez59.regionprotector.api.logger.ILogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class RPLogger implements ILogger {

    private long lastTime = -1;

    @Override
    public void startDelay() {
        this.lastTime = System.currentTimeMillis();
    }

    @Override
    public void logDelay(String message) {
        try {
            this.log(String.format(message, this.handleDelay()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendDelay(CommandSender player, Object object) {
        RPConfig rpConfig = (RPConfig) object;

        try {
            player.sendMessage(rpConfig.getString()
                    .replace(RPVariables.TIME.get(), String.valueOf(this.handleDelay()))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', this.getPrefix() + message));
    }

    @Override
    public void error(String message) {
        this.log("&c" + message);
    }

    private long handleDelay() throws Exception {
        if (this.lastTime == -1) {
            this.error("Start the delay before log him.");
            throw new Exception();
        }

        long value = System.currentTimeMillis() - this.lastTime;

        this.lastTime = -1;

        return value;
    }

    private String getPrefix() {
        return "§2[RegionProtector] §7: §a";
    }
}
