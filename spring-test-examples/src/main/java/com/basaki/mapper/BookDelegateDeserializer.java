package com.basaki.mapper;

import com.basaki.model.Book;
import com.basaki.service.LanguageService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

/**
 * {@code BookDelegateDeserializer} deserializes JSON into a {@code Book} object
 * using default deserializer. Then constructs the {@code Language} based on the
 * value provided. If language code is missing use default value.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/31/17
 */
public class BookDelegateDeserializer extends StdDeserializer<Book> implements ResolvableDeserializer {

    private final JsonDeserializer<?> defaultDeserializer;

    private LanguageService languageService;

    public BookDelegateDeserializer(JsonDeserializer<?> defaultDeserializer,
            LanguageService languageService) {
        super(Book.class);
        this.defaultDeserializer = defaultDeserializer;
        this.languageService = languageService;
    }

    @Override
    public Book deserialize(JsonParser parser,
            DeserializationContext context) throws IOException, JsonProcessingException {
        Book book = (Book) defaultDeserializer.deserialize(parser, context);

        if (book.getLanguage() != null) {
            book.setLanguage(
                    languageService.read(book.getLanguage().getCode()));
        } else {
            book.setLanguage(languageService.read("es"));
        }

        return book;
    }

    @Override
    public void resolve(
            DeserializationContext context) throws JsonMappingException {
        ((ResolvableDeserializer) defaultDeserializer).resolve(context);

    }
}
