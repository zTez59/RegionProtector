package fr.ztez59.regionprotector.api.storage;

import fr.ztez59.regionprotector.api.region.IRegion;

import java.util.List;

public interface IStorage {

    String getName();

    void load();

    void saveAll();

    void store(IRegion value);

    void delete(String name);

    List<IRegion> getDataList();

}
