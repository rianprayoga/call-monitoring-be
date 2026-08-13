package com.example.monitoring.controller.sentiments.dto;

import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
public class GetSentimentDto {

    public GetSentimentDto(
            UUID callId,
            OffsetDateTime callTimestamp,
            String customerServiceName,
            String customerName,
            String customerSentiment) {
        this.callId = callId;
        this.callTimestamp = callTimestamp;
        this.customerServiceName = customerServiceName;
        this.customerName = customerName;
        this.customerSentiment = customerSentiment;
    }

    private UUID callId;
    private OffsetDateTime callTimestamp;
    private String customerServiceName;
    private String customerName;
    private String customerSentiment;
}
