package com.venturus.infra;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        addCorsHeaders(response);

        if ("OPTIONS".equals(request.getMethod())/* || request.getServletPath().equals("/login")*/) {
            filterChain.doFilter(request, response);

        } else {
            if (!isTokenPresentInRequest(request)) {
                System.out.println(String.format("Incoming request to URI '%s' does not contains header 'token'.", request.getRequestURI()));
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            } else {
                System.out.println(String.format("Incoming request to URI '%s' contains header 'token'.", request.getRequestURI()));
                filterChain.doFilter(request, response);
            }
        }
    }

    private boolean isTokenPresentInRequest(HttpServletRequest request) {
        return Strings.isNotBlank(request.getHeader("token"));

    	/*if (Objects.isNull(request.getCookies())) {
    		return false;
		}

		Optional<Cookie> token = Arrays.stream(request.getCookies())
				.filter(cookie -> cookie.getName().equals("token"))
				.findAny();

		return token.isPresent();*/
    }

    private void addCorsHeaders(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
    }
}
