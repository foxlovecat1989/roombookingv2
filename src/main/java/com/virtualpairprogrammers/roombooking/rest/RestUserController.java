package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.UserRepository;
import com.virtualpairprogrammers.roombooking.model.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
@AllArgsConstructor
public class RestUserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping()
    public User newUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping()
    public User updateUser(@RequestBody User updateUser) {
        User originalUser = userRepository.findById(updateUser.getId()).get();
        originalUser.setName(updateUser.getName());

        return userRepository.save(originalUser);
    }
}
