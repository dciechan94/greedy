package pl.block.bash.services.config;

import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.block.bash.core.config.CoreConfigConstants;
import pl.block.bash.services.SomeDbService;

@Configuration
@ComponentScan(basePackages = CoreConfigConstants.COMPONENT_SCAN_SCOPE)
public class ServicesBeanConfig {


    @Bean(name = "someMongoDbService")
    public SomeDbService pluginDbService(MongoDatabase localDatabase2) {

        SomeDbService someDbService = new SomeDbService();
        someDbService.setLocalDatabase(localDatabase2);
        return someDbService;
    }
}
