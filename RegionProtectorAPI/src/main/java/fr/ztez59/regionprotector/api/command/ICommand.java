package fr.ztez59.regionprotector.api.command;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface ICommand {

    String getName();

    String getPermission();

    String getDescription();

    String getUsage();

    boolean needPlayer();

    List<String> getAliases();

    boolean execute(CommandSender player, String[] args);

    default String buildArgs(String[] args, int start) {
        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = start; i < args.length; i++) stringBuilder.append(args[i]).append(" ");

        return stringBuilder.toString();
    }



}
