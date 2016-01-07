package be.kdg.chat.prog4.tdd.frontend.config;

import be.kdg.chat.prog4.tdd.backend.config.BackendContextConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import be.kdg.chat.prog4.tdd.frontend.config.security.MultiHttpSecurityConfig;

@Configuration
@Import(value = { BackendContextConfig.class, MultiHttpSecurityConfig.class })
public class RootContextConfig { }
