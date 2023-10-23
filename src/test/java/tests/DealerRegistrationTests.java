package tests;

import com.github.javafaker.Faker;
import org.example.pages.dealerRegistration.DealerRegistrationFirstPage;
import org.example.pages.dealerRegistration.DealerRegistrationSecondPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.utils.ConfigReader;
import tests.utils.DriverSetup;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealerRegistrationTests {

    public DriverSetup driverSetup;
    Faker faker;

    @BeforeEach
    public void setUp() {
        driverSetup = new DriverSetup();
        driverSetup.getDriver().navigate().to(ConfigReader.getDealerRegistrationUrl());
        faker = new Faker();
    }

    @Test
    @DisplayName("Успешная регистрация дилера")
    public void dealerRegistration_success() {

        ChromeDriver driver = driverSetup.getDriver();

        DealerRegistrationFirstPage firstPage = new DealerRegistrationFirstPage(driver);

        // Заполняем данные на первой странице регистрации дилера
        firstPage.getOrganizationTypeSelect().selectElement("Юридическое лицо");
        firstPage.getCompanyNameInput().getInput().sendKeys(faker.company().name());
        firstPage.getAddressInput().getInput().sendKeys(faker.address().fullAddress());
        firstPage.getServiceDescriptionInput().getInput().sendKeys(faker.shakespeare().hamletQuote());
        firstPage.getTariffDescriptionInput().getInput().sendKeys(faker.shakespeare().hamletQuote());

        firstPage.getInnInput().getInput().sendKeys("453");
        firstPage.getPhoneInput().getInput().sendKeys(faker.phoneNumber().cellPhone());

        // Нажимаем кнопку "Далее"
        firstPage.getNextButton().click();

        // Заполняем данные на второй странице регистрации дилера
        DealerRegistrationSecondPage secondPage = new DealerRegistrationSecondPage(driver);
        secondPage.getEmailInput().getInput().sendKeys(faker.internet().emailAddress());
        secondPage.getFullNameInput().getInput().sendKeys(faker.name().fullName());

        secondPage.getPasswordInput().getInput().sendKeys("password1");
        secondPage.getConfirmPasswordInput().getInput().sendKeys("password1");

        // Нажимаем кнопку "Зарегистрироваться"
        secondPage.getRegistrationButton().click();

        // Я б тут что-нибудь дописал, но капча :(
    }

    @Test
    @DisplayName("Наличие сообщений о необходимости заполнить обязательные поля на первой странице регистрации")
    public void dealerRegistration_firstPageWithoutRequiredFields() throws InterruptedException {
        ChromeDriver driver = driverSetup.getDriver();

        // Нажимаем кнопку далее
        DealerRegistrationFirstPage firstPage = new DealerRegistrationFirstPage(driver);
        firstPage.getNextButton().click();

        Thread.sleep(500);

        // Проверяем наличие сообщений о необходимости заполнения обязательных полей
        assertEquals("Введите тип организации", firstPage.getOrganizationTypeSelect().getNotification().getText(), "Текст сообщения поля 'Тип организации' не соответствует ожидаемому");
        assertEquals("Введите название компании", firstPage.getCompanyNameInput().getNotification().getText(), "Текст сообщения поля 'Название компании' не соответствует ожидаемому");
        assertEquals("Введите ИНН", firstPage.getInnInput().getNotification().getText(), "Текст сообщения поля 'ИНН' не соответствует ожидаемому");
        assertEquals("Введите телефон", firstPage.getPhoneInput().getNotification().getText(), "Текст сообщения поля 'Телефон' не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Наличие сообщений о необходимости заполнить обязательные поля на второй странице регистрации")
    public void dealerRegistration_secondPageWithoutRequiredFields() throws InterruptedException {
        ChromeDriver driver = driverSetup.getDriver();

        DealerRegistrationFirstPage firstPage = new DealerRegistrationFirstPage(driver);

        // Заполняем обязательные поля на первой странице регистрации дилера
        firstPage.getOrganizationTypeSelect().selectElement("Юридическое лицо");
        firstPage.getCompanyNameInput().getInput().sendKeys(faker.company().name());

        firstPage.getInnInput().getInput().sendKeys("453");
        firstPage.getPhoneInput().getInput().sendKeys(faker.phoneNumber().cellPhone());

        // Нажимаем кнопку "Далее"
        firstPage.getNextButton().click();

        DealerRegistrationSecondPage secondPage = new DealerRegistrationSecondPage(driver);

        // Нажимаем кнопку зарегистрироваться
        secondPage.getRegistrationButton().click();

        Thread.sleep(500);

        // Проверяем наличие сообщений о необходимости заполнения обязательных полей
        assertEquals("Введите ваш email", secondPage.getEmailInput().getNotification().getText(), "Текст сообщения поля 'Email' не соответствует ожидаемому");
        assertEquals("Введите ваш ФИО", secondPage.getFullNameInput().getNotification().getText(), "Текст сообщения поля 'ФИО' не соответствует ожидаемому");
        assertEquals("Введите пароль", secondPage.getPasswordInput().getNotification().getText(), "Текст сообщения поля 'Пароль' не соответствует ожидаемому");
        assertEquals("Подтвердите пароль", secondPage.getConfirmPasswordInput().getNotification().getText(), "Текст сообщения поля 'Повторите пароль' не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Наличие сообщения о несовпадении введенных паролей")
    public void dealerRegistration_incorrectPasswordConfirm() throws InterruptedException {
        ChromeDriver driver = driverSetup.getDriver();

        DealerRegistrationFirstPage firstPage = new DealerRegistrationFirstPage(driver);

        // Заполняем обязательные поля на первой странице регистрации дилера
        firstPage.getOrganizationTypeSelect().selectElement("Юридическое лицо");
        firstPage.getCompanyNameInput().getInput().sendKeys(faker.company().name());

        firstPage.getInnInput().getInput().sendKeys("453");
        firstPage.getPhoneInput().getInput().sendKeys(faker.phoneNumber().cellPhone());

        // Нажимаем кнопку "Далее"
        firstPage.getNextButton().click();

        // В поля "Пароль" и "Подтвердите пароль" вводим разные значения
        DealerRegistrationSecondPage secondPage = new DealerRegistrationSecondPage(driver);
        secondPage.getPasswordInput().getInput().sendKeys("password");
        secondPage.getConfirmPasswordInput().getInput().sendKeys("trulyalya");

        // Нажимаем кнопку зарегистрироваться
        secondPage.getRegistrationButton().click();

        Thread.sleep(500);

        // Проверяем наличие сообщения о несовпадении паролей
        assertEquals("пароли не совпадают", secondPage.getConfirmPasswordInput().getNotification().getText(), "Текст сообщения поля 'Повторите пароль' не соответствует ожидаемому");
    }

    @AfterEach
    public void tearDown() {
        driverSetup.getDriver().quit();
    }
}
