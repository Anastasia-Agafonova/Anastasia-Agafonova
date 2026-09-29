package ru.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ru.praktikum.api.UserClient;
import ru.praktikum.models.User;
import ru.praktikum.pages.ForgotPasswordPage;
import ru.praktikum.pages.LoginPage;
import ru.praktikum.pages.MainPage;
import ru.praktikum.pages.RegisterPage;
import ru.praktikum.utils.UserGenerator;

public class LoginTest extends BaseTest {

    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void createUser() {

        userClient = new UserClient();

        user = UserGenerator.createRandomUser();

        userClient.createUser(user);

        accessToken = userClient
                .loginUser(user)
                .jsonPath()
                .getString("accessToken");

    }

    @After
    public void deleteUser() {

        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт'")
    @Description("Проверка входа с главной страницы")
    public void loginFromMainPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Кнопка 'Оформить заказ' не отобразилась после логина",
                mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через Личный кабинет")
    @Description("Проверка входа через кнопку Личный кабинет")
    public void loginFromPersonalAccountTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Кнопка 'Оформить заказ' не отобразилась после логина через Личный кабинет",
                mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    @Description("Проверка входа по ссылке из формы регистрации")
    public void loginFromRegisterPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Кнопка 'Оформить заказ' не отобразилась после логина из формы регистрации",
                mainPage.isMakeOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через форму восстановления пароля")
    @Description("Проверка входа по ссылке из формы восстановления пароля")
    public void loginFromForgotPasswordPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPage forgotPasswordPage =
                new ForgotPasswordPage(driver);

        forgotPasswordPage.clickLoginLink();

        loginPage.login(user.getEmail(), user.getPassword());

        Assert.assertTrue("Кнопка 'Оформить заказ' не отобразилась после логина из формы восстановления пароля",
                mainPage.isMakeOrderButtonDisplayed());
    }
}