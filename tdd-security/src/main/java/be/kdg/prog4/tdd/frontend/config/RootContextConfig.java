package be.kdg.prog4.tdd.frontend.config;

import be.kdg.prog4.tdd.backend.config.BackendContextConfig;
import be.kdg.prog4.tdd.frontend.config.security.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { BackendContextConfig.class, SecurityConfig.class })
public class RootContextConfig { }
