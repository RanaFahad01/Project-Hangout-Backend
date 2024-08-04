# Project Hangout: The Backend API Usage Demo
Please refer to this demo to see how the API is used.

## Endpoints exposed:
* "/api/activity":
  * GET: Returns a JSON array of the last 25 activities posted, sorted by earliest first
  * POST: Takes an Activity object in the request body and submits it for posting
* "/api/activityByUser":
  * GET: Takes a username in the request parameter (required), then returns a list of activites posted by that user sorted by earliest first
 
## Usage examples:
* "/api/activity":
  * GET:
    * Response:
      
      ```json
      [
        {
            "id": 3,
            "userName": "exampleuser3",
            "timePosted": "2024-01-03T14:00:00",
            "title": "Anybody down for game3?",
            "description": "Just wanna play some game3 man",
            "contactInfo": "Instagram person3, Discord person3lol"
        },
        {
            "id": 2,
            "userName": "exampleuser2",
            "timePosted": "2024-01-02T13:00:00",
            "title": "Anybody down for game2?",
            "description": "Just wanna play some game2 man",
            "contactInfo": "Instagram person2, Discord person2lol"
        },
        {
            "id": 1,
            "userName": "exampleuser1",
            "timePosted": "2024-01-01T12:00:00",
            "title": "Anybody down for game1?",
            "description": "Just wanna play some game1 man",
            "contactInfo": "Instagram person1, Discord person1lol"
        }
      ]
      ```

  * POST:
    * First request's body:

      ```json
      {
        "userName":"ExampleMan",
        "timePosted":"2024-08-01T21:04:00",
        "title":"I want to play some Fortnite!",
        "description":"Just wanna play some fortnite. I'm cracked in ranked.",
        "contactInfo":"Instagram ranafahad"
      }
      ```
    * Response to first request:
      
      The response status will be ```200 OK```
      ``` json
      Activity posted successfully!
      ```
      ---
     * Second request's body:

        ```json
        {
          "userName":"ExampleMan",
          "timePosted":"2024-08-01T21:04:00",
          "title":"I want to play some Fortnite!",
          "description":"Just wanna play some fortnite. I'm shit in ranked.",
          "contactInfo":"Instagram ranafahad"
        }
        ```
      * Response to second request:
   
        The response status will be ```422 UNPROCESSABLE_ENTITY```
        ``` json
        Your entry includes inappropriate language, please edit it.
        ```
<br>

* "/api/activityByUser":

  * GET:
    *  URL: ``` http://localhost:PORT/api/activityByUser?username=exampleuser1 ```
    *  Response to request:

       A JSON array of activities posted by that user:
       ```json
       [
        {
            "id": 1,
            "userName": "exampleuser1",
            "timePosted": "2024-01-01T12:00:00",
            "title": "Anybody down for game1?",
            "description": "Just wanna play some game1 man",
            "contactInfo": "Instagram person1, Discord person1lol"
        }
        ]
       ```

       **NOTE: The "username" request parameter is mandatory. <br>
       Leaving it out or writing it incorrectly will result in the ```400 BAD_REQUEST``` response status.**
