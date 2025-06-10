import POM.MainPagePOM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

public class MainPageTest {

    private WebDriver driver;

    @BeforeEach
    public void optionsForTests(){
        ChromeOptions options =  new ChromeOptions();
        driver = new ChromeDriver(options);
        //FirefoxDriver firefoxDriverOptions = new FirefoxDriver();
        //driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); // это ожидание можно удалить.
        driver.manage().window().maximize();

        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void testClickToDropdown0(){
        MainPagePOM mainPagePOM = new MainPagePOM(driver);
        mainPagePOM.clickToButtonCookies();

        By dropdownText = By.xpath("//div[@class='accordion__panel' and @id='accordion__panel-0']");
        //Проверяем, что текст изначально скрыт (атрибут hidden)
        assertTrue(driver.findElement(dropdownText).getAttribute("hidden") != null, "Текст НЕ скрыт!");

        mainPagePOM.clickToDropdown0();
        //Проверяем, что текст теперь виден
        assertFalse(driver.findElement(dropdownText).getAttribute("hidden") != null, "Текст не появился после клика!");

        //Проверяем сам текст
        assertEquals("Сутки — 400 рублей. Оплата курьеру — наличными или картой.", driver.findElement(dropdownText).getText());
    }

    @Test
    public void testClickToDropdown4(){
        MainPagePOM mainPagePOM = new MainPagePOM(driver);
        mainPagePOM.clickToButtonCookies();

        By dropdownText = By.xpath("//div[@class='accordion__panel' and @id='accordion__panel-4']");
        //Проверяем, что текст изначально скрыт (атрибут hidden)
        assertTrue(driver.findElement(dropdownText).getAttribute("hidden") != null, "Текст НЕ скрыт!");

        mainPagePOM.clickToDropdown4();
        //Проверяем, что текст теперь виден
        assertFalse(driver.findElement(dropdownText).getAttribute("hidden") != null, "Текст не появился после клика!");

        //Проверяем сам текст
        assertEquals("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", driver.findElement(dropdownText).getText());
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }

}
