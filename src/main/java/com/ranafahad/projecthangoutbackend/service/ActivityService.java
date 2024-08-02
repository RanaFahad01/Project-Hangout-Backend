package com.ranafahad.projecthangoutbackend.service;

import com.ranafahad.projecthangoutbackend.model.Activity;
import com.ranafahad.projecthangoutbackend.model.Response;
import com.ranafahad.projecthangoutbackend.proxy.ProfanityCheckerProxy;
import com.ranafahad.projecthangoutbackend.repository.ActivityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ProfanityCheckerProxy profanityCheckerProxy;

    public ActivityService(ActivityRepository activityRepository,
                           ProfanityCheckerProxy profanityCheckerProxy)
    {
        this.activityRepository = activityRepository;
        this.profanityCheckerProxy = profanityCheckerProxy;
    }

    public List<Activity> getAllActivities(){
        return activityRepository.getAllActivities();
    }

    //If it doesn't have profanity, post it. If not, don't.
    public ResponseEntity<String> postActivity(Activity activity){
        ResponseEntity<String> response;

        try {
            boolean hasProfanity = profanityCheckerProxy.checkForProfanity(activity);

            if (hasProfanity) {

                response = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .body("Your entry includes inappropriate language, please edit it.");

            } else {

                activityRepository.save(activity);
                response = ResponseEntity.ok("Activity posted successfully!");

            }

            return response;

        } catch (RuntimeException e){

            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong with the server! Please check the details below:\n" + e.getMessage());
            return response;

        }


    }

    //Not used right now, will be used when authentication is implemented
    public List<Activity> getAllActivitiesByUser(String username){
        return activityRepository.getAllActivitiesByUser(username);
    }



}
