package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Страница входа
 */
public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement getLoginInput() {
        return driver.findElement(By.xpath("//div[./label[text()=' Логин: ']]//input"));
    }

    public WebElement getPasswordInput() {
        return driver.findElement(By.xpath("//div[./label[text()=' Пароль: ']]//input"));
    }

    public WebElement getSignInButton() {
        return driver.findElement(By.xpath("//button[text()=' Войти ']"));
    }

}
