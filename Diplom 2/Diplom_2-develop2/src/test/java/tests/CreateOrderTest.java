package tests;

import api.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.OrderRequest;
import org.junit.Test;
import java.util.List;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class CreateOrderTest {

    private final OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Создание заказа авторизованным пользователем")
    @Description("Проверка успешного создания заказа с авторизацией")
    public void createOrderWithAuthorizationTest() {
        OrderRequest orderRequest = new OrderRequest(List.of("61c0c5a71d1f82001bdaaa6d"));

        orderClient.createOrder(orderRequest, "token")
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    @Description("Проверка создания заказа без токена")
    public void createOrderWithoutAuthorizationTest() {
        OrderRequest orderRequest = new OrderRequest(List.of("61c0c5a71d1f82001bdaaa6d"));

        orderClient.createOrderWithoutAuthorization(orderRequest)
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Создание заказа с ингредиентами")
    @Description("Проверка создания заказа с несколькими ингредиентами")
    public void createOrderWithIngredientsTest() {
        OrderRequest orderRequest = new OrderRequest(List.of(
                "61c0c5a71d1f82001bdaaa6d",
                "61c0c5a71d1f82001bdaaa6f"
        ));

        orderClient.createOrderWithoutAuthorization(orderRequest)
                .statusCode(SC_OK)
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    @Description("Проверка ошибки 400 Bad Request при отправке пустого списка ингредиентов")
    public void createOrderWithoutIngredientsTest() {
        OrderRequest orderRequest = new OrderRequest(List.of());

        orderClient.createOrderWithoutAuthorization(orderRequest)
                .statusCode(SC_BAD_REQUEST)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа с неверным хешем ингредиента")
    @Description("Проверка ошибки 500 Internal Server Error при передаче несуществующего ID")
    public void createOrderWithIncorrectIngredientHashTest() {
        OrderRequest orderRequest = new OrderRequest(List.of("123456789"));

        orderClient.createOrderWithoutAuthorization(orderRequest)
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }
}