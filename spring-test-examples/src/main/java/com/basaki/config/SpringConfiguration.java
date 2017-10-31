package com.basaki.config;

import com.basaki.mapper.BookDeserializer;
import com.basaki.mapper.LanguageDeserializer;
import com.basaki.model.Book;
import com.basaki.model.Language;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
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

//    @Bean
//    public Jackson2ObjectMapperBuilder objectMapperBuilder(
//            LanguageDeserializer deserializer) {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(Language.class, deserializer);
//        builder.modules(module);
//
//        return builder;
//    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder(
            BookDeserializer deserializer) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Book.class, deserializer);
        builder.modules(module);

        return builder;
    }
}