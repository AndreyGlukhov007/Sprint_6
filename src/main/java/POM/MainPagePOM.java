package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPagePOM {

    private WebDriver driver;
    //Кнопка "да все привыкли" для принятия cookies
    private By coociesClick = By.id("rcc-confirm-button");
    //Выпадающий список с текстом "Сколько это стоит? И как оплатить?".
    private By dropdown0 = By.id("accordion__heading-0");
    //Выпадающий список с текстом "Хочу сразу несколько самокатов! Так можно?".
    private By dropdown1 = By.id("accordion__heading-1");
    //Выпадающий список с текстом "Как рассчитывается время аренды?".
    private By dropdown2 = By.id("accordion__heading-2");
    //Выпадающий список с текстом "Можно ли заказать самокат прямо на сегодня?".
    private By dropdown3 = By.id("accordion__heading-3");
    //Выпадающий список с текстом "Можно ли продлить заказ или вернуть самокат раньше?".
    private By dropdown4 = By.id("accordion__heading-4");
    //Выпадающий список с текстом "Вы привозите зарядку вместе с самокатом?"
    private By dropdown5 = By.id("accordion__heading-5");
    //Выпадающий список с текстом "Можно ли отменить заказ?"
    private By dropdown6 = By.id("accordion__heading-6");
    //Выпадающий список с текстом "Я жизу за МКАДом, привезёте?"
    private By dropdown7 = By.id("accordion__heading-7");
    //Верхняя кнопка "Заказать" находящаяся на хедере.
    private By buttonOrderUp = By.xpath("//button[@class='Button_Button__ra12g']");
    //Нижняя кнопка "Заказать" находящаяся внизу страницы.
    private By buttonOrderDown = By.xpath("//button[@class='Button_Button__ra12g Button_UltraBig__UU3Lp']");

    public MainPagePOM(WebDriver driver){
        this.driver=driver;
    }

    //Принять cookies
    public void clickToButtonCookies(){
        driver.findElement(coociesClick).click();
    }

    //Раскрыть выпадающий список с текстом "Сколько это стоит? И как оплатить?"
    public void clickToDropdown0(){ driver.findElement(dropdown0).click(); }

    //Можно ли продлить заказ или вернуть самокат раньше?"
    public void clickToDropdown4(){
        driver.findElement(dropdown4).click();
    }

    public void clickToDropdownForParameterizedTest(String locator){
        driver.findElement((By.id(locator))).click();
    }

    public void clickToButtonOrderUp(){
        driver.findElement(buttonOrderUp).click();
    }

    public void clickToButtonOrderDown(){
        driver.findElement(buttonOrderDown).click();
    }
}
