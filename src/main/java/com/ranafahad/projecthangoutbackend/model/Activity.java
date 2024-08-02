package com.ranafahad.projecthangoutbackend.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

//The model class, mapped to the "activity" table in Postgres
public class Activity {

    @Id
    @Column("id")
    private long id;

    @Column("username")
    private String userName;

    @Column("time_posted")
    private LocalDateTime timePosted;

    @Column("title")
    private String title;

    @Column("description")
    private String description;

    @Column("contact_info")
    private String contactInfo;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(LocalDateTime timePosted) {
        this.timePosted = timePosted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
