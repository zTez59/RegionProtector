package fr.ztez59.regionprotector.core.configs;

import fr.ztez59.regionprotector.core.api.RegionProtectorAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public enum RPConfig {


    STORAGE_NAME(false, "flatfile"),

    STORAGE_MYSQL_HOST(false, "localhost"),
    STORAGE_MYSQL_USER(false, "root"),
    STORAGE_MYSQL_DATABASE(false, "regionprotector"),
    STORAGE_MYSQL_PASSWORD(false, "monsupermotdepasse"),

    COMMAND_ONLYPLAYER(true, "&cSeul les joueurs peuvent éxecuter cette commande."),
    COMMAND_PERMISSION(true, "&cVous n'avez pas la permission."),

    HELP_LINE(true, "&f&m----------------------------------------"),
    HELP_TITLE(true, "                   &2§lRegionProtector &7- &c" + RPVariables.PLUGIN_VERSION.get()),
    HELP_FORMAT(true, "  &8» &2/rp " + RPVariables.COMMAND_NAME.get() + " " + RPVariables.COMMAND_USAGE.get() + "&8: &a" + RPVariables.COMMAND_DESCRIPTION.get() + " &c(" + RPVariables.COMMAND_PERMISSION.get() + ")"),
    RELOAD(true, "&aPlugin reload in &2" + RPVariables.TIME.get() + " ms"),
    COMMAND_REGION_CREATE(true, "&aLa région à bien été créée."),
    COMMAND_REGION_UNKNOWN(true, "&cLa région n'existe pas."),
    COMMAND_REGION_EXIST(true, "&cLa région existe déjà."),
    COMMAND_REGION_SELECTION(true, "&cVeuillez selectionez une zone.");

    private static HashMap<String, Object> values;

    public static void register() {
        values = new HashMap<>();

        final JavaPlugin iPlugin = RegionProtectorAPI.get().getPlugin();

        iPlugin.reloadConfig();
        final FileConfiguration fileConfiguration = iPlugin.getConfig();

        for (RPConfig rpConfig : values()) {
            String key = rpConfig.key;

            if (!fileConfiguration.isSet(key)) {
                fileConfiguration.set(key, rpConfig.value);
            }

            Object value = fileConfiguration.get(key);

            if (value instanceof String) value = ChatColor.translateAlternateColorCodes('&', (String) value);

            values.put(key, value);
        }

        iPlugin.saveConfig();
    }

    private String key;
    private final Object value;
    private final boolean isMessage;

    RPConfig(boolean isMessage, Object value) {
        this.isMessage = isMessage;
        this.key = this.name().toLowerCase().replace('_', '.');
        this.value = value;

        if (this.isMessage()) {
            this.key = "messages." + key;
        }
    }

    private Object getValue() {
        return values.get(this.key);
    }

    private boolean isMessage() {
        return isMessage;
    }

    public String getString() {
        return (String) this.getValue();
    }

    public int getInt() {
        return (int) this.getValue();
    }
}
