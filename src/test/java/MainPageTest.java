import POM.MainPagePOM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest {

    private WebDriver driver;

    @BeforeEach
    public void optionsForTests(){
        /*
        Я оставил строчки 24 и 25 для того чтобы проверить работу браузера GoogleChrome. Я раскомментирную строчки 24 и 25 и закомментирую строчку 26, и тем самым запущу
        тесты в GoogleChrome. Возможно есть более изящный способ провести кроссбраузерное тестирование, но я только учусь и о других способах не знаю. Если для прохождения
        спринта мне нужно будет удалить 24 и 25 строчку я это сделаю.
         */
        //ChromeOptions options =  new ChromeOptions();
        //driver = new ChromeDriver(options);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public static Object[][] data(){
        return new Object[][]{
                {"accordion__heading-0", "//div[@class='accordion__panel' and @id='accordion__panel-0']",
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-1", "//div[@class='accordion__panel' and @id='accordion__panel-1']",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"accordion__heading-2", "//div[@class='accordion__panel' and @id='accordion__panel-2']",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                         "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"accordion__heading-3", "//div[@class='accordion__panel' and @id='accordion__panel-3']",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"accordion__heading-4", "//div[@class='accordion__panel' and @id='accordion__panel-4']",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"accordion__heading-5", "//div[@class='accordion__panel' and @id='accordion__panel-5']",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"accordion__heading-6", "//div[@class='accordion__panel' and @id='accordion__panel-6']",
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"accordion__heading-7", "//div[@class='accordion__panel' and @id='accordion__panel-7']",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testClickToDropdown(String locatorDropdown, String locatorText, String actualText) throws InterruptedException {
        MainPagePOM mainPagePOM = new MainPagePOM(driver);
        mainPagePOM.clickToButtonCookies();

        By dropdownText = By.xpath(locatorText);
        //Проверяем, что текст изначально скрыт (атрибут hidden)
        assertTrue(driver.findElement(dropdownText).getAttribute("hidden") != null, "Текст НЕ скрыт!");

        mainPagePOM.clickToDropdownForParameterizedTest(locatorDropdown);
        //Проверяем, что текст теперь виден
        assertFalse(driver.findElement(dropdownText).getAttribute("hidden") != null, "Текст не появился после клика!");

        //Проверяем сам текст
        assertEquals(actualText, driver.findElement(dropdownText).getText());
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }

}
