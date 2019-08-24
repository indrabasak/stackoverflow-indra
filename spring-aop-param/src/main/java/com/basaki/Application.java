package com.basaki;

import com.basaki.service.Hello;
import com.basaki.service.SayHelloService;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

/**
 * {@code BookApplication} represents the entry point for the Spring
 * boot application example.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/4/17
 */
@SpringBootApplication(scanBasePackages = {"com.basaki"})
@EnableAspectJAutoProxy
@EnableScheduling
//@EnableOAuth2Sso
@Component
public class Application {

    @Autowired
    private SayHelloService service;

    @Autowired
    private Hello hello;

    public static void main(String[] args) {
        InputStream istream =
                Application.class.getClassLoader().getResourceAsStream(
                        "application.yml");
        Yaml yaml = new Yaml();
        Map map = (Map) yaml.load(istream);
        map.entrySet().forEach(e -> System.out.println(e));

        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setResources(new ClassPathResource("application.yml"));
        Properties props = yamlFactory.getObject();
        Enumeration enumeration = props.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            System.out.println(key + " -> " + props.getProperty(key));
        }

        System.out.println(
                "info.description: " + props.getProperty("info.description"));

        SpringApplication.run(Application.class, args);
    }

    public void sayHello() {
        System.out.println("888888");
    }

    @PostConstruct
    public void init() {
        service.message("test");
        service.justAnotherStaticMethod();
        hello.getGreetingA();
        hello.getGreetingB();
    }
}
