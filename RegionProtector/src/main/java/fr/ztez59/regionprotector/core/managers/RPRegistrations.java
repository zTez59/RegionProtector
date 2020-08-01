package fr.ztez59.regionprotector.core.managers;

import fr.ztez59.regionprotector.api.command.ICommand;
import fr.ztez59.regionprotector.core.configs.RPConfig;
import fr.ztez59.regionprotector.api.region.IRegions;
import fr.ztez59.regionprotector.api.storage.IStorage;
import fr.ztez59.regionprotector.core.api.RegionProtectorAPI;
import fr.ztez59.regionprotector.core.command.RPBaseCommand;
import fr.ztez59.regionprotector.core.command.subcommands.FlagCommand;
import fr.ztez59.regionprotector.core.command.subcommands.RegionCommand;
import fr.ztez59.regionprotector.core.command.subcommands.ReloadCommand;
import fr.ztez59.regionprotector.core.regions.RPRegions;
import fr.ztez59.regionprotector.core.storages.RPFileStorage;
import fr.ztez59.regionprotector.core.storages.RPMysqlStorage;
import fr.ztez59.regionprotector.core.tasks.RegionsTask;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RPRegistrations implements fr.ztez59.regionprotector.api.managers.IRegistrations {

    private List<IStorage> IStorages;
    private List<ICommand> ICommands;

    private IStorage IStorage;
    private IRegions IRegions;

    @Override
    public CompletableFuture<Void> enable() {
        return CompletableFuture.runAsync(() -> {
            this.ICommands = new ArrayList<>();
            this.IStorages = new ArrayList<>();

            RPConfig.register();

            this.registerStorages(
                    RPFileStorage.class,
                    RPMysqlStorage.class
            );

            this.registerCommands(
                    RegionCommand.class,
                    FlagCommand.class,
                    ReloadCommand.class
            );

            this.registerListeners();

            this.IStorage.load();

            this.IRegions = new RPRegions();

            Bukkit.getScheduler().runTaskTimerAsynchronously(RegionProtectorAPI.get().getPlugin(), new RegionsTask(), 5L, 5L);
        });
    }

    @Override
    public void disable() {
        this.ICommands.clear();
        this.IStorage.saveAll();
    }


    @Override
    public void registerCommands(Class<? extends ICommand>... commands) {
        Arrays.asList(commands).forEach(this::registerCommand);

        Bukkit.getPluginCommand("regionprotector").setExecutor(new RPBaseCommand());
    }

    @Override
    public void registerListeners() {

    }

    @Override
    public void registerStorages(Class<? extends IStorage>... storages) {
        Arrays.asList(storages).forEach(this::registerStorage);

        this.IStorage = this.IStorages.stream().filter(rpStorage -> rpStorage.getName().equals(RPConfig.STORAGE_NAME.getString().toLowerCase())).findFirst().orElseGet(() -> this.IStorages.stream().filter(rpStorage -> rpStorage.getName().equals("flatfile")).findFirst().orElse(null));
    }

    @Override
    public void registerCommand(Class<? extends ICommand> command) {
        try {
            this.ICommands.add(command.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerStorage(Class<? extends IStorage> storage) {
        try {
            this.IStorages.add(storage.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ICommand> getICommands() {
        return this.ICommands;
    }

    @Override
    public IStorage getIStorage() {
        return this.IStorage;
    }

    @Override
    public IRegions getIRegions() {
        return this.IRegions;
    }

}
