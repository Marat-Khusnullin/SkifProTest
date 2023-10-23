package org.example.pages.mainPagePanels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Панель "Добавить компанию"
 */
public class AddCompanyPanel {

    WebDriver driver;

    String basePanelPath = "//div[./div[./div[text()=' Добавить компанию ']]]";

    public AddCompanyPanel(WebDriver driver) {
        this.driver = driver;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement getCompanyNameInput() {
        return driver.findElement(By.xpath(basePanelPath + "//div[./div[text()=' Имя компании ']]//input"));
    }

    public WebElement getTimeZoneSelectInput() {
        return driver.findElement(By.xpath(basePanelPath + "//div[./div[text()=' Часовой пояс ']]//input"));
    }

    public WebElement getImeiInput() {
        return driver.findElement(By.xpath(basePanelPath+ "//textarea"));
    }

    public WebElement getAddUserButton() {
        return driver.findElement(By.xpath("//span[text()=' ДОБАВИТЬ ПОЛЬЗОВАТЕЛЯ ']"));
    }

    public WebElement getUserNameInput() {
        return driver.findElement(By.xpath(basePanelPath + "//div[./div[text()='Имя пользователя:']]//input"));
    }

    public WebElement getUserEmailInput() {
        return driver.findElement(By.xpath(basePanelPath + "//div[./div[text()='Email:']]//input"));
    }

    public WebElement getEnterPasswordInput() {
        return driver.findElement(By.xpath(basePanelPath + "//div[./div[text()='Введите пароль']]//input"));
    }

    public WebElement getConfirmPasswordInput() {
        return driver.findElement(By.xpath(basePanelPath + "//div[./div[text()=' Повторите пароль ']]//input"));
    }

    public WebElement getSaveButton() {
        return driver.findElement(By.xpath("//button[text()=' Сохранить ']"));
    }

    public WebElement getCancelButton() {
        return driver.findElement(By.xpath("//span[text()=' ОТМЕНИТЬ ']"));
    }

    public WebElement getSwitchCompanyCheckbox() {
        return driver.findElement(By.xpath("//span[text()=' Переключиться в выбранную компанию ']"));
    }

    public void selectTimeZone(String timeZone) throws InterruptedException {
        getTimeZoneSelectInput().click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//li/span[text()='" + timeZone +"']")).click();
    }
}
