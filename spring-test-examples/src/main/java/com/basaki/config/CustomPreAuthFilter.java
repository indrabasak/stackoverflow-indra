package com.basaki.config;

import com.basaki.service.TenantService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * {@code CustomPreAuthFilter} is a custom pre-authentication filter.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/5/17
 */
public class CustomPreAuthFilter extends OncePerRequestFilter {

    private TenantService service;

    public CustomPreAuthFilter(TenantService service) {
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Basic ")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String[] tokens = extractAndDecodeHeader(header, request);
            assert tokens.length == 2;

            String username = tokens[0];
            service.sayHello(username);

        } catch (Exception e) {
            //do nothing
        }

        chain.doFilter(request, response);
    }

    /**
     * Decodes the header into a username and password.
     *
     * @throws BadCredentialsException if the Basic header is not present or is
     *                                 not valid
     *                                 Base64
     */
    private String[] extractAndDecodeHeader(String header,
            HttpServletRequest request)
            throws IOException {

        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new BadCredentialsException(
                    "Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(
                delim + 1)};
    }
}
