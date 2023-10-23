package org.example.pages.mainPagePanels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Панель "Админ-панель"
 */
public class AdminPanel {

    WebDriver driver;

    String basePanelPath = "//div[./div[text()=' Админ-панель ']]";

    public AdminPanel(WebDriver driver) {
        this.driver = driver;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement getCloseButton() {
        return driver.findElement(By.xpath(basePanelPath + "//i[contains(@class, 'el-icon-close')]"));
    }

    public WebElement getCompanySelectInput() {
        return driver.findElement(By.xpath(basePanelPath + "//div[./p[text()=' Компания ']]//input"));
    }

    public void selectCompany(String companyName) {
        getCompanySelectInput().click();
        driver.findElement(By.xpath("//li/span[text()='" + companyName +"']")).click();
    }

    public WebElement getModuleSelectInput() {
        return driver.findElement(By.xpath(basePanelPath + "//div[./p[text()=' Модуль ']]//input"));
    }

    public WebElement getModuleSearchInput() {
        return driver.findElement(By.xpath(basePanelPath + "//input[@placeholder='Поиск']"));
    }

    public WebElement getAddCompanyButton() {
        return driver.findElement(By.xpath(basePanelPath + "//span[text()=' Добавить компанию ']"));
    }

    public WebElement getInviteButton() {
        return driver.findElement(By.xpath(basePanelPath + "//button[text()=' Пригласить ']"));
    }

    public WebElement getInviteEmailInput() {
        return driver.findElement(By.xpath("//div[contains(@class, 'inviteToCompanyBox')]//input"));
    }

    public WebElement getSendInviteButton() {
        return driver.findElement(By.xpath("//button[./span[contains(text(), 'Пригласить')]]"));
    }

    public boolean isTableContainsField(String fieldName) {
        try {
            driver.findElement(By.xpath("//td[./span[text()=' " + fieldName +" ']]"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void selectModule(String moduleName) {
        getModuleSelectInput().click();
        driver.findElement(By.xpath("//li/span[text()='" + moduleName +"']")).click();
    }
}
