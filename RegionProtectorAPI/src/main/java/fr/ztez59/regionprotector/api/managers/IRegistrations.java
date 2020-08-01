package fr.ztez59.regionprotector.api.managers;

import fr.ztez59.regionprotector.api.command.ICommand;
import fr.ztez59.regionprotector.api.region.IRegions;
import fr.ztez59.regionprotector.api.storage.IStorage;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IRegistrations {

    CompletableFuture<Void> enable();

    void disable();

    void registerCommands(Class<? extends ICommand>... commands);

    void registerListeners();

    void registerStorages(Class<? extends IStorage>... storages);

    void registerCommand(Class<? extends ICommand> command);

    void registerStorage(Class<? extends IStorage> storage);

    List<ICommand> getICommands();

    IStorage getIStorage();

    IRegions getIRegions();

}
