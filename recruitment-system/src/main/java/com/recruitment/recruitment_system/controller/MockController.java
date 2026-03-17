package com.recruitment.recruitment_system.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mock")
public class MockController {

    @GetMapping("/nid/{nid}")
    public Map<String, Object> getNidData(@PathVariable String nid) {

        Map<String, Object> response = new HashMap<>();

        response.put("nid", nid);
        response.put("firstName", "John");
        response.put("lastName", "Doe");
        response.put("dateOfBirth", "1998-05-10");
        response.put("nationality", "Rwandan");

        return response;
    }

    @GetMapping("/nesa/{indexNumber}")
    public Map<String, Object> getNesaData(@PathVariable String indexNumber) {

        Map<String, Object> response = new HashMap<>();

        response.put("indexNumber", indexNumber);
        response.put("candidateName", "Jane Doe");
        response.put("school", "Kigali High School");
        response.put("year", "2020");
        response.put("status", "PASSED");

        return response;
    }
}
