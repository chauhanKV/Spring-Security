package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.entity.AppUser;
import com.example.SpringSecurity.service.AppUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @PostMapping("/create-user")
    ResponseEntity<Long> createUser(@RequestBody AppUser user)
    {
        Long id = appUserDetailsService.createUser(user);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String hello(@AuthenticationPrincipal UserDetails userDetails)
    {
        // Used to get authenticated user details
        LOGGER.info("User Info through Security Context Holder : {}", ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        LOGGER.info("User Info through AuthenticationPrincipal Arg: {}", userDetails.getUsername());
        return "Hello from Admin";
    }


    // This post call was not working because by default Java Springboot has enabled CRSF.
    // With CRSF we cannot modify resources without getting token from backend to check if it's a legitimate request.
    // To make post request - CRSF token (generate by same app from backend) and JSessionID is required.

    @PostMapping("/hello")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String postHello(@AuthenticationPrincipal UserDetails userDetails)
    {
        // Used to get authenticated user details
        LOGGER.info("User Info through Security Context Holder : {}", ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        LOGGER.info("User Info through AuthenticationPrincipal Arg: {}", userDetails.getUsername());
        return "Hello from Admin";
    }


    // Never update passwords in GET call . Its vulnerability 1
//    @GetMapping("/changePassword")
//    public ResponseEntity<String> changePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String newPassword)
//    {
//        appUserDetailsService.changePassword(userDetails, newPassword);
//        return ResponseEntity.ok("Password Changed Successfully");
//    }
}
