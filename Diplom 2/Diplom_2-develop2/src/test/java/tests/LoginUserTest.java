package tests;

import api.UserClient;
import helpers.UserGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class LoginUserTest {

    private final UserClient userClient = new UserClient();

    private User user;
    private String accessToken;


    @Before
    public void createUser() {

        user = UserGenerator.createRandomUser();

        accessToken = userClient.createUser(user)
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .extract()
                .path("accessToken");
    }


    @After
    public void deleteUser() {

        if (accessToken != null) {

            userClient.deleteUser(accessToken)
                    .statusCode(SC_ACCEPTED)
                    .body("success", equalTo(true));
        }
    }


    @Test
    @DisplayName("Авторизация существующего пользователя")
    @Description("Проверка успешной авторизации пользователя с корректными данными")
    public void loginWithExistingUserTest() {

        userClient.loginUser(user)
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }


    @Test
    @DisplayName("Авторизация с неверным email")
    @Description("Проверка ошибки при авторизации с несуществующим email")
    public void loginWithIncorrectEmailTest() {

        User userWithWrongEmail = new User(
                "wrong" + System.currentTimeMillis() + "@test.ru",
                user.getPassword(),
                user.getName()
        );


        userClient.loginUser(userWithWrongEmail)
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }


    @Test
    @DisplayName("Авторизация с неверным паролем")
    @Description("Проверка ошибки при авторизации с неправильным паролем")
    public void loginWithIncorrectPasswordTest() {

        User userWithWrongPassword = new User(
                user.getEmail(),
                "wrongPassword",
                user.getName()
        );


        userClient.loginUser(userWithWrongPassword)
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}