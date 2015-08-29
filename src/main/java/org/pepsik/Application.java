package org.pepsik;

import org.pepsik.web.UserController;
import org.pepsik.web.UserSettingsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

@ImportResource(value = {"classpath:spring/business-config.xml"})
@ComponentScan(basePackages = {"org.pepsik"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = UserController.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = UserSettingsController.class)})
@EnableAutoConfiguration
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Inject
    private Environment env;

    /**
     * Initializes application with configured profiles
     * <p>
     * Spring profiles can be configured with a program arguments --spring.profiles.active=active-profile
     * </p>
     */

    @PostConstruct
    public void initApplication() {
        if (env.getActiveProfiles().length == 0)
            log.warn("No Spring profile configured, running with default configuration");
        else {
            log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Application.class);
        Environment env = app.run(args).getEnvironment();
        log.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

    //TODO setup profiles
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {

    }

    public static class WebMvcAutoConfigurationAdapter extends WebMvcConfigurerAdapter {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            if (!registry.hasMappingForPattern("/js/**")) {
                registry.addResourceHandler("/js/**").addResourceLocations(
                        "js/");
            }
        }
    }
}
