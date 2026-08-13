package com.example.monitoring.data.repository;

import com.example.monitoring.data.entity.CallSentiment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CallSentimentRepo extends JpaRepository<CallSentiment, UUID> {

}
