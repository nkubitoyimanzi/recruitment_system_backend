package com.recruitment.recruitment_system.repository;

import com.recruitment.recruitment_system.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
