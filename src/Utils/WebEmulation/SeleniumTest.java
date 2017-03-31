package Utils.WebEmulation;

import Harness.TestLifecycleLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;

/** Used to set up very basic logging and tear down selenium */
public class SeleniumTest implements TestLifecycleLogger {
    protected Selenium selenium;

    @AfterEach
    void tearDown(){
        selenium.kill();
    }


    @BeforeAll
    static void beforeAllTests(TestInfo testinfo) {
        LOG.info("Starting Selenium test: " + testinfo.getTestClass());
    }
}
