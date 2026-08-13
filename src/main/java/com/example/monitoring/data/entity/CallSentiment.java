package com.example.monitoring.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "call_sentiments")
public class CallSentiment {

    @Id
    @Column(name = "call_id")
    private UUID callId;

    @Column(name = "call_timestamp")
    private Date callTimestamp;

    @Column(name = "customer_service_name")
    private String customerServiceName;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_sentiment")
    private String customerSentiment;

    public UUID getCallId() {
        return callId;
    }

    public Date getCallTimestamp() {
        return callTimestamp;
    }

    public String getCustomerServiceName() {
        return customerServiceName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerSentiment() {
        return customerSentiment;
    }
}