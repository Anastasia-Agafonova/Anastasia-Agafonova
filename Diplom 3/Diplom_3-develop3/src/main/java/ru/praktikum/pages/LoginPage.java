package ru.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    // Поле email
    private final By emailInput = By.xpath(".//input[@name='name']");

    // Поле пароль
    private final By passwordInput = By.xpath(".//input[@name='Пароль']");

    // Кнопка Войти
    private final By loginButton = By.xpath(".//button[text()='Войти']");

    // Кнопка Зарегистрироваться
    private final By registerLink = By.xpath(".//a[text()='Зарегистрироваться']");

    // Ссылка на восстановление пароля
    private final By forgotPasswordLink = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Кликнуть по кнопке 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Залогиниться пользователем: {email}")
    public void login(String email, String password) {
    setEmail(email);
    setPassword(password);
    clickLoginButton();
    }

    @Step("Кликнуть по ссылке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Кликнуть по ссылке 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }

    @Step("Проверить, что открылась страница логина")
    public boolean isLoginPageOpened() {
        return new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.urlContains("/login"));
    }
}
