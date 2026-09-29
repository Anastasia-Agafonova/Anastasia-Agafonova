package ru.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.praktikum.models.User;

public class UserClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru";

    @Step("Создать пользователя {user.email}")
    public Response createUser(User user) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(BASE_URL+ "/api/auth/register");
    }

    // Авторизация пользователя
    @Step("Авторизовать пользователя {user.email}")
    public Response loginUser(User user) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(BASE_URL + "/api/auth/login");
    }

    // Удаление пользователя
    @Step("Удалить пользователя по токену")
    public Response deleteUser(String accessToken) {
        return RestAssured
                .given()
                .header("Authorization", accessToken)
                .delete(BASE_URL + "/api/auth/user");
    }
}
