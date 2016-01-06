package be.kdg.chat.prog4.tdd.frontend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import be.kdg.chat.prog4.tdd.backend.config.BackendContextConfig;
//import be.kdg.chat.prog4.tdd.frontend.config.security.MultiHttpSecurityConfig;

@Configuration
@Import(value = { BackendContextConfig.class/*, MultiHttpSecurityConfig.class*/ })
@ComponentScan(
        basePackages = "be.kdg.chat.prog4.tdd.frontend",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
        }
)
public class RootContextConfig { }
