package com.example.httpclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableScheduling
public class HttpClientController {
    @Value("${DEMO_HTTP_TO_GRPC:http://127.0.0.1:8182}")
    private String httpToGrpcUrl;
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ping")
    public String ping() {
        return "hello http-client";
    }

    @Scheduled(cron = "${httpclient.mysql.cron}")
    public void mysql() {
        String res = restTemplate.getForObject(httpToGrpcUrl + "/mysql", String.class);
        System.out.println("HTTP Client received: " + res);
    }
}
