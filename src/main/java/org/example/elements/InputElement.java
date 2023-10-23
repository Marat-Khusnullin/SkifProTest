package org.example.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Стандартный элемент ввода (Используется на странице регистрации дилера)
 */
public class InputElement {

    private String baseElementPath;

    private WebDriver driver;

    public InputElement(WebDriver driver, String fieldName) {
        this.driver = driver;
        baseElementPath = "//div[./span[text()='" + fieldName + "']]";
    }

    public WebElement getLabel() {
        return driver.findElement(By.xpath(baseElementPath + "//span"));
    }

    public WebElement getInput() {
        return driver.findElement(By.xpath(baseElementPath + "//input"));
    }

    public WebElement getNotification() {
        return driver.findElement(By.xpath(baseElementPath + "//div[contains(@class, 'text-notify')]"));
    }
}
