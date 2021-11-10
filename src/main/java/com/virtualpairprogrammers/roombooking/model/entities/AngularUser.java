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

     public static AngularUser toAngularUser(User user) {
        AngularUser angularUser = new AngularUser();
        angularUser.id = user.getId();
        angularUser.name = user.getName();

        return angularUser;
    }

    public static User toUser(AngularUser angularUser) {
        User user = new User();
        user.setId(angularUser.getId());
        user.setName(angularUser.getName());
        user.setPassword("");

        return user;
    }
}
