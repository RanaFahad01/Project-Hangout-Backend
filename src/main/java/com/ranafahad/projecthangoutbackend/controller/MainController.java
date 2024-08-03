package com.ranafahad.projecthangoutbackend.controller;

import com.ranafahad.projecthangoutbackend.model.Activity;
import com.ranafahad.projecthangoutbackend.service.ActivityService;
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
        return ResponseEntity.ok(activityService.getAllActivities());
    }

    @PostMapping("activity")
    public ResponseEntity<String> postActivity(
            @RequestBody Activity activity
    ){
        return activityService.postActivity(activity);
    }

    @PostMapping("activityByUser")
    public ResponseEntity<List<Activity>> getAllActivitiesByUser(
            @RequestParam(required = true) String username
    ){
        return ResponseEntity.ok(activityService.getAllActivitiesByUser(username));
    }

}
