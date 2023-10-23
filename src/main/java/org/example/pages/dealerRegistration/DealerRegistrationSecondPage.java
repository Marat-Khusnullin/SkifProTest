package org.example.pages.dealerRegistration;

import org.example.elements.InputElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Вторая страница "Регистрация дилера"
 */
public class DealerRegistrationSecondPage {

    WebDriver driver;

    public DealerRegistrationSecondPage(WebDriver driver) {
        this.driver = driver;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement getHeader() {
        return driver.findElement(By.xpath("//span"));
    }

    public InputElement getEmailInput() {
        return new InputElement(driver, "Email");
    }

    public InputElement getFullNameInput() {
        return new InputElement(driver, "ФИО");
    }

    public InputElement getPasswordInput() {
        return new InputElement(driver, "Пароль");
    }

    public InputElement getConfirmPasswordInput() {
        return new InputElement(driver, "Подтвердите пароль*");
    }

    public WebElement getCaptchaInput() {
        return driver.findElement(By.xpath("//input[not(@placeholder)]"));
    }

    public WebElement getBackButton() {
        return driver.findElement(By.xpath("//span[text()='Назад']"));
    }

    public WebElement getRegistrationButton() {
        return driver.findElement(By.xpath("//button[./span[text()='Зарегистрироваться']]"));
    }
}
