package org.example.pages.dealerRegistration;

import org.example.elements.InputElement;
import org.example.elements.SelectElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Первая страница "Регистрация дилера"
 */
public class DealerRegistrationFirstPage {

    WebDriver driver;

    public DealerRegistrationFirstPage(WebDriver driver) {
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

    public SelectElement getOrganizationTypeSelect() {
        return new SelectElement(driver, " Тип организации ");
    }

    public InputElement getCompanyNameInput() {
        return new InputElement(driver, "Название компании");
    }

    public InputElement getAddressInput() {
        return new InputElement(driver, " Адрес ");
    }

    public InputElement getServiceDescriptionInput() {
        return new InputElement(driver, " Описание услуг ");
    }

    public InputElement getTariffDescriptionInput() {
        return new InputElement(driver, " Описание тарифа ");
    }

    public InputElement getInnInput() {
        return new InputElement(driver, " ИНН ");
    }

    public InputElement getPhoneInput() {
        return new InputElement(driver, "Телефон:");
    }

    public WebElement getSignInButton() {
        return driver.findElement(By.xpath("//p[text()='Войти']"));
    }

    public WebElement getNextButton() {
        return driver.findElement(By.xpath("//button[./span[text()='Далее']]"));
    }

}
