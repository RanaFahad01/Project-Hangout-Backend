package com.ranafahad.projecthangoutbackend.repository;

import com.ranafahad.projecthangoutbackend.model.Activity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {

    //The activities are sorted by descending time posted (earliest first)
    @Query("SELECT * FROM activity ORDER BY timeposted DESC LIMIT 25")
    List<Activity> getAllActivities();


    //Returns all the activities posted by a specific user
    //Not used right now, but will be used when authentication is implemented
    @Query("SELECT * FROM activity WHERE username = :username ORDER BY timeposted DESC")
    List<Activity> getAllActivitiesByUser(String username);

}
