package com.example.monitoring.controller.sentiments;

import com.example.monitoring.controller.sentiments.dto.GetSentimentDto;
import com.example.monitoring.domain.MonitoringService;
import com.example.monitoring.utilities.PageResponse;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class MonitoringController {

    @Autowired
    private MonitoringService monitoringService;

    @GetMapping("/sentiments")
    public ResponseEntity<PageResponse<GetSentimentDto>> getSentiments(
            @RequestParam(required = false) String cursor,
            @RequestParam(required = false, defaultValue = "${pagination.defaultSize}")
                    @Min(value = 1, message = "Size can't be less than 0.")
                    Integer size) {
        PageResponse<GetSentimentDto> page = monitoringService.getSentiments(cursor, size);
        return ResponseEntity.ok(page);
    }

}
