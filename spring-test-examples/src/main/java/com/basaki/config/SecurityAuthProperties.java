package com.basaki.config;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@code SecurityAuthProperties} represents the basic authorization security
 * properties. The properties are declared as security.auth.*. .
 * <pre>
 * security:
 *   auth:
 *     users:
 *       userA:
 *         password: pwdA
 *       userB:
 *         password: pwdB
 * </pre>
 *
 * @author Indra Basak
 * @since 10/5/17
 */
@ConfigurationProperties("security.auth")
@Getter
@Setter
public class SecurityAuthProperties {

    private Map<String, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        users.entrySet()
                .stream()
                .filter(e -> e.getValue().getId() == null)
                .forEach(e -> e.getValue().setId(e.getKey()));
    }

    /**
     * {@code Route} represents a basic auth security route for a given service
     * id with associated roles.
     * <p/>
     *
     * @author Indra Basak
     */
    @Data
    @Slf4j
    public static class User {

        private String id;

        private String password;
    }
}
