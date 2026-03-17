package com.recruitment.recruitment_system.controller;

import com.recruitment.recruitment_system.model.Application;
import com.recruitment.recruitment_system.service.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/applicant")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicantController {

    @Autowired
    private ApplicationService applicationService;

    // ✅ Apply
    @PostMapping("/apply")
    public String apply(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam MultipartFile cv,
            @RequestParam Long jobId
    ) throws Exception {

        return applicationService.submitApplication(
                firstName,
                lastName,
                email,
                phone,
                cv,
                jobId
        );
    }

    // ✅ View applications
    @GetMapping("/my-applications/{email}")
    public List<Application> getMyApplications(@PathVariable String email) {
        return applicationService.getApplicationsByEmail(email);
    }
}