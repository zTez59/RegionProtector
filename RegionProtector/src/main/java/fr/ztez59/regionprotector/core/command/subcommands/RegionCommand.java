package fr.ztez59.regionprotector.core.command.subcommands;

import com.sk89q.worldedit.bukkit.selections.Selection;
import fr.ztez59.regionprotector.api.command.ICommand;
import fr.ztez59.regionprotector.api.managers.IRegistrations;
import fr.ztez59.regionprotector.api.region.IRegion;
import fr.ztez59.regionprotector.api.region.IRegions;
import fr.ztez59.regionprotector.core.api.RegionProtectorAPI;
import fr.ztez59.regionprotector.core.configs.RPConfig;
import fr.ztez59.regionprotector.core.regions.RPLocation;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class RegionCommand implements ICommand {

    @Override
    public String getName() {
        return "regions";
    }

    @Override
    public String getPermission() {
        return "rp.regions";
    }

    @Override
    public String getDescription() {
        return "Manage regions.";
    }

    @Override
    public String getUsage() {
        return "<list/create/delete> <name> ";
    }

    @Override
    public boolean needPlayer() {
        return false;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("r", "reg");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        final IRegistrations IRegistrations = RegionProtectorAPI.get().getIRegistrations();
        final List<IRegion> iStorage = IRegistrations.getIStorage().getDataList();

        if (args.length == 3) {
            final IRegions IRegions = IRegistrations.getIRegions();
            final String name = args[2].toLowerCase();

            switch (args[1].toLowerCase()) {
                case "create":
                    if (IRegions.hasRegion(name)) {
                        sender.sendMessage(RPConfig.COMMAND_REGION_EXIST.getString());
                        return true;
                    }

                    final Selection selection = RegionProtectorAPI.get().getWorldEdit().getSelection((Player) sender);

                    if (selection == null) {
                        sender.sendMessage(RPConfig.COMMAND_REGION_SELECTION.getString());
                        return true;
                    }

                    final Location firstPoint = selection.getMinimumPoint();
                    final Location secondPoint = selection.getMaximumPoint();

                    IRegions.addRegion(name,
                            new RPLocation(firstPoint.getWorld().getName(), firstPoint.getBlockX(), firstPoint.getBlockY(), firstPoint.getBlockZ()),
                            new RPLocation(secondPoint.getWorld().getName(), secondPoint.getBlockX(), secondPoint.getBlockY(), secondPoint.getBlockZ())
                    );
                    sender.sendMessage(RPConfig.COMMAND_REGION_CREATE.getString());

                    return true;
                case "delete":
                    /*
                    @TODO
                     */
                    return true;
                default:
                    return false;
            }
        } else if (args.length == 2) {
            if (args[1].toLowerCase().equals("list")) {
                if (iStorage.isEmpty()) {
                    sender.sendMessage("YA R LE FRERE !");
                } else {
                    iStorage.forEach(iRegion -> sender.sendMessage(iRegion.getName()));
                }
                return true;
            }
        }
        return false;
    }

}
