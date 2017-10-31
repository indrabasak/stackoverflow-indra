package com.basaki.service;

import com.basaki.model.Language;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {

    private static Map<String, String> codeToLanguageMap = new HashMap<>();

    static {
        codeToLanguageMap.put("en", "English");
        codeToLanguageMap.put("en-gb", "English (United Kingdom)");
        codeToLanguageMap.put("en-us", "English (United States)");
        codeToLanguageMap.put("es", "Spanish (Spain)");
    }

    public Language read(String  code) {
        String language = codeToLanguageMap.get(code);
        if (language != null) {
            return new Language(code, language);
        }

        return null;
    }
}
