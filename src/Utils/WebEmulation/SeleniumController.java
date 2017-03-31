package Utils.WebEmulation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumController {
    private String device;
    WebDriver webdriver;

    // todo find better way to init drivers rather than strings
    SeleniumController(String s) {
        device = s.toLowerCase();
        switch (device) {
            case "chrome": default:
                System.setProperty("webdriver.chrome.driver", "c:\\automation\\chromedriver.exe");
                webdriver = new ChromeDriver();
                break;
        }
    }

    public String getDevice(){
        return device;
    }

    void kill(){
        webdriver.close();
    }
}
