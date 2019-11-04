package com.example.demospringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianyalei
 */
@RestController
public class HelloWorld {
    @RequestMapping("/hello")
    String helloWorld(){
        return "Hello World !";
    }
}
