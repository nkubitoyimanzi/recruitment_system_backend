package com.recruitment.recruitment_system.controller;

import com.recruitment.recruitment_system.model.Application;
import com.recruitment.recruitment_system.service.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/applicant")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicantController {

    @Autowired
    private ApplicationService applicationService;


    // USER: Apply for a job
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


    // USER: View their applications
    @GetMapping("/my-applications/{email}")
    public List<Application> getMyApplications(@PathVariable String email) {

        return applicationService.getApplicationsByEmail(email);

    }


    // HR or USER: Download CV
    @GetMapping("/cv/{fileName}")
    public ResponseEntity<Resource> downloadCV(@PathVariable String fileName) throws Exception {

        String uploadDir = System.getProperty("user.dir") + "/uploads/";

        Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();

        File file = filePath.toFile();

        if (!file.exists()) {
            throw new RuntimeException("File not found");
        }

        Resource resource = new UrlResource(filePath.toUri());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}