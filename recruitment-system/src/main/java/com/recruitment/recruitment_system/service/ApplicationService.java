package com.recruitment.recruitment_system.service;

import com.cloudinary.Cloudinary;
import com.recruitment.recruitment_system.model.Application;
import com.recruitment.recruitment_system.repository.ApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired(required = false)
    private Cloudinary cloudinary;

    // ✅ APPLY
    public String submitApplication(
            String firstName,
            String lastName,
            String email,
            String phone,
            MultipartFile cv,
            Long jobId
    ) throws Exception {

        Application application = new Application();

        application.setFirstName(firstName);
        application.setLastName(lastName);
        application.setEmail(email);
        application.setPhone(phone);
        application.setJobId(jobId);
        application.setStatus("PENDING");

        // ✅ Upload CV to Cloudinary
        if (cloudinary != null && !cv.isEmpty()) {

            Map uploadResult = cloudinary.uploader().upload(cv.getBytes(), Map.of());

            String cvUrl = uploadResult.get("secure_url").toString();

            application.setCvUrl(cvUrl);
        }

        applicationRepository.save(application);

        return "Application submitted successfully";
    }

    // ✅ USER: get own applications
    public List<Application> getApplicationsByEmail(String email) {
        return applicationRepository.findByEmail(email);
    }

    // ✅ HR: pagination + search
    public Page<Application> getApplicationsForHR(String search, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        if (search == null || search.isEmpty()) {
            return applicationRepository.findAll(pageable);
        } else {
            return applicationRepository.findByLastNameContainingIgnoreCase(search, pageable);
        }
    }

    // ✅ HR: accept
    public void acceptApplication(Long id) {
        Application app = applicationRepository.findById(id).orElseThrow();
        app.setStatus("ACCEPTED");
        applicationRepository.save(app);
    }

    // ✅ HR: reject
    public void rejectApplication(Long id, String reason) {
        Application app = applicationRepository.findById(id).orElseThrow();
        app.setStatus("REJECTED");
        app.setRejectionReason(reason);
        applicationRepository.save(app);
    }

    // ✅ HR: delete
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}