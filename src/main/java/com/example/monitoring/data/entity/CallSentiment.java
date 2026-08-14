package com.example.monitoring.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "call_sentiments")
@Getter
public class CallSentiment {

    @Id
    @Column(name = "call_id")
    private UUID callId;

    @Column(name = "call_timestamp")
    private OffsetDateTime callTimestamp;

    @Column(name = "customer_service_name")
    private String customerServiceName;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_sentiment")
    private Double customerSentiment;

}