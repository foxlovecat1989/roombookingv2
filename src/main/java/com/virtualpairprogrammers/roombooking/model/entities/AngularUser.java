package com.virtualpairprogrammers.roombooking.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class AngularUser {
    private Long id;
    private String name;

     public AngularUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public User asUser() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setPassword("");

        return user;
    }
}
