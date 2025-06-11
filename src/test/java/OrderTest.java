import POM.MainPagePOM;
import POM.OrderPage1POM;
import POM.OrderPage2POM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest {

    private WebDriver driver;

    @BeforeEach
    public void optionsForTests(){
        //driver = new ChromeDriver();
        FirefoxDriver firefoxDriverOptions = new FirefoxDriver();
        driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // это ожидание можно удалить.
        driver.manage().window().maximize();

        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // Метод для предоставления локаторов кнопок
    private static Stream<By> provideButtonLocators() {
        return Stream.of(
                By.xpath("//button[@class='Button_Button__ra12g']"), // Локатор первой кнопки "Заказать"
                By.xpath("//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']")       // Локатор второй кнопки "Заказать"
        );
    }

    @ParameterizedTest
    @MethodSource("provideButtonLocators")
    public void getOrder(By buttonLocator) throws InterruptedException {
        MainPagePOM mainPagePOM = new MainPagePOM(driver);
        //Принимаем cookies
        mainPagePOM.clickToButtonCookies();
        // Находим кнопку по переданному локатору
        WebElement button = driver.findElement(buttonLocator);
        // Проверяем, что кнопка отображается
        assertTrue(button.isDisplayed(), "Кнопка должна быть видимой");
        // Кликаем по кнопке
        button.click();
        // Проверяем переход на нужную страницу
        assertTrue(driver.getCurrentUrl().contains("https://qa-scooter.praktikum-services.ru/order"),
                "Должен произойти переход на целевую страницу");

        OrderPage1POM order1POM = new OrderPage1POM(driver);
        // заполнение полей на первой странице заказа
        order1POM.fieldName();
        order1POM.fieldSurname();
        order1POM.fieldAdress();
        order1POM.fieldPhoneNumber();
        order1POM.fieldMetroStation();
        // нажатие на кнопку "Далее"
        order1POM.clickButton();

        OrderPage2POM orderPage2POM = new OrderPage2POM(driver);
        // заполнение полей на второй странице заказа
        orderPage2POM.fieldDate();
        orderPage2POM.fieldRent();
        orderPage2POM.fieldСolor();
        orderPage2POM.fieldComment();
        orderPage2POM.clickButtonOrder();
        orderPage2POM.clickButtonYes();
        orderPage2POM.clickButtonSeeStatus();
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }

}
