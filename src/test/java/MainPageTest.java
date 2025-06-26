package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions();

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.habr.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход на страницу с правилами сайта")
    public void changeLogTest() {
        WebElement webLink = driver.findElement(By.xpath("//a[contains(., 'Устройство сайта')]"));
        webLink.click();

        assertTrue(driver.findElement(By.xpath("//a[@href='/ru/docs/changelog/']")).isDisplayed(),
                "Changelog не найден");
    }

    @Test
    @DisplayName("Переход на форму восстановления пароля")
    public void remindPasswordTest() {
        WebElement enter = driver.findElement(By.xpath("//button[text()='Войти']"));
        enter.click();

        By xpathRemindPassword = By.xpath("//a[contains(., 'Забыли пароль')]");
        assertTrue(driver.findElement(xpathRemindPassword).isDisplayed(), "Нет перехода на форму авторизации");

        WebElement forgotPassword = driver.findElement(xpathRemindPassword);
        forgotPassword.click();
    }

    @Test
    @DisplayName("Смена языка интерфейса")
    public void changeLanguage() {
        WebElement settings = driver.findElement(By.xpath("//button[@data-test-id=\"user-menu-settings\"]"));
        settings.click();

        assertTrue(driver.findElement(By.cssSelector(".tm-page-settings-form__title")).isDisplayed(),
                "Нет перехода на страницу настроек");

        WebElement engLanguage = driver.findElement(By.xpath("//label[.='English'][@class]"));
        engLanguage.click();
    }
}

