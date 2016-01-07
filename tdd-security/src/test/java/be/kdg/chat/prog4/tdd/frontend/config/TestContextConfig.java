package be.kdg.chat.prog4.tdd.frontend.config;

import be.kdg.chat.prog4.tdd.backend.config.BackendContextConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ BackendContextConfig.class })
public class TestContextConfig { }
