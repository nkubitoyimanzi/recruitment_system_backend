package com.recruitment.recruitment_system.service;

import com.recruitment.recruitment_system.dto.DashboardDTO;
import com.recruitment.recruitment_system.repository.ApplicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public DashboardDTO getStats() {

        long total = applicationRepository.count();
        long pending = applicationRepository.countByStatus("PENDING");
        long accepted = applicationRepository.countByStatus("ACCEPTED");
        long rejected = applicationRepository.countByStatus("REJECTED");

        return new DashboardDTO(total, pending, accepted, rejected);
    }
}
