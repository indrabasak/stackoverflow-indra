package com.basaki.config.processor;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.metadata.ConfigurationMetadata;
import org.springframework.boot.configurationprocessor.metadata.ItemHint;
import org.springframework.boot.configurationprocessor.metadata.ItemMetadata;
import org.springframework.boot.configurationprocessor.metadata.JsonMarshaller;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.yaml.snakeyaml.Yaml;

import static org.junit.Assert.assertNotNull;

/**
 * {@code SpringConfigurationMetadataProcessorTest} is an example of processing
 * spring configuration metatdata and turn it into an HTML file.
 * <p>
 *
 * @author Indra Basak
 * @since 12/11/17
 */
@RunWith(SpringRunner.class)
public class SpringConfigurationMetadataProcessorTest {

    @Value(value = "classpath:spring-configuration-metadata.json")
    private Resource metaDataJson;

    private List<Map<String, Object>> convertHintsToKeyValue(
            List<ItemHint> hints) {
        List<Map<String, Object>> hintlist = new LinkedList<>();

        for (ItemHint hint : hints) {
            Map<String, Object> hintMap = new HashMap<>();
            hintlist.add(hintMap);

            hintMap.put("name", hint.getName());

            List<Map<String, String>> valueList = new LinkedList<>();
            for (ItemHint.ValueHint value : hint.getValues()) {
                Map<String, String> valueMap = new HashMap<>();
                valueList.add(valueMap);
                if (value.getValue() != null) {
                    valueMap.put("value", value.getValue().toString());
                }
                if (value.getDescription() != null) {
                    valueMap.put("description", value.getDescription());
                }
            }

            if (valueList.size() > 0) {
                hintMap.put("values", valueList);
            }

            List<Map<String, Object>> providerList = new LinkedList<>();
            for (ItemHint.ValueProvider provider : hint.getProviders()) {
                Map<String, Object> providerMap = new HashMap<>();
                providerList.add(providerMap);
                if (provider.getName() != null) {
                    providerMap.put("name", provider.getName());
                }

                if (provider.getParameters() != null &&
                        !provider.getParameters().isEmpty()) {
                    Map<String, String> parameters = new HashMap<>();
                    provider.getParameters().entrySet().forEach(
                            e -> parameters.put(e.getKey(),
                                    e.getValue().toString()));
                    providerMap.put("parameters", parameters);
                }
            }

            if (providerList.size() > 0) {
                hintMap.put("providers", providerList);
            }
        }

        return hintlist;
    }

    private Map<String, Object> convertItemsToKeyValue(
            List<ItemMetadata> items) {

        Map<String, Object> itemsMap = new HashMap<>();

        /*
          private ItemMetadata.ItemType itemType;
    private String name;
    private String type;
    private String description;
    private String sourceType;
    private String sourceMethod;
    private Object defaultValue;
    private ItemDeprecation deprecation;
         */
        List<Map<String, Object>> groupList = new LinkedList<>();
        List<Map<String, Object>> propertyList = new LinkedList<>();

        for (ItemMetadata item : items) {
            Map<String, Object> itemMap = new HashMap<>();

            if (item.getName() != null) {
                itemMap.put("name", item.getName());
            }

            if (item.getType() != null) {
                itemMap.put("type", item.getType());
            }

            if (item.getDescription() != null) {
                itemMap.put("description", item.getDescription());
            }

            if (item.getSourceType() != null) {
                itemMap.put("sourceType", item.getSourceType());
            }

            if (item.getSourceMethod() != null) {
                itemMap.put("sourceMethod", item.getSourceMethod());
            }

            if (item.getDefaultValue() != null) {
                itemMap.put("defaultValue", item.getDefaultValue());
            }

            if (item.getDeprecation() != null) {

            }

            if (item.isOfItemType(ItemMetadata.ItemType.GROUP)) {
                groupList.add(itemMap);
            } else if (item.isOfItemType(ItemMetadata.ItemType.PROPERTY)) {
                propertyList.add(itemMap);
            }

            if (groupList.size() > 0) {
                itemsMap.put("groups", groupList);
            }

            if (propertyList.size() > 0) {
                itemsMap.put("properties", propertyList);
            }
        }

        return itemsMap;

    }

    @Test
    public void testMarshalling() throws Exception {
        assertNotNull(metaDataJson);
        metaDataJson.getInputStream();

        ConfigurationMetadata metadata =
                new JsonMarshaller().read(metaDataJson.getInputStream());

        convertToHtml(metadata);
    }

    private void convertToYaml(ConfigurationMetadata metadata) {
        Map<String, Object> map = new HashMap<>();

        List<Map<String, Object>> hintslist =
                convertHintsToKeyValue(metadata.getHints());
        if (hintslist.size() > 0) {
            map.put("hints", hintslist);
        }

        //        Map<String, Object> itemsMap =
        //                convertItemsToKeyValue(metadata.getItems());
        //        itemsMap.entrySet().forEach(e -> map.put(e.getKey(), e.getValue()));

        Yaml yaml = new Yaml();
        String str = yaml.dump(map);

        System.out.println(str);
    }

    private void convertToHtml(
            ConfigurationMetadata metadata) throws IOException, TemplateException {
        Configuration cfg = new Configuration(new Version(2, 3, 27));

        // template is loaded from 'templates' folder under resources
        cfg.setClassForTemplateLoading(
                SpringConfigurationMetadataProcessorTest.class,
                "/templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(
                TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("title", "Spring Configuration Metadata Example");
        input.put("metadata", metadata);
        input.put("GROUP", ItemMetadata.ItemType.GROUP);
        input.put("PROPERTY", ItemMetadata.ItemType.PROPERTY);

        Template template = cfg.getTemplate("metadata.ftl");

        // write output to the console
        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);

        // write output into a file
        Writer fileWriter = new FileWriter(new File("metadata.html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }
    }
}
