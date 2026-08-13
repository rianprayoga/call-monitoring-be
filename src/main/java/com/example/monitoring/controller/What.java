package com.example.monitoring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class What {


    @GetMapping("/test")
    public ResponseEntity<String> what(){
        return ResponseEntity.ok("what");
    }

}
