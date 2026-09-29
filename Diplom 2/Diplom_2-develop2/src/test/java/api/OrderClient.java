package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.OrderRequest;
import static io.restassured.RestAssured.given;

public class OrderClient {

    private static final String CREATE_ORDER_PATH = "/api/orders";

    @Step("Создать заказ с авторизацией")
    public ValidatableResponse createOrder(OrderRequest orderRequest, String token) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .body(orderRequest)
                .when()
                .post(ApiEndpoints.CREATE_ORDER)
                .then();
    }

    @Step("Создать заказ без авторизации")
    public ValidatableResponse createOrderWithoutAuthorization(OrderRequest orderRequest) {
        return given()
                .header("Content-type", "application/json")
                .body(orderRequest)
                .when()
                .post(ApiEndpoints.CREATE_ORDER)
                .then();
    }
}