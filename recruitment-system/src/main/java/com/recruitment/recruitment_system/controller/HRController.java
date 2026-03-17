package com.recruitment.recruitment_system.controller;

import com.recruitment.recruitment_system.model.Application;
import com.recruitment.recruitment_system.service.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr")
@CrossOrigin(origins = "http://localhost:3000")
public class HRController {

    @Autowired
    private ApplicationService applicationService;

    // Get applications with pagination + search
    @GetMapping("/applications")
    public Page<Application> getApplications(

            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size

    ) {

        return applicationService.getApplicationsForHR(search, page, size);
    }

    // Accept application
    @PutMapping("/accept/{id}")
    public Application accept(@PathVariable Long id) {
        return applicationService.updateStatus(id, "ACCEPTED", null);
    }

    // Reject application
    @PutMapping("/reject/{id}")
    public Application reject(
            @PathVariable Long id,
            @RequestParam String reason
    ) {
        return applicationService.updateStatus(id, "REJECTED", reason);
    }

    // Delete application
    @DeleteMapping("/delete/{id}")
    public void deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
    }
}