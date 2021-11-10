package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.UserRepository;
import com.virtualpairprogrammers.roombooking.model.entities.AngularUser;
import com.virtualpairprogrammers.roombooking.model.entities.User;
import lombok.AllArgsConstructor;
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
        return userRepository.findAll().stream().map(AngularUser::toAngularUser).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AngularUser getUser(@PathVariable("id") Long id) {
        return AngularUser.toAngularUser(userRepository.findById(id).get());
    }

    @PostMapping()
    public AngularUser newUser(@RequestBody AngularUser angularUser) {
        return AngularUser.toAngularUser(userRepository.save(AngularUser.toUser(angularUser)));
    }

    @PutMapping()
    public AngularUser updateUser(@RequestBody AngularUser angularUser) {
        User originalUser = userRepository.findById(angularUser.getId()).get();
        originalUser.setName(angularUser.getName());

        return AngularUser.toAngularUser(userRepository.save(AngularUser.toUser(angularUser)));
    }
}
