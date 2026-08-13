package com.example.monitoring.controller;

import com.example.monitoring.data.entity.CallSentiment;
import com.example.monitoring.domain.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1")
public class MonitoringController {

    @Autowired
    private MonitoringService monitoringService;

    @GetMapping("/sentiments")
    public ResponseEntity<List<CallSentiment>> getSentiments(){
        List<CallSentiment> sentiments = monitoringService.getSentiments();
        return ResponseEntity.ok(sentiments);
    }

}
