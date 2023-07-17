package com.restcontroller.example.restcontroller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoRestController {

    // add code for the "/hello" end points

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Adesh";
    }

}
