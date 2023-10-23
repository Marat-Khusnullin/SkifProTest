package tests;

import com.github.javafaker.Faker;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.utils.ConfigReader;
import tests.utils.DriverSetup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CampaignTests {

    public DriverSetup driverSetup;
    public MainPage mainPage;
    public Faker faker;

    @BeforeEach
    public void setUp() {
        driverSetup = new DriverSetup();
        driverSetup.getDriver().navigate().to(ConfigReader.getLoginUrl());
        faker = new Faker();

        LoginPage page = new LoginPage(driverSetup.getDriver());

        page.getLoginInput().sendKeys(ConfigReader.getEmail());
        page.getPasswordInput().sendKeys(ConfigReader.getPassword());
        page.getSignInButton().click();

        mainPage = new MainPage(driverSetup.getDriver());
        mainPage.getAdminPanelButton().click();
    }

    @Test
    @DisplayName("Успешное добавление новой компании")
    public void addNewCompany_success() throws InterruptedException {

        var adminPanel = mainPage.getAdminPanel();

        // Выбираем модуль "Компании"
        adminPanel.selectModule("Компании");

        // Нажимаем кнопку "Добавить компанию"
        adminPanel.getAddCompanyButton().click();

        var addCompanyPanel = mainPage.getAddCompanyPanel();

        // Нажимаем кнопку "Добавить пользователя"
        addCompanyPanel.getAddUserButton().click();

        // Заполняем данные новой компании
        var companyName = faker.company().name();
        addCompanyPanel.getCompanyNameInput().sendKeys(companyName);
        addCompanyPanel.selectTimeZone("(GMT-10:00) Гавайи");
        addCompanyPanel.getSwitchCompanyCheckbox().click();

        // Заполняем данные нового пользователя
        addCompanyPanel.getUserNameInput().sendKeys(faker.name().fullName());
        addCompanyPanel.getUserEmailInput().sendKeys(faker.internet().emailAddress());
        addCompanyPanel.getEnterPasswordInput().sendKeys("password1");
        addCompanyPanel.getConfirmPasswordInput().sendKeys("password1");

        // Нажимаем кнопку "Сохранить"
        addCompanyPanel.getSaveButton().click();

        // Проверяем наличие добавленной компании в списке компаний
        assertTrue(adminPanel.isTableContainsField(companyName), "В списке компаний нет добавленной компании!");
    }

    @Test
    @DisplayName("Успешная отправка приглашения")
    public void sendInvite_success() throws InterruptedException {
        var adminPanel = mainPage.getAdminPanel();

        // Выбираем модуль "Компании"
        adminPanel.selectModule("Компании");

        // Нажимаем кнопку "Пригласить"
        adminPanel.getInviteButton().click();

        // Вводим email для отправки пришлашения
        var email = faker.internet().emailAddress();
        adminPanel.getInviteEmailInput().sendKeys(email);

        // Нажимаем кнопку "Пригласить"
        adminPanel.getSendInviteButton().click();

        Thread.sleep(2000);

        // Проверяем наличие сообщения об успешной отправке
        assertEquals("Приглашение было успешно отправлено на email " + email, mainPage.getNotificationText(), "Сообщение об успешной отправке отсутствует");
    }

    @AfterEach
    public void tearDown() {
        driverSetup.getDriver().quit();
    }
}
