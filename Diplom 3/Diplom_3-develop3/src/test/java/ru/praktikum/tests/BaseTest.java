package ru.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.praktikum.utils.Constants;
import ru.praktikum.utils.DriverFactory;
import ru.praktikum.pages.MainPage;

public class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;

    @Before
    public void setUp() {

        String browser = System.getProperty("browser", "chrome");

        driver = DriverFactory.createDriver(browser);

        driver.get(Constants.BASE_URL);

        mainPage = new MainPage(driver);
    }

    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
