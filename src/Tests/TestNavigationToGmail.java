package Tests;

import Utils.WebEmulation.Selenium;
import Utils.WebEmulation.SeleniumTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.*;

@Tag("Gmail")
@Tag("Critical")
@DisplayName("Verify clicking Gmail icon from homepage takes us to gmail sign-in")
public class TestNavigationToGmail extends SeleniumTest {

    private void setUpTest(){
        selenium.goTo("https://www.google.ca/");
        selenium.click("//a[text()='Gmail']");
    }

    @Test
    void desktopTest(){
        selenium = new Selenium("chrome");
        setUpTest();
        assertTrue("Did not navigate to Gmail", selenium.getUrl().contains("mail.google.com"));
    }
}
