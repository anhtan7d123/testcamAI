package com.example.camai.controller;

import com.example.camai.entity.EvenApp;
import com.example.camai.entity.MyRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cam")
public class GetEvent {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/get-data")
    public ResponseEntity<?> callOtherAPIWithBody(@RequestBody MyRequestBody myRequestBody, HttpServletRequest request) throws JsonProcessingException {
        String authorizationHeader = request.getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authorizationHeader);
        String url = "http://192.168.14.125:8080/AIViewService/api/aiview/getEventApplicationChartPopup";
        MyRequestBody requestBody = new MyRequestBody();
        requestBody.setApplicationId(myRequestBody.getApplicationId());
        requestBody.setFromDate(myRequestBody.getFromDate());
        requestBody.setToDate(myRequestBody.getToDate());
        HttpEntity<MyRequestBody> request1 = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request1, Map.class);
        Object o = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(o,Map.class);
        return new ResponseEntity<>(map.get("data"), HttpStatus.OK);
    }
}
