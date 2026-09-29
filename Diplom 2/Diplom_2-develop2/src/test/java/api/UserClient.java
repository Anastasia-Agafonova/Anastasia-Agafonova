package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("Создать пользователя с email {user.email}")
    public ValidatableResponse createUser(User user) {

         return given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(ApiEndpoints.REGISTER)
                .then();
    }

    @Step("Авторизовать пользователя с email {user.email}")
    public ValidatableResponse loginUser(User user) {

        return given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(ApiEndpoints.LOGIN)
                .then();
    }

    @Step("Удалить пользователя")
    public ValidatableResponse deleteUser(String accessToken) {

        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(ApiEndpoints.DELETE_USER)
                .then();
    }
}