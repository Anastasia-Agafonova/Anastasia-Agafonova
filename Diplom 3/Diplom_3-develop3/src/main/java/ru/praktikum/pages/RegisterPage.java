package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class RegisterPage {

    private final WebDriver driver;

    // Поля Имя
    private final By nameInput = By.xpath(".//label[text()='Имя']/following-sibling::input");

    // Поле Email
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");

    // Поле Пароль
    private final By passwordInput = By.xpath(".//input[@name='Пароль']");

    // Кнопка Зарегистрироваться
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    // Ссылка Войти
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    // Ошибка Некорректный пароль
    private final By passwordError = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести имя: {name}")
    public void enterName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Кликнуть по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Зарегистрировать пользователя с именем: {name} и email: {email}")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    @Step("Кликнуть по ссылке 'Войти' на странице регистрации")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Получить текст ошибки некорректного пароля")
    public String getPasswordErrorText() {
        return driver.findElement(passwordError).getText();
    }
}
