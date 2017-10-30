package com.basaki.aspect;

import java.util.Map;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

/**
 * {@code SecurityAspect} intercepts any calls to {@code PersonRepository}
 * and logs oauth2 token and user information.
 * <p>
 *
 * @author Indra Basak
 * @since 10/29/17
 */
@Component
@Aspect
@Log
public class SecurityAspect {

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Pointcut("execution(public * com.basaki.data.repository.PersonRepository.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object advice(ProceedingJoinPoint pjp) throws Throwable {
        log.info(
                "Entering SecurityAspect.advice() in class "
                        + pjp.getSignature().getDeclaringTypeName()
                        + " - method: " + pjp.getSignature().getName());

        OAuth2AccessToken accessToken = oauth2ClientContext.getAccessToken();
        log.info("AccessToken:: " + accessToken);

        if (SecurityContextHolder.getContext().getAuthentication()
                instanceof OAuth2Authentication) {
            OAuth2Authentication authentication =
                    (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getUserAuthentication() instanceof UsernamePasswordAuthenticationToken) {
                UsernamePasswordAuthenticationToken userToken =
                        (UsernamePasswordAuthenticationToken) authentication.getUserAuthentication();
                log.info("Principal id: " + userToken.getPrincipal());
                if (userToken.getDetails() instanceof Map) {
                    Map details = (Map) userToken.getDetails();
                    log.info("Principal Name: " + details.get("name"));
                }
            }
        }

        return pjp.proceed();
    }
}
