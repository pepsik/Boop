package org.pepsik.web.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserInfoToSessionCollectorFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoToSessionCollectorFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);
        session.setAttribute("User-Agent", request.getHeader("User-Agent"));
        session.setAttribute("RemoteAddress", request.getRemoteAddr());
        logger.debug("User-Agent info, ip successful added to HttpSession");
        filterChain.doFilter(request, response);
    }
}
