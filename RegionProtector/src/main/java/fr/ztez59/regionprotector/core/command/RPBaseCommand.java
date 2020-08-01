package fr.ztez59.regionprotector.core.command;

import fr.ztez59.regionprotector.api.command.ICommand;
import fr.ztez59.regionprotector.core.configs.RPConfig;
import fr.ztez59.regionprotector.core.configs.RPVariables;
import fr.ztez59.regionprotector.core.api.RegionProtectorAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class RPBaseCommand implements CommandExecutor {

    private final List<ICommand> ICommands;

    public RPBaseCommand() { this.ICommands = RegionProtectorAPI.get().getIRegistrations().getICommands(); }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (this.checkPermission(sender, "rp.use")) {
            if (args.length == 0) {
                this.displayHelp(sender);
            } else {
                final String subCommandName = args[0];

                final Optional<ICommand> subCommand = this.ICommands
                        .stream()
                        .filter(rpCommand -> rpCommand.getName().equals(subCommandName) || rpCommand.getAliases().contains(subCommandName))
                        .findFirst();

                if (subCommand.isPresent()) {
                    final ICommand ICommand = subCommand.get();

                    if (!ICommand.getPermission().isEmpty() && !this.checkPermission(sender, ICommand.getPermission()))
                        return true;

                    if (ICommand.needPlayer() && !(sender instanceof Player)) {
                        sender.sendMessage(RPConfig.COMMAND_ONLYPLAYER.getString());
                        return true;
                    }

                    if (!ICommand.execute(sender, args)) {
                        this.displayHelp(sender);
                    }
                } else {
                    this.displayHelp(sender);
                }
            }
        }

        return true;
    }

    private void displayHelp(CommandSender sender) {
        sender.sendMessage(RPConfig.HELP_LINE.getString());
        sender.sendMessage(RPConfig.HELP_TITLE.getString()
                .replace(RPVariables.PLUGIN_VERSION.get(),
                        RegionProtectorAPI.get().getPlugin().getDescription().getVersion())
        );
        sender.sendMessage(" ");
        this.ICommands
                .forEach(rpCommand -> sender.sendMessage(RPConfig.HELP_FORMAT.getString()
                        .replace(RPVariables.COMMAND_NAME.get(), rpCommand.getName())
                        .replace(RPVariables.COMMAND_DESCRIPTION.get(), rpCommand.getDescription())
                        .replace(RPVariables.COMMAND_USAGE.get(), rpCommand.getUsage())
                        .replace(RPVariables.COMMAND_PERMISSION.get(), rpCommand.getPermission())
                ));
        sender.sendMessage(RPConfig.HELP_LINE.getString());
    }


    private boolean checkPermission(CommandSender sender, String permission) {
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(RPConfig.COMMAND_PERMISSION.getString());
            return false;
        }

        return true;
    }
}
