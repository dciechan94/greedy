package pl.block.bash.core.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pl.block.bash.ItemDAO;
import pl.block.bash.ItemDAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = {CoreConfigConstants.MONGO_REPOSITORIES_SCAN_SCOPE})
public class MongoConfiguration extends AbstractMongoConfiguration {

    private static final Logger LOGGER = LogManager.getLogger(MongoConfiguration.class);

    @Value("${mongodb.databaseName}")
    private String databaseName;

    @Value("${mongodb.databaseHost}")
    private String databaseHost;

    @Value("${mongodb.databasePort}")
    public int databasePort;

    @Value("${mongodb.isDatabaseAuthenticated}")
    public boolean isDatabaseAuthenticated;

    @Value("${mongodb.userName}")
    private String userName;

    @Value("${mongodb.userPassword}")
    private String userPassword;

    @Value("${mongodb.userDatabase}")
    private String userDatabase;

    @Bean(name="localDatabaseImpl")
    @Primary
    public MongoDatabase localDatabase() {
        LOGGER.info("Creating 'localDatabase' MongoDB connection bean. Host:port = {}:{}, database name = {}", databaseHost, databasePort, databaseName);
        try {
            MongoClient mongoClient = (MongoClient) mongo();
            return mongoClient.getDatabase(databaseName);
        } catch (Exception e) {
            LOGGER.error("Cannot connect to MongoDB database. Host:port = {}:{}, database name = {}", databaseHost, databasePort, databaseName, e);
            throw new RuntimeException("Cannot connect to MongoDB database", e);
        }
    }

    @Bean(name="localDatabaseImpl2")
    //@Primary
    public MongoDatabase localDatabase3() {
        LOGGER.info("Creating 'localDatabase3' MongoDB connection bean. Host:port = {}:{}, database name = {}", databaseHost, databasePort, "EduQuiz");
        try {
            MongoClient mongoClient = (MongoClient) mongo();
            return mongoClient.getDatabase("EduQuiz");
        } catch (Exception e) {
            LOGGER.error("Cannot connect to MongoDB database. Host:port = {}:{}, database name = {}", databaseHost, databasePort, "EduQuiz", e);
            throw new RuntimeException("Cannot connect to MongoDB database", e);
        }
    }

    @Bean
    public ItemDAO itemDAO() {
        LOGGER.info("Creating 'itemDAO' bean");
        return new ItemDAOImpl();

    }

    @Bean
    public EntityManager entityManager() {
        LOGGER.info("Creating 'entityManager' bean");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "persistenceUnitName" );
        return entityManagerFactory.createEntityManager();

    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public Mongo mongo() throws Exception {
        ServerAddress server = new ServerAddress(databaseHost, databasePort);
        if (isDatabaseAuthenticated) {
            LOGGER.info("Using credential authentication to create database connection. User = {}, users database = {}", userName, userDatabase);

            MongoCredential credentials = MongoCredential.createCredential(userName, userDatabase, userPassword.toCharArray());
            return new MongoClient(server, Collections.singletonList(credentials));
        }
        return new MongoClient(server);

    }
}
