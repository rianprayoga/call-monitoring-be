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

    @Autowired
    private CallSentimentRepo callSentimentRepo;

    public PageResponse<GetSentimentDto> getSentiments(Filter filter, Integer size){

        if (filter.cursor() != null){
            CallSentiment sentiment = callSentimentRepo
                    .getSentimentBy(UUID.fromString(filter.cursor()))
                    .orElseThrow(() -> new BadRequestException(INVALID_CURSOR, "Invalid cursor provided."));

            List<GetSentimentDto> sentiments =
                    callSentimentRepo.getSentiments(UUID.fromString(filter.cursor()), sentiment.getCallTimestamp(), size).stream()
                            .map(x -> new GetSentimentDto(
                                    x.getCallId(),
                                    x.getCallTimestamp(),
                                    x.getCustomerServiceName(),
                                    x.getCustomerName(),
                                    x.getCustomerSentiment()))
                            .toList();

            if (sentiments.size() > size) {
                return new PageResponse<>(
                        sentiments.subList(0, size),
                        true,
                        sentiments.get(size - 1).getCallId().toString());
            }

            return new PageResponse<>(sentiments, false, null);
        }

        List<GetSentimentDto> sentiments =
                callSentimentRepo.getSentiments(size, filter.min(), filter.max(), filter.start(), filter.end()).stream()
                        .map(x -> new GetSentimentDto(
                                x.getCallId(),
                                x.getCallTimestamp(),
                                x.getCustomerServiceName(),
                                x.getCustomerName(),
                                x.getCustomerSentiment()))
                        .toList();

        if (sentiments.size() > size) {
            return new PageResponse<>(
                    sentiments.subList(0, size),
                    true,
                    sentiments.get(size - 1).getCallId().toString());
        }

        return new PageResponse<>(sentiments, false, null);
    }

}
