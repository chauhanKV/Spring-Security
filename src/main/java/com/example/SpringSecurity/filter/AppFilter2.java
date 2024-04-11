package com.example.SpringSecurity.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Order(1)
public class AppFilter2 extends HttpFilter {

    private static Logger LOGGER = LoggerFactory.getLogger(AppFilter2.class);

    public void doFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("Processing APPFILTER2 before calling controller function");
        filterChain.doFilter(httpServletRequest,httpServletResponse);
        LOGGER.info("Processing APPFilter2 before sending API response to the client");
    }

}
