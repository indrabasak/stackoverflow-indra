package com.basaki.mapper;

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
 * {@code LanguageDeserializer} deserializes JSON into a {@code Language} object.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/30/17
 */
@Component
public class LanguageDeserializer extends JsonDeserializer<Language> {

    @Autowired
    private LanguageService service;

    @Override
    public Language deserialize(JsonParser parser,
            DeserializationContext context) throws IOException, JsonProcessingException {

        final JsonNode node = parser.readValueAsTree();
        if (node != null) {
            JsonNode field = node.get("code");
            final String languageCode = field.asText();
            return service.read(languageCode);
        } else {
            return service.read("en");
        }
    }
}
