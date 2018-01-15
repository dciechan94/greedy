package pl.block.bash.services;

import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class SomeDbService {
    private static final Logger LOGGER = LogManager.getLogger(SomeDbService.class);

    @Autowired
    private MongoDatabase database;

    public String yell() {
        return "OK " + database.getName();
    }

    public void setLocalDatabase(MongoDatabase localDatabase) {
        this.database = localDatabase;
    }

}
