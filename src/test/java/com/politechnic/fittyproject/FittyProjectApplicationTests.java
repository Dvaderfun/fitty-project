package com.politechnic.fittyproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = FittyProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FittyProjectApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    private String getRootUrl() {
        return "http://localhost:" + port;
    }
    @Test
    public void contextLoads() {
    }
    @Test
    public void testGetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }
    @Test
    public void testGetUserById() {
        UserTest userTest = restTemplate.getForObject(getRootUrl() + "/users/1", UserTest.class);
        System.out.println(userTest.getFirstName());
        assertNotNull(userTest);
    }
    @Test
    public void testCreateUser() {
        UserTest userTest = new UserTest();
        userTest.setEmail("admin@gmail.com");
        userTest.setFirstName("admin");
        userTest.setLastName("admin");
        userTest.setCreatedBy("admin");
        userTest.setUpdatedBy("admin");
        ResponseEntity<UserTest> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", userTest, UserTest.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }
    @Test
    public void testUpdatePost() {
        int id = 1;
        UserTest userTest = restTemplate.getForObject(getRootUrl() + "/users/" + id, UserTest.class);
        userTest.setFirstName("admin1");
        userTest.setLastName("admin2");
        restTemplate.put(getRootUrl() + "/users/" + id, userTest);
        UserTest updatedUserTest = restTemplate.getForObject(getRootUrl() + "/users/" + id, UserTest.class);
        assertNotNull(updatedUserTest);
    }
    @Test
    public void testDeletePost() {
        int id = 2;
        UserTest userTest = restTemplate.getForObject(getRootUrl() + "/users/" + id, UserTest.class);
        assertNotNull(userTest);
        restTemplate.delete(getRootUrl() + "/users/" + id);
        try {
            userTest = restTemplate.getForObject(getRootUrl() + "/users/" + id, UserTest.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}