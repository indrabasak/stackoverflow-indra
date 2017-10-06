package com.basaki.config;

import com.basaki.service.TenantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * {@code SecurityConfiguration} is the configuration for setting up
 * Spring security.}
 * <p/>
 *
 * @author Indra Basak
 * @since 10/5/17
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableConfigurationProperties(SecurityAuthProperties.class)
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private SecurityAuthProperties properties;

    private TenantService service;

    @Autowired
    public SecurityConfiguration(SecurityAuthProperties properties,
            TenantService service) {
        this.properties = properties;
        this.service = service;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(
                new CustomPreAuthFilter(service),
                BasicAuthenticationFilter.class);

        http.authorizeRequests().anyRequest().fullyAuthenticated()
                .and().httpBasic().and().csrf().disable();

        http.sessionManagement().sessionCreationPolicy(
                SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/", "/v2/api-docs", "/configuration/ui",
                "/swagger-resources",
                "/configuration/security", "/swagger-ui.html",
                "/webjars/**", "/swagger-resources/configuration/ui",
                "/swagge‌​r-ui.html", "/docs/**", "/ping",
                "/swagger-resources/configuration/security");
    }

    /**
     * Configures the security provider. The base class delegates the
     * security provider to a child class.
     *
     * @param auth builder for creating the security manager
     * @throws Exception
     */
    protected void configure(AuthenticationManagerBuilder auth) throws
            Exception {
        properties.getUsers().entrySet().stream()
                .forEach(e -> {
                    try {
                        auth.inMemoryAuthentication().withUser(
                                e.getKey()).password(
                                e.getValue().getPassword()).roles("USER");
                    } catch (Exception excp) {
                        throw new RuntimeException(excp);
                    }
                });
    }
}
