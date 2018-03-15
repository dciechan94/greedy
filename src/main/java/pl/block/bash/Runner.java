package pl.block.bash;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import pl.block.bash.core.config.ApplicationConfiguration;
import pl.block.bash.core.config.CoreConfigConstants;


public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        LOGGER.info("Starting application");
        SpringApplication.run(ApplicationConfiguration.class, args);
        LOGGER.info("Application started successfully");
    }
}
