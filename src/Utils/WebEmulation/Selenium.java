package Utils.WebEmulation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/** WebDriver method wrapper class */
public class Selenium extends SeleniumController {

    public Selenium(String s) {
        super(s);
    }

    private void click(By by){
        webdriver.findElement(by).click();
    }

    public void click(String xpath){
        click(By.xpath(xpath));
    }

    private void sendKeys(By by, String text){
        WebElement element = webdriver.findElement(by);
        element.sendKeys(text);
    }

    public void sendKeys(String xpath, String text){
        sendKeys(By.xpath(xpath), text);
    }



    public void goTo(String url){
        webdriver.get(url);
    }

    public String getUrl(){
        return webdriver.getCurrentUrl();
    }
}
