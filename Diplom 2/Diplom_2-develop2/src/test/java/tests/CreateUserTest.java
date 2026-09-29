package tests;

import api.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.User;
import org.junit.After;
import org.junit.Test;
import helpers.UserGenerator;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest {

    private final UserClient userClient = new UserClient();

    private String accessToken;


    @After
    public void deleteCreatedUser() {

        if (accessToken != null) {
            userClient.deleteUser(accessToken)
                    .statusCode(SC_ACCEPTED)
                    .body("success", equalTo(true));
        }
    }


    @Test
    @DisplayName("Создание уникального пользователя")
    @Description("Проверка успешного создания нового пользователя")
    public void createUniqueUserTest() {

        User user = UserGenerator.createRandomUser();

        accessToken = userClient.createUser(user)
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract()
                .path("accessToken");
    }


    @Test
    @DisplayName("Создание уже зарегистрированного пользователя")
    @Description("Проверка ошибки при попытке повторной регистрации пользователя")
    public void createAlreadyRegisteredUserTest() {

        User user = UserGenerator.createRandomUser();

        accessToken = userClient.createUser(user)
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract()
                .path("accessToken");


        userClient.createUser(user)
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
    }
}