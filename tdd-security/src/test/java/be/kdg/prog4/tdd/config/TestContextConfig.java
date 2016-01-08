package be.kdg.prog4.tdd.config;

import be.kdg.prog4.tdd.backend.config.BackendContextConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ BackendContextConfig.class })
public class TestContextConfig { }
