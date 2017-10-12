package com.basaki;

import com.basaki.service.Hello;
import com.basaki.service.SayHelloService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * {@code BookApplication} represents the entry point for the Spring
 * boot application example.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/4/17
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.basaki"})
@Component
public class Application {

    @Autowired
    private SayHelloService service;

    @Autowired
    private Hello hello;

    public static void main(String[] args) {
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
