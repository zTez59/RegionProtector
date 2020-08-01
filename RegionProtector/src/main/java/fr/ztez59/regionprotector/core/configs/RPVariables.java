package fr.ztez59.regionprotector.core.configs;

public enum RPVariables {

    COMMAND_NAME("{COMMAND_NAME}"),
    COMMAND_DESCRIPTION("{COMMAND_DESCRIPTION}"),
    COMMAND_PERMISSION("{COMMAND_PERMISSION}"),
    COMMAND_USAGE("{COMMAND_USAGE}"),
    PLUGIN_VERSION("{PLUGIN_VERSION}"),
    TIME("{TIME}");

    private final String name;

    RPVariables(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}