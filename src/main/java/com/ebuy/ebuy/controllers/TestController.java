package com.ebuy.ebuy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebuy.ebuy.services.TestService;

@RestController
public class TestController {
    @Autowired
    public TestService testService;
    @PostMapping(path = "/test")
    public String test(){
        return testService.testing();
    }
}
