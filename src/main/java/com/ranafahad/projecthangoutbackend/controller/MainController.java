package com.ranafahad.projecthangoutbackend.controller;

import com.ranafahad.projecthangoutbackend.model.Activity;
import com.ranafahad.projecthangoutbackend.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class MainController {

    private final ActivityService activityService;

    public MainController(ActivityService activityService){
        this.activityService = activityService;
    }

    @GetMapping("activity")
    public ResponseEntity<List<Activity>> getAllActivities(){
        List<Activity> activities;
        try{
            activities = activityService.getAllActivities();
            return ResponseEntity.ok(activities);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("activity")
    public ResponseEntity<String> postActivity(
            @RequestBody Activity activity
    ){
        return activityService.postActivity(activity);
    }

    @GetMapping("activityByUser")
    public ResponseEntity<List<Activity>> getAllActivitiesByUser(
            @RequestParam(required = true) String username
    ){
        List<Activity> activities;
        try{
            activities = activityService.getAllActivitiesByUser(username);
            return ResponseEntity.ok(activities);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
