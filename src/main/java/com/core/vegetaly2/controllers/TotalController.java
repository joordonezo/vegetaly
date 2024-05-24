package com.core.vegetaly2.controllers;

import com.core.vegetaly2.models.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TotalController {

    @Value("${microservice1.url}")
    String urlService1;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String home() {
        return "Vegetaly";
    }

    @GetMapping("/total")
    public ResponseEntity<String> total() {
        String urlResource = urlService1 + "/api/all";
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new BasicAuthenticationInterceptor("user", "3053c58d-6d73-4801-826c-89265c08b7e8"));
        restTemplate.setInterceptors(interceptors);
        ResponseEntity<String> response = restTemplate.getForEntity(urlResource, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping("add")
    public ResponseEntity<String> add(@RequestBody KeyValue keyValue){
         return  ResponseEntity.ok("Key: " + keyValue.getKey() + " Value: " + keyValue.getValue());
    }
}
