package fr.ztez59.regionprotector.core.storages;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr.ztez59.regionprotector.core.configs.RPConfig;
import fr.ztez59.regionprotector.api.region.IRegion;
import fr.ztez59.regionprotector.api.storage.IStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RPMysqlStorage implements IStorage {

    private List<IRegion> dataList;
    private HikariDataSource hikariDataSource;

    @Override
    public String getName() {
        return "mysql";
    }

    @Override
    public void load() {
        CompletableFuture.supplyAsync(() -> {
            final HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(String.format("jdbc:mysql:/%s/%s", RPConfig.STORAGE_MYSQL_HOST.getString(), RPConfig.STORAGE_MYSQL_DATABASE.getString()));
            hikariConfig.setUsername(RPConfig.STORAGE_MYSQL_USER.getString());
            hikariConfig.setPassword(RPConfig.STORAGE_MYSQL_PASSWORD.getString());
            hikariConfig.setMaximumPoolSize(4);

            return new HikariDataSource(hikariConfig);
        }).whenComplete((data, err) -> {
            if (err != null) err.printStackTrace();
            else {
                this.hikariDataSource = data;
                this.dataList = new ArrayList<>();

                /*
                @TODO
                 */
            }
        });

    }

    @Override
    public void saveAll() {
        /*
        @TODO
         */
    }

    @Override
    public void store(IRegion value) {
        /*
        @TODO
         */
    }

    @Override
    public void delete(String value) {
        /*
        @TODO
         */
    }

    @Override
    public List<IRegion> getDataList() {
        return this.dataList;
    }

}
