package be.kdg.prog4.tdd.frontend.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.JstlView;

/**
 * Handles configuration of all presentation layer oriented components and helper classes such
 * as formatters and validators, internationalization (i18n), view resolvers, static resources (CSS, HTML, js, images).
 */
@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = "be.kdg.prog4.tdd.frontend",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
        }
)
public class WebContextConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/pages/", ".jsp")
                .viewClass(JstlView.class);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
