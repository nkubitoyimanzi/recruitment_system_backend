package com.recruitment.recruitment_system.service;

import com.recruitment.recruitment_system.model.Application;
import com.recruitment.recruitment_system.model.Job;
import com.recruitment.recruitment_system.repository.ApplicationRepository;
import com.recruitment.recruitment_system.repository.JobRepository;
import com.recruitment.recruitment_system.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    private final String uploadDir = System.getProperty("user.dir") + "/uploads/";


    // USER: View their applications
    public List<Application> getApplicationsByEmail(String email) {
        return applicationRepository.findByEmail(email);
    }


    // USER: Apply for a job
    public String submitApplication(
            String firstName,
            String lastName,
            String email,
            String phone,
            MultipartFile cv,
            Long jobId
    ) throws IOException {

        // CHECK USER EXISTS
        if (!userRepository.existsByEmail(email)) {
            return "You must register before applying.";
        }

        // PREVENT APPLYING TWICE TO SAME JOB
        if (applicationRepository.existsByEmailAndJob_Id(email, jobId)) {
            return "You have already applied for this job.";
        }

        File folder = new File(uploadDir);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + cv.getOriginalFilename();

        File destination = new File(folder, fileName);

        cv.transferTo(destination);

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Application application = new Application();

        application.setFirstName(firstName);
        application.setLastName(lastName);
        application.setEmail(email);
        application.setPhone(phone);
        application.setCvFileName(fileName);
        application.setStatus("PENDING");
        application.setJob(job);

        applicationRepository.save(application);

        return "Application submitted successfully";
    }


    // HR: Get all applications
    public List<Application> getAllApplications() {
        return applicationRepository.findAll(Sort.by("firstName").ascending());
    }


    // HR: Accept / Reject application
    public Application updateStatus(Long id, String status, String reason) {

        Optional<Application> optionalApplication = applicationRepository.findById(id);

        if (optionalApplication.isPresent()) {

            Application application = optionalApplication.get();

            application.setStatus(status);

            if (status.equals("REJECTED")) {
                application.setRejectionReason(reason);
            }

            return applicationRepository.save(application);
        }

        return null;
    }


    // HR: Delete application
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }


    // HR DASHBOARD
    // - Sorted by last name
    // - Search by name
    // - Pagination
    public Page<Application> getApplicationsForHR(String search, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("lastName").ascending());

        if (search == null || search.trim().isEmpty()) {
            return applicationRepository.findAll(pageable);
        }

        return applicationRepository.findByLastNameContainingIgnoreCase(search, pageable);
    }
}