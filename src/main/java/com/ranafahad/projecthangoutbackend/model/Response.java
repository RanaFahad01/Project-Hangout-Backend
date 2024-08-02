package com.ranafahad.projecthangoutbackend.model;

public class Response {
    private boolean posted;
    private String status;

    public boolean isPosted() {
        return posted;
    }

    public void setPosted(boolean posted) {
        this.posted = posted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
