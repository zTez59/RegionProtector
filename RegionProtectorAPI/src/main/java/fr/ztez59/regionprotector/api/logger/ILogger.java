package fr.ztez59.regionprotector.api.logger;

import org.bukkit.command.CommandSender;

public interface ILogger {

    void startDelay();

    void logDelay(String message);

    void sendDelay(CommandSender player, Object rpConfig);

    void log(String message);

    void error(String message);
}
