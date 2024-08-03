package com.ranafahad.projecthangoutbackend;

import com.ranafahad.projecthangoutbackend.model.Activity;
import com.ranafahad.projecthangoutbackend.proxy.ProfanityCheckerProxy;
import com.ranafahad.projecthangoutbackend.repository.ActivityRepository;
import com.ranafahad.projecthangoutbackend.service.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ActivityServiceTests {

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private ProfanityCheckerProxy profanityCheckerProxy;

    @InjectMocks
    private ActivityService activityService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    //Makes a mocked list of 3 Activities, then checks if the getAllActivities method gets 3 activities
    @Test
    public void testGetAllActivities(){
        List<Activity> activities = List.of(new Activity(), new Activity(), new Activity());

        when(activityRepository.getAllActivities()).thenReturn(activities);

        List<Activity> result = activityService.getAllActivities();

        assertEquals(3, result.size());

        //Verify that the getAllActivities method was called exactly one time
        verify(activityRepository,times(1)).getAllActivities();
    }

    //Tests if ActivityService posts an activity if the activity does not contain profanity
    @Test
    public void testPostActivityNoProfanity(){
        Activity activity = new Activity();

        when(profanityCheckerProxy.checkForProfanity(activity)).thenReturn(false);

        ResponseEntity<String> response = activityService.postActivity(activity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Activity posted successfully!", response.getBody());

        //Verify that the .save() method was called exactly one time
        verify(activityRepository, times(1)).save(activity);
    }

    //Tests if ActivityService does not post an activity if the activity contains profanity
    @Test
    public void testPostActivityYesProfanity(){
        Activity activity = new Activity();

        when(profanityCheckerProxy.checkForProfanity(activity)).thenReturn(true);

        ResponseEntity<String> response = activityService.postActivity(activity);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("Your entry includes inappropriate language, please edit it.", response.getBody());

        //Verify that the .save() method was never called
        verify(activityRepository, never()).save(activity);
    }

    //Tests if the proper status code and message is returned if a Runtime Exception occurs on the server
    @Test
    public void testPostActivityExceptionHandling(){
        Activity activity = new Activity();

        when(profanityCheckerProxy.checkForProfanity(activity)).thenThrow(new RuntimeException("Exception :("));

        ResponseEntity<String> response = activityService.postActivity(activity);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Something went wrong with the server! Please check the details below:\nException :(", response.getBody());

        //Verify that the .save() method was never called
        verify(activityRepository, never()).save(activity);
    }

    //Tests the getAllActivitiesByUser method
    public void testGetAllActivitiesByUser() {
        String username = "testUser";
        List<Activity> activities = List.of(new Activity(), new Activity());

        when(activityRepository.getAllActivitiesByUser(username)).thenReturn(activities);

        List<Activity> result = activityService.getAllActivitiesByUser(username);

        assertEquals(2, result.size());

        //Verify the getAllActivitiesByUser method was called exactly once
        verify(activityRepository, times(1)).getAllActivitiesByUser(username);
    }



}
