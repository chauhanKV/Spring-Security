package com.example.SpringSecurity;

import com.example.SpringSecurity.filter.AppFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private static Logger LOGGER = LoggerFactory.getLogger(AppFilter.class);
    @GetMapping()
    public String hello(@AuthenticationPrincipal UserDetails userDetails)
    {
        // Used to get authenticated user details
        LOGGER.info("User Info through Security Context Holder : {}", ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        LOGGER.info("User Info through AuthenticationPrincipal Arg: {}", userDetails.getUsername());
        return "Hello from server";
    }
}
