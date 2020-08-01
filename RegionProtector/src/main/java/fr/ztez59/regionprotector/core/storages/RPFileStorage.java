package fr.ztez59.regionprotector.core.storages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.ztez59.regionprotector.api.region.IRegion;
import fr.ztez59.regionprotector.api.storage.IStorage;
import fr.ztez59.regionprotector.core.api.RegionProtectorAPI;
import fr.ztez59.regionprotector.core.regions.RPRegion;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class RPFileStorage implements IStorage {

    private List<IRegion> dataList;
    private Gson gson;
    private File dataFile;

    @Override
    public String getName() {
        return "flatfile";
    }

    @Override
    public void load() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();

        this.dataFile = new File(RegionProtectorAPI.get().getPlugin().getDataFolder(), "data.json");
        try {
            if (!this.dataFile.exists()) {
                this.dataFile.createNewFile();
            }

            this.dataList = this.gson.fromJson(FileUtils.readFileToString(this.dataFile, Charset.forName("UTF-8")), new TypeToken<ArrayList<RPRegion>>() {
            }.getType());

            if (this.dataList == null) this.dataList = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveAll() {
        try {
            FileUtils.writeStringToFile(this.dataFile, this.gson.toJson(this.dataList), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(IRegion value) {
        this.dataList.add(value);
    }

    @Override
    public void delete(String name) {
        this.dataList.removeIf(rpRegion -> rpRegion.getName().equals(name));
    }

    @Override
    public List<IRegion> getDataList() {
        return this.dataList;
    }
}
