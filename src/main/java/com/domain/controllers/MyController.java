package com.domain.controllers;

import com.domain.services.HeavyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyController.class);

    @Value("${welcome.text}")
    private String welcomeText;

    @Autowired
    private HeavyService heavyService;

    @GetMapping("/first")
    public String myFirstEndpoint() {
        return "This is my first endpoint";
    }

    @GetMapping("/second")
    public String mySecondEndpoint() {
        return "This is my second endpoint";
    }

    @GetMapping("/data")
    public String getData() {
        return heavyService.getSomeData();
    }

    @GetMapping("/welcometext")
    public String welcomeText() {
        LOGGER.debug("Starting welcome text");
        return welcomeText;
    }

}
