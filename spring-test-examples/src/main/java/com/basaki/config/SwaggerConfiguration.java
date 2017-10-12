package com.basaki.config;

import com.basaki.model.Book;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;*/

/**
 * {@code SwaggerConfiguration} is the configuration for setting up swagger for
 * the author controller. The swagger documentation can be viewed at {@code
 * http://<host>:<port>/swagger-ui-html}
 * <p/>
 *
 * @author Indra Basak
 * @since 10/4/17
 */
@Configuration
@EnableSwagger
//@EnableSwagger2
public class SwaggerConfiguration {

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;


    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        //Class[] clazz = {Book.class};
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo("Book API", "Book Service API"));
                //.ignoredParameterTypes(clazz);

                //.apiVersion("1.0")
                //.includePatterns("/com/basaki/controller/.*");
                //.includePatterns(".*basaki.controller.*");
    }

    /**
     * Creates the Swagger configuration bean.
     *
     * @return docket bean
     */
/*    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("book")
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        "com.basaki.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo("Book API", "Book Service API"));
    }*/

    /**
     * Creates an object containing API information including author name,
     * email, version, license, etc.
     *
     * @param title       API title
     * @param description API description
     * @return API information
     */
/*    private ApiInfo apiInfo(String title, String description) {
        Contact contact = new Contact("Indra Basak", "",
                "developer@gmail.com");
        return new ApiInfo(title, description, "1.0", "terms of controller url",
                contact, "license", "license url");
    }*/
    private ApiInfo apiInfo(String title, String description) {
        ApiInfo apiInfo = new ApiInfo(
                title,
                description,
                "terms of controller url",
                "developer@gmail.com",
                "license",
                "license url");
        return apiInfo;
    }
}
