package com.basaki.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

/**
 * {@code CustomEnvironmentPostProcessor} add new application configurable
 * properties.
 * <p>
 *
 * @author Indra Basak
 * @since 10/13/17
 */
public class CustomEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String APPLICATION_CONFIGURATION_PROPERTIES =
            "applicationConfigurationProperties";

    private static final String SYSTEM_PATTERN = "%%mysystemName%%";

    @Override
    public void postProcessEnvironment(
            ConfigurableEnvironment environment,
            SpringApplication springApplication) {

        MutablePropertySources sources = environment.getPropertySources();
        Properties modifiedProps = new Properties();

        if (sources.contains(APPLICATION_CONFIGURATION_PROPERTIES)) {
            PropertySource<?> source = sources.get(
                    APPLICATION_CONFIGURATION_PROPERTIES);
            if (source instanceof EnumerablePropertySource) {
                EnumerablePropertySource target =
                        (EnumerablePropertySource) source;
                for (String name : target.getPropertyNames()) {
                    Object value = target.getProperty(name);
                    if (value instanceof String) {
                        if (((String) value).contains(SYSTEM_PATTERN)) {
                            try {
                                String newValue = ((String) value).replaceAll(
                                        SYSTEM_PATTERN,
                                        hostname());
                                modifiedProps.put(name, newValue);
                            } catch (UnknownHostException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        if (!modifiedProps.isEmpty()) {
            sources.addFirst(new PropertiesPropertySource("customAppProperties",
                    modifiedProps));
        }
    }

    public String hostname() throws UnknownHostException {
        String hostName = InetAddress.getLocalHost().getHostName();
        int index = hostName.indexOf(".local");
        hostName = (index > 0) ? hostName.substring(0, index) : hostName;
        return hostName;
    }
}
