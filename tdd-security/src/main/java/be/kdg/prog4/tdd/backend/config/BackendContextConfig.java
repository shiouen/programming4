package be.kdg.prog4.tdd.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

/**
 * Main configuration file for the backend.
 * Configures backend services and repositories
 */

@Configuration
@ComponentScan(
        basePackages = "be.kdg.prog4.tdd.backend",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
        }
)
@Import({ DataSourceConfig.class })
public class BackendContextConfig { }