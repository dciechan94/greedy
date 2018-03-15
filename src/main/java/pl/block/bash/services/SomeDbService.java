package pl.block.bash.services;

import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pl.block.bash.Item;
import pl.block.bash.ItemDAO;

import java.time.LocalDate;
import java.util.Date;

public class SomeDbService {
    private static final Logger LOGGER = LogManager.getLogger(SomeDbService.class);

    @Autowired
    private MongoDatabase database;

    @Autowired
    private ItemDAO itemDAO;

    public String yell() {
        Item item = new Item("apple", "big and red from poland", new Date(System.currentTimeMillis()+1000*60*60*24));
        itemDAO.save(item);

        return "OK " + database.getName() + " " + itemDAO.findAll();
    }

    public void setLocalDatabase(MongoDatabase localDatabase) {
        this.database = localDatabase;
    }

}
