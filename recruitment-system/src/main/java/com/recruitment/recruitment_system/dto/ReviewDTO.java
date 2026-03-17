package com.recruitment.recruitment_system.dto;

public class ReviewDTO {

    private String reason;

    public ReviewDTO() {
    }

    public ReviewDTO(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
