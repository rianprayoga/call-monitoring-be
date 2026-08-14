package com.example.monitoring.controller.sentiments.dto;

import com.example.monitoring.errors.ErrorCode;
import com.example.monitoring.errors.http.BadRequestException;

import java.time.OffsetDateTime;

public record Filter(
        Double min,
        Double max,
        OffsetDateTime start,
        OffsetDateTime end,
        OffsetDateTime period,
        String cursor
) {

    public static Filter of(Double min, Double max, String start, String end, String period, String cursor) {
        if (min != null && max != null && min > max){
            throw new BadRequestException(ErrorCode.SCHEMA_VALIDATION_FAILED, "Min must be less than max.");
        }

        if (period != null) {
            if ((start != null || end != null))
                throw new BadRequestException(
                        ErrorCode.SCHEMA_VALIDATION_FAILED, "Period can't be used together with start or end.");

            return new Filter(min, max, null, null, OffsetDateTime.parse(period), cursor);
        }

        if (start != null && end != null) {
            OffsetDateTime startTime = OffsetDateTime.parse(start);
            OffsetDateTime endTime = OffsetDateTime.parse(end);
            if (startTime.isAfter(endTime))
                throw new BadRequestException(ErrorCode.SCHEMA_VALIDATION_FAILED, "Start must before End.");
            return new Filter(min, max, OffsetDateTime.parse(start), OffsetDateTime.parse(end), null, cursor);
        }

        if (start == null && end == null) {
            return new Filter(min, max, null, null, null, cursor);
        }

        throw new BadRequestException(
                ErrorCode.SCHEMA_VALIDATION_FAILED,
                "Start and End must be used together.");

    }

}
