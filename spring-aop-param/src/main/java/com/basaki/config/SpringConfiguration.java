package com.basaki.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;


/**
 * {@code SpringConfiguration} configures resources for localization, exception
 * handling, etc.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/4/17
 */
@Configuration
public class SpringConfiguration {

    /**
     * Creates the exception resolver bean for converting an exception message
     * to JSON.
     *
     * @return exception resolver
     */
    @Bean
    public ExceptionHandlerExceptionResolver createExceptionResolver() {
        ExceptionHandlerExceptionResolver resolver =
                new ExceptionHandlerExceptionResolver();

        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter();
        converter.setPrefixJson(false);

        List<MediaType> mediaTypes = Arrays.asList(MediaType.TEXT_PLAIN,
                MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypes);

        List<HttpMessageConverter<?>> msgConverters = new ArrayList<>();
        msgConverters.add(converter);
        resolver.setMessageConverters(msgConverters);

        return resolver;
    }

    /**
     * Creates a message converter bean for reading and writing JSON.
     *
     * @return aJSON message converter bean
     */
    @Bean
    public MappingJackson2HttpMessageConverter getHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter();
        converter.setPrefixJson(false);
        converter.setSupportedMediaTypes(Arrays.asList(
                MediaType.APPLICATION_JSON,
                MediaType.TEXT_PLAIN));
        return converter;
    }
}