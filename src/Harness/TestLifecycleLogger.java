package Harness;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

/** Copied from JUnit 5 sample */
public interface TestLifecycleLogger {

     Logger LOG = Logger.getLogger(TestLifecycleLogger.class.getName());

    @BeforeAll
    static void beforeAllTests(TestInfo testinfo) {
        LOG.info("Starting test run");
    }

    @AfterAll
    static void afterAllTests(TestInfo testinfo) {
        LOG.info("Finished test run");
    }

    @BeforeEach
    default void beforeEachTest(TestInfo testInfo) {
        LOG.info(String.format("About to execute [%s]", testInfo.getDisplayName()));
    }

    @AfterEach
    default void afterEachTest(TestInfo testInfo) {
        LOG.info(String.format("Finished executing [%s]", testInfo.getDisplayName()));
    }

}