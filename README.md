# Project Hangout: The Backend API

Project Hangout is a project inspired by the lack of social life every college student has felt at some point in their lives.

It lets users post activities (gaming, studying, hangouts etc) along with their contact information. Other users can then view these activities and contact the poster if interested.

### Features
* **Easy To Use** — All you'll have to do to use it is visit the website! No installations needed
* **Simple authentication process** — No need to log in for viewing posts, only for making posts.
* **Moderated content** — All posts are monitored to check for inappropriate content, ensuring a friendly and welcoming space!
<br>

## (COMING SOON) Getting Started (As a User)

I will post instructions here on how to use the service once I'm done creating the frontend for the API. Thank you for your patience!

See [Getting Started (As a Developer)](#getting-started-as-a-developer) for instructions on how to get a copy up and running for development and testing purposes.

<br>

## Getting Started (As a Developer)

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites
* Java 17 or above
* An IDE with Maven support (IntelliJ IDEA recommended)
* A [PostgreSQL](https://www.w3schools.com/postgresql/postgresql_install.php) or [MySQL](https://www.geeksforgeeks.org/how-to-install-mysql-in-windows/) local database for testing
  * See [Setting Up The Test Database](#setting-up-the-test-database) to set up the test database
* A free account with [API-Ninjas](https://api-ninjas.com/) so that you can use their [profanity filter API](https://api-ninjas.com/api/profanityfilter)

### Installation

* #### Through GitHub:
    I. Clone the repository
  
    II. Open it using an IDE of your choice, it should automatically load in the dependencies since it's a Maven project.

    III. In the application.properties file:
  * Copy your API key from the API-ninjas website and paste it in the "apininjas.api.key" field
  * Enter your database login credentials in the "spring.datasource.username" and "spring.datasource.password" fields.


### Setting Up The Test Database
Run this script in your database to create the table:

* #### For PostgreSQL:
  ```sql
  CREATE TABLE IF NOT EXISTS activity(
      id SERIAL PRIMARY KEY,
      username VARCHAR(25) NOT NULL,
      time_posted TIMESTAMP NOT NULL,
      title VARCHAR(127) NOT NULL,
      description VARCHAR(255) NOT NULL,
      contact_info VARCHAR(127) NOT NULL
  );
  ```

* #### For MySQL:
  ```sql
  CREATE TABLE IF NOT EXISTS activity(
      id INTEGER AUTO_INCREMENT PRIMARY KEY,
      username VARCHAR(25) NOT NULL,
      time_posted DATETIME NOT NULL,
      title VARCHAR(127) NOT NULL,
      description VARCHAR(255) NOT NULL,
      contact_info VARCHAR(127) NOT NULL
  );
  ```

### Testing

This project comes with unit and integration tests. If you want to look at the tests and what they do, they are stored in the src/Test directory

<br>

## Built With

* Java
* [Maven](https://maven.apache.org/)
* [Spring Boot](https://start.spring.io/)
