package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class ForgotPasswordPage {

    private final WebDriver driver;

    // Ссылка Войти
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть по ссылке 'Войти' на странице восстановления пароля")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
