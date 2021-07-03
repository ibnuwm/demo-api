package com.domain.controllers;

import com.domain.services.HeavyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

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

}
