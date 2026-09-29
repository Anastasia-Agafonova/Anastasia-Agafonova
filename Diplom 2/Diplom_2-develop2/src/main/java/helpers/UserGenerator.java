package helpers;

import models.User;
import java.util.UUID;

public class UserGenerator {

    public static User createRandomUser() {
        String email = UUID.randomUUID().toString() + "@test.ru";

        return new User(email, "password", "Test User");
    }
}
