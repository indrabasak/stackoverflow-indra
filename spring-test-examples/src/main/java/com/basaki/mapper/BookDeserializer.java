package com.basaki.mapper;

import com.basaki.model.Book;
import com.basaki.model.Language;
import com.basaki.service.LanguageService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * {@code BookDeserializer} deserializes JSON into a {@code Book} object.
 * <p/>
 *
 * @author Indra Basak
 * @since 1030/17
 */
@Component
public class BookDeserializer extends JsonDeserializer<Book> {

    @Autowired
    private LanguageService service;

    @Override
    public Book deserialize(JsonParser parser,
            DeserializationContext context) throws IOException, JsonProcessingException {
        Book book = null;

        JsonNode node = parser.readValueAsTree();
        if (node != null) {
            Integer id = null;
            String title = null;
            String author = null;
            Language language = null;

            JsonNode idField = node.get("id");
            if (idField != null) {
                id = Integer.parseInt(idField.asText());
            }

            JsonNode titleField = node.get("title");
            if (idField != null) {
                title = titleField.asText();
            }

            JsonNode authorField = node.get("author");
            if (authorField != null) {
                author = authorField.asText();
            }

            JsonNode langField = node.get("language");
            if (langField != null) {
                JsonNode codeField = node.get("code");
                if (codeField != null) {
                    language = service.read(codeField.asText());
                }
            }

            if (language == null) {
                language = service.read("es");
            }

            book = new Book(id, title, author, language);
        }

        return book;
    }
}
