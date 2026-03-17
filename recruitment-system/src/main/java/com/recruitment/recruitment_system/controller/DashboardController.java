package com.recruitment.recruitment_system.controller;

import com.recruitment.recruitment_system.dto.DashboardDTO;
import com.recruitment.recruitment_system.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/stats")
    public DashboardDTO getStats() {
        return dashboardService.getStats();
    }
}
