package com.example.SpringSecurity.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(2)
public class AppFilter extends HttpFilter {
    private static Logger LOGGER = LoggerFactory.getLogger(AppFilter.class);
    @Override
    public void doFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("Processing APIFilter before Controller");
        LOGGER.info("Set MDC in MDC RequestFilter");
        MDC.put("X-Request-Id", httpServletRequest.getHeader("X-Request-Id"));
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        LOGGER.info("Processing APIFilter before sending response to the client");
    }
}
