package com.edureka.ms.training.securitysimple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//http://localhost:<port>/secured
public class ProtectedService {

    @RequestMapping("/secured")
    public String getHello(){
        return "I am an secured API";
    }
}
