package ru.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Кнопка Войти в аккаунт
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    private By makeOrderButton = By.xpath(".//button[text()='Оформить заказ']");

   // Кнопка Личный кабинет
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    // Вкладка Булки
    private final By bunsTab = By.xpath(".//span[text()='Булки']");

    // Вкладка Соусы
    private final By saucesTab = By.xpath(".//span[text()='Соусы']");

    // Вкладка Начинки
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']");

    // Активная вкладка
    private final By currentTab = By.xpath(".//div[contains(@class,'tab_tab_type_current')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Step("Кликнуть по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }

    @Step("Проверить, отображается ли кнопка 'Оформить заказ'")
    public boolean isMakeOrderButtonDisplayed() {
        try {

            return wait.until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton)).isDisplayed();
        } catch (Exception e) {
             return false;
        }
    }

    @Step("Кликнуть по кнопке 'Личный Кабинет'")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Перейти на вкладку 'Булки'")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab))
                .click();
    }

    @Step("Перейти на вкладку 'Соусы'")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab))
                .click();
    }

    @Step("Перейти на вкладку 'Начинки'")
    public void clickFillingTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab))
                .click();
    }

    // Проверка активной вкладки
    @Step("Проверить, активна ли вкладка 'Булки'")
    public boolean isBunsTabActive() {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(currentTab, "Булки"));
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить, активна ли вкладка 'Соусы'")
    public boolean isSaucesTabActive() {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(currentTab, "Соусы"));
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить, активна ли вкладка 'Начинки'")
    public boolean isFillingsTabActive() {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(currentTab, "Начинки"));
        } catch (Exception e) {
            return false;
        }
    }
}
