package ru.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.praktikum.pages.MainPage;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка активации вкладки 'Соусы' при клике")
    public void clickSaucesTabSwitchToSaucesTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesTab();

        Assert.assertTrue("Вкладка 'Соусы' не активировалась", mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка активации вкладки 'Начинки' при клике")
    public void clickFillingsTabSwitchToFillingsTest() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingTab();

        Assert.assertTrue("Вкладка 'Начинки' не активировалась", mainPage.isFillingsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка возврата к вкладке 'Булки' после переключения на другую вкладку")
    public void clickBunsTabSwitchToBunsTest() {
        MainPage mainPage = new MainPage(driver);

        // По умолчанию активны "Булки". Сначала переключаемся на "Соусы", чтобы сменить состояние
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        Assert.assertTrue("Вкладка 'Булки' не активировалась при обратном переключении", mainPage.isBunsTabActive());
    }
}