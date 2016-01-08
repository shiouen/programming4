package be.kdg.prog4.tdd.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Responsible for creating the H2 Datasource for a Server Mode In-Memory Database
 * using ;DB_CLOSE_DELAY=-1 options to keep data for life-time of JVM process.
 */
@Configuration
public class DataSourceConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
