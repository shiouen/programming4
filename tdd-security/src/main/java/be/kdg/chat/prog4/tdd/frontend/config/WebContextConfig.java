package be.kdg.chat.prog4.tdd.frontend.config;

import java.util.List;

import org.springframework.context.annotation.*;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.JstlView;

/**
 * Handles configuration of all presentation layer oriented components and helper classes such
 * as formatters and validators, internationalization (i18n), view resolvers, static resources (CSS, HTML, js, images).
 */
@Configuration
@EnableWebMvc
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@ComponentScan(
        basePackages = "be.kdg.chat.prog4.tdd.frontend",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
        }
)

public class WebContextConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry)
    {
        registry.jsp("/WEB-INF/pages/", ".jsp")
                .viewClass(JstlView.class);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }

}
