package com.recruitment.recruitment_system.service;

import com.recruitment.recruitment_system.model.Application;
import com.recruitment.recruitment_system.repository.ApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HRService {

    @Autowired
    private ApplicationRepository applicationRepository;

    // Get all applications
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // Accept applicant
    public Application acceptApplication(Long id) {

        Optional<Application> optional = applicationRepository.findById(id);

        if (optional.isPresent()) {

            Application app = optional.get();
            app.setStatus("ACCEPTED");

            return applicationRepository.save(app);
        }

        return null;
    }

    // Reject applicant
    public Application rejectApplication(Long id) {

        Optional<Application> optional = applicationRepository.findById(id);

        if (optional.isPresent()) {

            Application app = optional.get();
            app.setStatus("REJECTED");

            return applicationRepository.save(app);
        }

        return null;
    }

    // Delete application
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }
}
