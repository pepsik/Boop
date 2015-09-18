package org.pepsik.rest.utilities.support;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.pepsik.rest.exceptions.InsufficientAuthorizationException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by pepsik on 7/6/15.
 */
@Aspect
@Component
class RememberMeAuthCheckAspect {

    @Before("execution(String org.pepsik.rest.mvc.UserSettingsController.update* (..))")
    public void securityControllerMethods() {
        if (rememberMeAuthenticated())
            throw new InsufficientAuthorizationException();
    }

    private boolean rememberMeAuthenticated() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }

        return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
    }
}
