package com.example.monitoring.controller.sentiments;

import com.example.monitoring.controller.sentiments.dto.Filter;
import com.example.monitoring.controller.sentiments.dto.GetSentimentDto;
import com.example.monitoring.domain.MonitoringService;
import com.example.monitoring.utilities.PageResponse;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("v1")
public class MonitoringController {

    private final MonitoringService monitoringService;

    @Autowired
    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @GetMapping("/sentiments")
    public ResponseEntity<PageResponse<GetSentimentDto>> getSentiments(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) @DecimalMin(value = "0.0", message = "Min can't be lower than 0.")
                    Double min,
            @RequestParam(required = false) @DecimalMax(value = "100.00", message = "Max can't be bigger than 100.00.")
                    Double max,
            @RequestParam(required = false)
                    @Pattern(
                            regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d{1,9})?Z$",
                            message = "Start must be a valid ISO 8601 UTC string format ending with 'Z'")
                    String start,
            @Pattern(
                            regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d{1,9})?Z$",
                            message = "End must be a valid ISO 8601 UTC string format ending with 'Z'")
                    @RequestParam(required = false)
                    String end,
            @Pattern(
                            regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d{1,9})?Z$",
                            message = "Period must be a valid ISO 8601 UTC string format ending with 'Z'")
                    @RequestParam(required = false)
                    String period,
            @RequestParam(required = false) String cursor,
            @RequestParam(required = false, defaultValue = "${pagination.defaultSize}")
                    @Min(value = 1, message = "Size can't be less than 0.")
                    Integer size) {

        Filter filter = Filter.of(query, min, max, start, end, period, cursor);

        PageResponse<GetSentimentDto> page = monitoringService.getSentiments(filter, size);

        return ResponseEntity.ok(page);
    }

}
