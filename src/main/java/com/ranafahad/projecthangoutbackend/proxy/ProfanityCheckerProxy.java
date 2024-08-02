package com.ranafahad.projecthangoutbackend.proxy;

import com.ranafahad.projecthangoutbackend.exception.UnexpectedStatusCodeException;
import com.ranafahad.projecthangoutbackend.model.Activity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

//The class that's used to check entries for profanity and inappropriate language
@Component
public class ProfanityCheckerProxy {

    //Switch profanity checking on or off:
    private final boolean profanityCheckingEnabled = true;

    private final RestTemplate restTemplate;
    private final Logger logger = Logger.getLogger(ProfanityCheckerProxy.class.getName());

    @Value("${apininjas.api.uri}")
    private String apiURI;
    @Value("${apininjas.api.key}")
    private String apiKey;

    public ProfanityCheckerProxy(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    //Takes an activity, checks for profanity and returns a boolean
    public boolean checkForProfanity(Activity activity){

        //Check if profanity checking is turned on or off:
        if(profanityCheckingEnabled) {

            //Puts the username, title, description and contactinfo together to check for profanity in just one HTTP request
            String textToCheck = activity.getUserName() + " " + activity.getTitle() + " "
                    + activity.getDescription() + " " + activity.getContactInfo();

            //Create HttpHeaders and add the API key in header
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Api-Key", apiKey);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            try {
                String finalURI = apiURI + "?text=" + textToCheck;

                ResponseEntity<ProfanityResponse> profanityResponse =
                        restTemplate.exchange(
                                finalURI,
                                HttpMethod.GET,
                                entity,
                                ProfanityResponse.class
                        );

                if (profanityResponse.getStatusCode().is2xxSuccessful() && profanityResponse.getBody() != null) {
                    return profanityResponse.getBody().getHas_profanity();
                } else {
                    throw new UnexpectedStatusCodeException("Unexpected status code: " + profanityResponse.getStatusCode());
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        //If not, just return false
        else {
            return false;
        }

    }


    //For mapping the response by the api to an object
    private static class ProfanityResponse{

        private String original;
        private String censored;
        private Boolean has_profanity;

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public String getCensored() {
            return censored;
        }

        public void setCensored(String censored) {
            this.censored = censored;
        }

        public Boolean getHas_profanity() {
            return has_profanity;
        }

        public void setHas_profanity(Boolean has_profanity) {
            this.has_profanity = has_profanity;
        }
    }


}
