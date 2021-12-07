package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.data.UserRepository;
import com.virtualpairprogrammers.roombooking.model.entities.AngularUser;
import com.virtualpairprogrammers.roombooking.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class RestUserController {

    private final UserRepository userRepository;
    private final int INDEX_OF_ROLE_ENDS = 5;
    private final int CLEAN_COOKIE_SET_TO_ZERO = 0;

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

    @GetMapping("/currentUserRole")
    public Map<String, String> getCurrentUserRole(){
        Collection<GrantedAuthority> roles =
                (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String role = "";
        if(roles.size() > 0){
            GrantedAuthority grantedAuthority = roles.iterator().next();
            role = grantedAuthority.getAuthority().substring(INDEX_OF_ROLE_ENDS);
        }
        Map<String, String> results = new HashMap<>();
        results.put("role", role);

        return results;
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("token", null);
        cookie.setPath("/api");
        cookie.setHttpOnly(true);
        // TODO: when in production must do cookie.setSecure(true);
        cookie.setMaxAge(CLEAN_COOKIE_SET_TO_ZERO);
        SecurityContextHolder.getContext().setAuthentication(null);

        return "";
    }
}
