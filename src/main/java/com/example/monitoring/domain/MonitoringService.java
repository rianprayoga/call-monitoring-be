package com.example.monitoring.domain;

import com.example.monitoring.controller.sentiments.dto.Filter;
import com.example.monitoring.controller.sentiments.dto.GetSentimentDto;
import com.example.monitoring.data.entity.CallSentiment;
import com.example.monitoring.data.repository.CallSentimentRepo;
import com.example.monitoring.errors.http.BadRequestException;
import com.example.monitoring.utilities.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.monitoring.errors.ErrorCode.INVALID_CURSOR;

@Service
public class MonitoringService {


    private final CallSentimentRepo callSentimentRepo;

    @Autowired
    public MonitoringService(CallSentimentRepo callSentimentRepo) {
        this.callSentimentRepo = callSentimentRepo;
    }

    public PageResponse<GetSentimentDto> getSentiments(Filter filter, Integer size){


        List<GetSentimentDto> sentimentDtos = getSentimentDtos(filter, size);

        if (sentimentDtos.size() > size) {
            return new PageResponse<>(
                    sentimentDtos.subList(0, size),
                    true,
                    sentimentDtos.get(size - 1).getCallId().toString());
        }

        return new PageResponse<>(sentimentDtos, false, null);
    }

    private List<GetSentimentDto> getSentimentDtos(Filter filter, Integer size){
        if (filter.cursor() == null){
            return callSentimentRepo
                    .getSentiments(
                            filter.query(),
                            size,
                            filter.min(),
                            filter.max(),
                            filter.start(),
                            filter.end(),
                            filter.period())
                    .stream()
                    .map(x -> new GetSentimentDto(
                            x.getCallId(),
                            x.getCallTimestamp(),
                            x.getCustomerServiceName(),
                            x.getCustomerName(),
                            x.getCustomerSentiment()))
                    .toList();
        }

        CallSentiment sentiment = callSentimentRepo
                .getSentimentBy(UUID.fromString(filter.cursor()))
                .orElseThrow(() -> new BadRequestException(INVALID_CURSOR, "Invalid cursor provided."));

        return callSentimentRepo
                .getSentiments(
                        UUID.fromString(filter.cursor()),
                        sentiment.getCallTimestamp(),
                        filter.query(),
                        filter.min(),
                        filter.max(),
                        filter.start(),
                        filter.end(),
                        filter.period(),
                        size)
                .stream()
                .map(x -> new GetSentimentDto(
                        x.getCallId(),
                        x.getCallTimestamp(),
                        x.getCustomerServiceName(),
                        x.getCustomerName(),
                        x.getCustomerSentiment()))
                .toList();
    }

}
