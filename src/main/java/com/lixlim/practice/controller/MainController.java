package com.lixlim.practice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    public ResponseEntity<String> getHome() {
        var bean = new Object() {
            int x = 10;
            String y = "hello";

            @Override
            public String toString() {
                return this.x + ", " + this.y;
            };
        };
        return ResponseEntity.ok("hello world! " + String.valueOf(bean));
    }
}
