package org.example.redisstudy.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class User {

    private final Long id;

    private final String name;

    private final String email;

    private final String tier;

    @Builder
    public User(Long id, String name, String email, String tier) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tier = tier;
    }

    public User change(String name, String email, String tier) {
        return User.builder()
                .id(this.id)
                .name(name)
                .email(email)
                .tier(tier)
                .build();
    }

    public static List<User> getUsers() {
        User first = User.builder()
                .id(1L)
                .name("gihyung1")
                .email("gihyung.coding1@gmail.com")
                .tier("gold")
                .build();
        User second = User.builder()
                .id(2L)
                .name("gihyung2")
                .email("gihyung.coding2@gmail.com")
                .tier("silver")
                .build();

        return Arrays.asList(first, second);
    }
}
