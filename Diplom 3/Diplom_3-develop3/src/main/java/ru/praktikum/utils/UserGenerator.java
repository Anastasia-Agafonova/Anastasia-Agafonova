package ru.praktikum.utils;

import ru.praktikum.models.User;

import java.util.UUID;

public class UserGenerator {

    public static User createRandomUser() {
        String email = UUID.randomUUID() + "@test.ru";

        return new User(email, "password", "Test User");
    }
}