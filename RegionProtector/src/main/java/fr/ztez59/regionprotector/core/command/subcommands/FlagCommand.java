package fr.ztez59.regionprotector.core.command.subcommands;


import fr.ztez59.regionprotector.api.command.ICommand;
import fr.ztez59.regionprotector.api.region.IRegion;
import fr.ztez59.regionprotector.api.region.IRegions;
import fr.ztez59.regionprotector.api.region.RPRegionRules;
import fr.ztez59.regionprotector.core.api.RegionProtectorAPI;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class FlagCommand implements ICommand {

    @Override
    public String getName() {
        return "flag";
    }

    @Override
    public String getPermission() {
        return "rp.flag";
    }

    @Override
    public String getDescription() {
        return "Manage regions.";
    }

    @Override
    public String getUsage() { return "<region> <rule> <allow/deny> "; }

    @Override
    public boolean needPlayer() {
        return false;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList("f", "flags");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length == 4) {
            final String regionName = args[1].toLowerCase();
            final IRegions iRegions = RegionProtectorAPI.get().getIRegistrations().getIRegions();

            if (!iRegions.hasRegion(regionName)) {
                sender.sendMessage("La region n'existe pas.");
                return true;
            }

            final RPRegionRules rpRegionRules;

            try {
                rpRegionRules = RPRegionRules.valueOf(args[2].toUpperCase());
            } catch (Exception e) {
                sender.sendMessage("Ce flag n'existe pas.");
                return true;
            }

            final IRegion IRegion = iRegions.getRegion(regionName);
            final boolean value = Boolean.parseBoolean(args[3]);

            if (value) {
                if (IRegion.hasRegionRule(rpRegionRules)) {
                    sender.sendMessage("Cette région à déjà le flag");
                    return true;
                }

                IRegion.addRegionRule(rpRegionRules);
                sender.sendMessage("Flag ajouté !");
                return true;
            } else {
                if (!IRegion.hasRegionRule(rpRegionRules)) {
                    sender.sendMessage("Cette région n'as pas le flag");
                    return true;
                }

                IRegion.removeRegionRule(rpRegionRules);
                sender.sendMessage("Flag enlevé !");
                return true;
            }
        }

        return false;
    }

}
