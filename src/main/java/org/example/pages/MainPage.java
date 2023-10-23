package org.example.pages;

import org.example.pages.mainPagePanels.AddCompanyPanel;
import org.example.pages.mainPagePanels.AdminPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Основная страница сервиса
 */
public class MainPage {

    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement getAdminPanelButton() {
        return driver.findElement(By.xpath("//button[./span[text()=' Админ панель ']]"));
    }

    public AdminPanel getAdminPanel() {
        return new AdminPanel(driver);
    }

    public AddCompanyPanel getAddCompanyPanel() {
        return new AddCompanyPanel(driver);
    }

    public String getNotificationText() {
        return driver.findElement(By.xpath("//div[contains(@class, 'el-notification right')]//p")).getText();
    }
}
