package com.example.monitoring.controller.sentiments;


import org.junit.Test;

import java.time.format.DateTimeFormatter;

public class MonitoringControllerTest {


    @Test
    public void whattt(){

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz");
        format.parse("2026-08-13T11:06:50.942Z");
    }

}
