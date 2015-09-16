package org.pepsik.rest.utility.support;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by pepsik on 7/6/15.
 */

@Aspect
@Component
public class SecurityContextRepositoryAddsInfoToSessionAspect {

    private static final Logger logger = LoggerFactory.getLogger(SecurityContextRepositoryAddsInfoToSessionAspect.class);

    @Before("execution(* org.springframework.security.web.context.HttpSessionSecurityContextRepository.loadContext (..))")
    public void addsUserRequestInfoToSession(JoinPoint joinPoint) {
        HttpRequestResponseHolder httpRequestResponseHolder = (HttpRequestResponseHolder) joinPoint.getArgs()[0];
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getClass().equals(AnonymousAuthenticationToken.class)) {
            return;
        }
        HttpSession session = httpRequestResponseHolder.getRequest().getSession(false);
        if (session == null)
            return;

        session.setAttribute("User-Agent", httpRequestResponseHolder.getRequest().getAttribute("User-Agent"));
        logger.debug("Aspect: User-Agent info successful added to HttpSession");
    }
}
