package com.basaki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * {@code BookApplication} represents the entry point for the Spring
 * boot application example.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/14/17
 */
@SpringBootApplication(scanBasePackages = {"com.basaki"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
