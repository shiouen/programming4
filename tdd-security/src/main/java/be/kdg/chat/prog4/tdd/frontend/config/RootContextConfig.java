package be.kdg.chat.prog4.tdd.frontend.config;

import be.kdg.chat.prog4.tdd.backend.config.BackendContextConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import be.kdg.chat.prog4.tdd.frontend.config.security.SecurityConfig;

@Configuration
@Import(value = { BackendContextConfig.class, SecurityConfig.class })
public class RootContextConfig { }
