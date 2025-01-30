package com.microservice_bank.microservice_bank.controller;

import com.microservice_bank.microservice_bank.exception.UserNotFaundException;
import com.microservice_bank.microservice_bank.model.User;
import com.microservice_bank.microservice_bank.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepos userRepos;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {

        return userRepos.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){

        return userRepos.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepos.findById(id)
                .orElseThrow(()->new UserNotFaundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepos.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setFirst_name(newUser.getFirst_name());
                    user.setSecond_name(newUser.getSecond_name());
                    user.setEmail(newUser.getEmail());
                    user.setPhone(newUser.getPhone());
                    return userRepos.save(user);
                }).orElseThrow(()-> new UserNotFaundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepos.existsById(id)){
            throw new UserNotFaundException(id);
        }

        userRepos.deleteById(id);
        return "User with id " + id + " has been deleted success.";

    }
}







