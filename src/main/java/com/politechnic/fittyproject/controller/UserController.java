package com.politechnic.fittyproject.controller;

import com.politechnic.fittyproject.entity.User;
import com.politechnic.fittyproject.entity.WeightData;
import com.politechnic.fittyproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /**
     * Get weight from User
     *
     * @param token the token of current user
     * @return the list of user's weight data
     */


    @PostMapping("/getWeight")
    public ArrayList<WeightData> getWeight(@Valid @RequestParam String token) {
        WeightData weightData = new WeightData();
        weightData.setWeight(10000);

        ArrayList<WeightData> weightData1 = new ArrayList<>();
        weightData1.add(weightData);

        User user = new User();

        user.setWeightData(weightData1);

       // userRepository.save(user);
        return weightData1;
    }
}
