package ru.praktikum.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;

public class DriverFactory {

    private static final boolean HEADLESS = false;

    public static WebDriver createDriver(String browserName) {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        if (HEADLESS) {
            options.addArguments("--headless=new");
        }

        switch (browserName.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(options);

            case "yandex":
                String userHome = System.getProperty("user.home");

                String cleanPath = Paths.get(
                        userHome,
                        "AppData",
                        "Local",
                        "Yandex",
                        "YandexBrowser",
                        "Application",
                        "browser.exe"
                ).toString();

                System.out.println("Yandex path: " + cleanPath);

                options.setBinary(cleanPath);

                WebDriverManager.chromedriver()
                        .driverVersion("148")
                        .setup();

                return new ChromeDriver(options);

            default:
                throw new IllegalArgumentException(
                        "Поддерживаются только Chrome и Yandex Browser"
                );
        }
    }
}