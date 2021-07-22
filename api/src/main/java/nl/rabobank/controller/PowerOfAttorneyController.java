package nl.rabobank.controller;

import nl.rabobank.authorizations.PowerOfAttorney;
import nl.rabobank.mongo.repository.PowerOfAttorneyRepository;
import nl.rabobank.service.PowerOfAttorneyService;
import nl.rabobank.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller for the POA api
 */
@RestController
@RequestMapping(value = "/poa")
public class PowerOfAttorneyController {

    @Autowired
    private PowerOfAttorneyService userService;

    @Autowired
    private PowerOfAttorneyRepository powerOfAttorneyRepository;

    // create new user
    @PostMapping(value = "/create-user")
    public User createUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }
    // update user
    @PutMapping(value = "/update-user/{id}")
    public User updateUser(@PathVariable("id") String id, @RequestBody User user) {
        return userService.updateUserInfo(id, user);
    }

    // add accounts
    @PutMapping(value = "/update-account/{id}")
    public User updateAccount(@PathVariable("id") String id, @RequestBody User user) {
        return userService.updateAccount(id, user);
    }

    // add power of attorney
    @PutMapping(value = "/update-poa/{id}")
    public User updatePoa(@PathVariable("id") String id, @RequestBody User user) {
        return userService.updatePoa(id, user);
    }

    // get User by ID
    @GetMapping(value = "/get-poas/{id}")
    public List<PowerOfAttorney> getPoas(@PathVariable("id") String userId) {
        return userService.getPoas(userId);
    }

    // get User by ID
    @GetMapping(value = "/get-user/{id}")
    public User getUserById(@PathVariable("id") String userId) {
        return userService.getUserById(userId);
    }





}