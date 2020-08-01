package fr.ztez59.regionprotector.core.api;

import fr.ztez59.regionprotector.api.IPlugin;

public class RegionProtectorAPI {

    private static IPlugin iPlugin;

    public static void setInstance(IPlugin instance) {
        if (iPlugin != null) new Exception("Do not try to set other instance").printStackTrace();
        else iPlugin = instance;
    }

    public static IPlugin get() {
        if (iPlugin == null) new Exception("Instance cannot be null").printStackTrace();
        return iPlugin;
    }
}
