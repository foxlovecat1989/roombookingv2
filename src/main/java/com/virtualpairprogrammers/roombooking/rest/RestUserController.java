package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.UserRepository;
import com.virtualpairprogrammers.roombooking.model.entities.AngularUser;
import com.virtualpairprogrammers.roombooking.model.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/users")
@AllArgsConstructor
public class RestUserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<AngularUser> getAllUsers() throws InterruptedException {
        Thread.sleep(3000);

        return userRepository.findAll().stream().map(AngularUser::toAngularUser).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AngularUser getUser(@PathVariable("id") Long id) {
        return AngularUser.toAngularUser(userRepository.findById(id).get());
    }

    @PostMapping()
    public AngularUser newUser(@RequestBody User user) {
        return AngularUser.toAngularUser(userRepository.save(user));
    }

    @PutMapping()
    public AngularUser updateUser(@RequestBody AngularUser angularUser) throws InterruptedException {
        Thread.sleep(3000);
        User originalUser = userRepository.findById(angularUser.getId()).get();
        originalUser.setName(angularUser.getName());
        originalUser.setPassword(originalUser.getPassword());

        return AngularUser.toAngularUser(userRepository.save(originalUser));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable("id") Long id) throws InterruptedException {
        Thread.sleep(3000);
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/resetPassword/{id}")
    public void resetPassword(@PathVariable("id") Long id){
        User user = userRepository.findById(id).get();
        user.setPassword("reset");
        userRepository.save(user);
    }
}
