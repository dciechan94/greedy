package pl.block.bash.core.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = CoreConfigConstants.COMPONENT_SCAN_SCOPE)
public class ApplicationConfiguration {
}
