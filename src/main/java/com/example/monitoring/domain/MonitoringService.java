package com.example.monitoring.domain;

import com.example.monitoring.data.entity.CallSentiment;
import com.example.monitoring.data.repository.CallSentimentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitoringService {

    @Autowired
    private CallSentimentRepo callSentimentRepo;

    public List<CallSentiment> getSentiments(){
       return callSentimentRepo.findAll();
    }

}
