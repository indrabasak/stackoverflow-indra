package com.basaki.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * {@code SwaggerConfiguration} is the configuration for setting up swagger for
 * the author controller. The swagger documentation can be viewed at {@code
 * http://<host>:<port>/swagger-ui-html}
 * <p/>
 *
 * @author Indra Basak
 * @since 11/15/17
 */
@Configuration
@Import({BeanValidatorPluginsConfiguration.class, SpringDataRestConfiguration.class})
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Creates the Swagger configuration bean.
     *
     * @return docket bean
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("book")
                .select()
                //                .apis(RequestHandlerSelectors.basePackage(
                //                        "com.basaki.controller"))
                //                .apis(exactPackages("com.basaki.controller", "com.basaki.model",
                //                        "com.basaki.data.repository"))
                //.paths(PathSelectors.any())
                .paths(matchPathRegex("/books(/|$).*",
                        "/booxs(/|$).*", "/tokens(/|$).*",
                        "/ping(/|$).*"))
                .build()
                .apiInfo(apiInfo("Example Springfox API",
                        "Example Springfox API"));
    }

    /**
     * Creates an object containing API information including author name,
     * email, version, license, etc.
     *
     * @param title       API title
     * @param description API description
     * @return API information
     */
    private ApiInfo apiInfo(String title, String description) {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .build();
    }

    private static Predicate<RequestHandler> exactPackages(
            final String... pkgs) {
        return input -> {
            String currentPkg = input.declaringClass().getPackage().getName();
            for (String pkg : pkgs) {
                if (pkg.equals(currentPkg)) {
                    return true;
                }
            }
            return false;
        };
    }

    public static Predicate<String> matchPathRegex(final String... pathRegexs) {
        return new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                for (String pathRegex : pathRegexs) {
                    if (input.matches(pathRegex)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
