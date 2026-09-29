package tests;

import api.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.User;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserWithoutRequiredFieldTest {

    private final UserClient userClient = new UserClient();


    @Test
    @DisplayName("Создание пользователя без email")
    @Description("Проверка ошибки при создании пользователя без обязательного поля email")
    public void createUserWithoutEmailTest() {

        User user = new User(
                null,
                "password",
                "Test User"
        );

        userClient.createUser(user)
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }


    @Test
    @DisplayName("Создание пользователя без пароля")
    @Description("Проверка ошибки при создании пользователя без обязательного поля password")
    public void createUserWithoutPasswordTest() {

        User user = new User(
                "test" + System.currentTimeMillis() + "@test.ru",
                null,
                "Test User"
        );

        userClient.createUser(user)
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }


    @Test
    @DisplayName("Создание пользователя без имени")
    @Description("Проверка ошибки при создании пользователя без обязательного поля name")
    public void createUserWithoutNameTest() {

        User user = new User(
                "test" + System.currentTimeMillis() + "@test.ru",
                "password",
                null
        );

        userClient.createUser(user)
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }
}