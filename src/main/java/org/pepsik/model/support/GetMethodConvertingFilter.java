package org.pepsik.model.support;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * Created by pepsik on 6/7/15.
 */

//solution for "HTTP Status 405 - JSPs only permit GET POST or HEAD"
//more on http://stackoverflow.com/questions/24673041/405-jsp-error-with-put-method
public class GetMethodConvertingFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        chain.doFilter(wrapRequest((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {
        // do nothing
    }

    private static HttpServletRequestWrapper wrapRequest(HttpServletRequest request) {
        return new HttpServletRequestWrapper(request) {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
    }
}