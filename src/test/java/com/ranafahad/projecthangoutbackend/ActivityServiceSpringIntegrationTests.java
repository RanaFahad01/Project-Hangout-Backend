package com.ranafahad.projecthangoutbackend;

import com.ranafahad.projecthangoutbackend.model.Activity;
import com.ranafahad.projecthangoutbackend.repository.ActivityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ProjectHangoutBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivityServiceSpringIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ActivityRepository activityRepository;

    private final Logger logger = Logger.getLogger(ActivityServiceSpringIntegrationTests.class.getName());

    private Activity activity;

    //Set up the activity object before each test
    @BeforeEach
    public void setUp(){
        activity = new Activity();
        activity.setUserName("TEST_USER_ROMEA");
        activity.setTimePosted(LocalDateTime.parse("2002-07-26T00:17:00"));
        activity.setTitle("EXAMPLE ENTRY - SHOULD NOT BE IN PRODUCTION DATABASE");
        activity.setContactInfo("TEST_CONTACT_INFORMATION");
    }

    //After each test, nullify the activity object and delete entries made by the test
    @AfterEach
    public void tearDown(){
        activity = null;

        //Delete all entries made by the tests, if there are any
        List<Activity> testEntries = activityRepository.getAllActivitiesByUser("TEST_USER_ROMEA");

        if(!testEntries.isEmpty()){
            activityRepository.deleteAll(testEntries);
        }

    }

    @Test
    public void testGetAllActivities() {
        String url = "http://localhost:" + port + "/api/activity";

        ResponseEntity<Activity[]> response = testRestTemplate.getForEntity(url, Activity[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        //Compare the size of activities gotten through the service, to the size of the activities gotten through the repository itself
        List<Activity> activities = List.of(response.getBody());
        assertEquals(activityRepository.getAllActivities().size(), activities.size());
    }

    @Test
    public void testPostActivityNoProfanity() {
        String url = "http://localhost:" + port + "/api/activity";
        activity.setDescription("EXAMPLE DESCRIPTION THAT DOES NOT HAVE ANY INAPPROPRIATE LANGUAGE :D");

        ResponseEntity<String> response = testRestTemplate.postForEntity(url, activity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Activity posted successfully!", response.getBody());
    }


    @Test
    public void testPostActivityYesProfanity() {
        String url = "http://localhost:" + port + "/api/activity";
        activity.setDescription("EXAMPLE DESCRIPTION THAT DOES HAVE INAPPROPRIATE LANGUAGE: SHIT :(");

        ResponseEntity<String> response = testRestTemplate.postForEntity(url, activity, String.class);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("Your entry includes inappropriate language, please edit it.", response.getBody());
    }

}
