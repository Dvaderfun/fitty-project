package com.politechnic.fittyproject.controller;

import com.politechnic.fittyproject.entity.UserTest;
import com.politechnic.fittyproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserControllerTest {
    @Autowired
    private UserRepository userRepository;
    /**
     * Get all users list.
     *
     * @return the list
     */
    @GetMapping("/users")
    public List<UserTest> getAllUsers() {
        return userRepository.findAll();
    }
    /**
     * Gets users by id.
     *
     * @param userId the user id
     * @return the users by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserTest> getUsersById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        UserTest userTest =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("UserTest not found on :: " + userId));
        return ResponseEntity.ok().body(userTest);
    }
    /**
     * Create userTest userTest.
     *
     * @param userTest the userTest
     * @return the userTest
     */
    @PostMapping("/users")
    public UserTest createUser(@Valid @RequestBody UserTest userTest) {
        return userRepository.save(userTest);
    }
    /**
     * Update user response entity.
     *
     * @param userId the user id
     * @param userTestDetails the user details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<UserTest> updateUser(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody UserTest userTestDetails)
            throws ResourceNotFoundException {
        UserTest userTest =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("UserTest not found on :: " + userId));
        userTest.setEmail(userTestDetails.getEmail());
        userTest.setLastName(userTestDetails.getLastName());
        userTest.setFirstName(userTestDetails.getFirstName());
        userTest.setUpdatedAt(new Date());
        final UserTest updatedUserTest = userRepository.save(userTest);
        return ResponseEntity.ok(updatedUserTest);
    }
    /**
     * Delete user map.
     *
     * @param userId the user id
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
        UserTest userTest =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("UserTest not found on :: " + userId));
        userRepository.delete(userTest);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException() {
        }

        public ResourceNotFoundException(String message) {
            super(message);
        }

        public ResourceNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

        public ResourceNotFoundException(Throwable cause) {
            super(cause);
        }

        public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}