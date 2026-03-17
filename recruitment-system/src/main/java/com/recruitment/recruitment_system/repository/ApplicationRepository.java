package com.recruitment.recruitment_system.repository;

import com.recruitment.recruitment_system.model.Application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    // Search applications by last name (HR Dashboard)
    Page<Application> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);

    // Prevent applying to the same job twice
    boolean existsByEmailAndJob_Id(String email, Long jobId);

    // Count applications by status (HR statistics)
    long countByStatus(String status);

    // Get applications for a specific user (My Applications page)
    List<Application> findByEmail(String email);

    // Sort applications by first name (HR dashboard)
    List<Application> findAllByOrderByFirstNameAsc();

}