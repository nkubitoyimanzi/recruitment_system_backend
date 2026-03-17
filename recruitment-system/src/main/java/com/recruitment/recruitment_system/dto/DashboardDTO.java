package com.recruitment.recruitment_system.dto;

public class DashboardDTO {

    private long total;
    private long pending;
    private long accepted;
    private long rejected;

    public DashboardDTO() {}

    public DashboardDTO(long total, long pending, long accepted, long rejected) {
        this.total = total;
        this.pending = pending;
        this.accepted = accepted;
        this.rejected = rejected;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPending() {
        return pending;
    }

    public void setPending(long pending) {
        this.pending = pending;
    }

    public long getAccepted() {
        return accepted;
    }

    public void setAccepted(long accepted) {
        this.accepted = accepted;
    }

    public long getRejected() {
        return rejected;
    }

    public void setRejected(long rejected) {
        this.rejected = rejected;
    }
}
