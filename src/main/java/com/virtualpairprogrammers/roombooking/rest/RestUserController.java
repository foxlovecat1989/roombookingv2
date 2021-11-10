package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.UserRepository;
import com.virtualpairprogrammers.roombooking.model.entities.AngularUser;
import com.virtualpairprogrammers.roombooking.model.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/users")
@AllArgsConstructor
public class RestUserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<AngularUser> getAllUsers(){
        return userRepository.findAll().stream().map(user -> new AngularUser(user)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AngularUser getUser(@PathVariable("id") Long id) {
        return new AngularUser(userRepository.findById(id).get());
    }

    @PostMapping()
    public AngularUser newUser(@RequestBody AngularUser user) {
        return new AngularUser(userRepository.save(user.asUser()));
    }

    @PutMapping()
    public AngularUser updateUser(@RequestBody AngularUser updateUser) {
        User originalUser = userRepository.findById(updateUser.getId()).get();
        originalUser.setName(updateUser.getName());

        return new AngularUser(userRepository.save(originalUser));
    }
}
