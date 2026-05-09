package tests;

/*
==========================================================================
FILE : AdvancedTestNGFeaturesTest.java
PACKAGE : tests

PURPOSE
==========================================================================
This file covers ADVANCED TestNG features that are NOT in GoogleTest.java.
Study GoogleTest.java first — it covers the fundamentals.
This file picks up where that one left off.

WHAT THIS FILE COVERS
==========================================================================
1.  invocationCount         : run the same test N times automatically
2.  successPercentage       : how many of those N runs must pass
3.  threadPoolSize          : run N invocations in parallel threads
4.  Reporter.log            : write custom messages to the HTML report
5.  Assert.fail             : explicitly force a test to fail
6.  alwaysRun on @Test      : run even if a dependency test failed
7.  dependsOnGroups         : depend on an entire GROUP not just one method
8.  ignoreMissingDependencies: do not fail if a dependency does not exist
9.  presenceOfElementLocated : wait for DOM presence (not just visibility)
10. urlContains             : wait condition for URL changes
11. textToBePresentInElement: wait for specific text inside an element
12. @DataProvider(parallel) : run DataProvider iterations in parallel
13. Multiple @DataProvider  : one class can have many data providers
14. @Parameters + @Optional : inject values from testng.xml with a default
15. retryAnalyzer           : auto-retry failed tests (flaky test handling)
16. RetryAnalyzer inner class: implement IRetryAnalyzer inside the test file
17. AtomicInteger           : thread-safe counter for parallel-safe state
==========================================================================
*/


/*
==========================================================================
IMPORTS
==========================================================================
*/

// AtomicInteger is a thread-safe integer.
// When tests run in parallel, a normal int variable is not safe because
// two threads can read and write it at the same time, causing wrong values.
// AtomicInteger guarantees that read-then-increment is one atomic operation.
import java.util.concurrent.atomic.AtomicInteger;

// Duration represents a unit of time (seconds, milliseconds).
import java.time.Duration;

// Objects utility class for null-safe operations.
import java.util.Objects;

// Method is a reflection class that holds metadata about a Java method.
// TestNG injects it into @BeforeMethod so you can read the test name.
import java.lang.reflect.Method;

// Selenium locator strategy.
import org.openqa.selenium.By;

// Keyboard key constants.
import org.openqa.selenium.Keys;

// Browser control interface.
import org.openqa.selenium.WebDriver;

// Represents one HTML element on the page.
import org.openqa.selenium.WebElement;

// Chrome browser implementation.
import org.openqa.selenium.chrome.ChromeDriver;

// Chrome startup configuration.
import org.openqa.selenium.chrome.ChromeOptions;

// Smart wait mechanism.
import org.openqa.selenium.support.ui.WebDriverWait;

// Library of pre-built conditions to wait for.
import org.openqa.selenium.support.ui.ExpectedConditions;

// Hard assertion library — stops test immediately on failure.
import org.testng.Assert;

// Interface to implement for custom retry logic.
// Your RetryAnalyzer class must implement this interface.
import org.testng.IRetryAnalyzer;

// Holds the result of a completed test (name, status, exception, time).
import org.testng.ITestResult;

// Utility that logs messages directly into the TestNG HTML report.
import org.testng.Reporter;

// Throw this to mark a test as intentionally SKIPPED.
import org.testng.SkipException;

// Lifecycle and feature annotations.
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

// @Optional provides a default value for a @Parameters parameter.
// If testng.xml does not define the parameter, the @Optional value is used.
// Without @Optional, a missing parameter causes a TestNGException at runtime.
import org.testng.annotations.Optional;

// @Parameters injects values defined in testng.xml into your test method.
import org.testng.annotations.Parameters;

// @Test marks a method as a test case.
import org.testng.annotations.Test;


/**
 * AdvancedTestNGFeaturesTest — Advanced TestNG Feature Reference
 * <p>
 * Covers advanced-level TestNG features using Google.com as the test target.
 * Every feature has a dedicated section with a working code example and
 * a detailed comment explaining what it does and when to use it.
 * <p>
 * Prerequisites: study GoogleTest.java before reading this file.
 * That file covers: lifecycle annotations, all Assert types, SoftAssert,
 * DataProvider basics, dependsOnMethods, SkipException, and ITestResult.
 */
public class AdvancedTestNGFeaturesTest {


    /*
    ======================================================================
    FIELDS AND CONSTANTS
    ======================================================================
    */

    // Shared browser session. Created once in @BeforeClass.
    private WebDriver driver;

    // Smart wait object. Created once in @BeforeClass. Reused by all tests.
    private WebDriverWait wait;

    // Application URL and wait timeout as constants.
    private static final String BASE_URL        = "https://www.google.com";
    private static final int    TIMEOUT_SECONDS = 10;

    /*
    ABOUT ThreadLocal<WebDriver> — for truly parallel tests:
    =========================================================
    When tests run in parallel (threadPoolSize or parallel="methods"),
    multiple threads share this class's fields simultaneously.
    A single "driver" field shared across threads causes race conditions —
    Thread A might close the browser that Thread B is still using.

    The professional solution is ThreadLocal<WebDriver>:
        private static ThreadLocal<WebDriver> driverHolder = new ThreadLocal<>();

    ThreadLocal gives each thread its OWN separate copy of the driver.
    Thread A's driver and Thread B's driver are completely independent.

    This file uses a single driver (non-parallel) to keep things simple.
    In a real parallel project, always use ThreadLocal<WebDriver>.
    */

    /*
    ABOUT AtomicInteger — for thread-safe counters:
    ================================================
    Used in the retry analyzer demo below.
    A static int retryCount would be shared across threads without safety.
    AtomicInteger guarantees that incrementAndGet() is an atomic operation —
    no two threads can corrupt each other's counter update.
    */
    private static final AtomicInteger retryAttemptCount = new AtomicInteger(0);


    /*
    ======================================================================
    SETUP AND TEARDOWN
    (Refer to GoogleTest.java for full explanations of each annotation)
    ======================================================================
    */

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        // options.addArguments("--headless=new"); // uncomment for CI/CD

        // Selenium Manager (built into Selenium 4.6+) downloads chromedriver automatically.
        driver = new ChromeDriver(options);
        wait   = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        driver.get(BASE_URL);
    }

    @AfterClass
    public void tearDown() {
        // quit() closes all tabs and kills the chromedriver process.
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeEachTest(Method method) {
        System.out.println("\nSTARTING : " + method.getName());
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.titleContains("Google"));
    }

    @AfterMethod
    public void afterEachTest(ITestResult result) {
        String name     = result.getName();
        long   duration = result.getEndMillis() - result.getStartMillis();

        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("PASSED  : " + name + " [" + duration + "ms]");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("FAILED  : " + name);
        } else {
            System.out.println("SKIPPED : " + name);
        }
    }


    /*
    ======================================================================
    FEATURE 1 : invocationCount
    ======================================================================
    invocationCount tells TestNG to run this @Test method exactly N times.
    TestNG treats each invocation as a separate test in the HTML report.

    WHEN TO USE:
    - Stability testing: run the same test 10 times to confirm it never fails
    - Load simulation: verify a button can be clicked 50 times without error
    - Repeated data entry: submit the same form 5 times and check consistency

    IMPORTANT: each invocation starts fresh because @BeforeMethod runs before
    every invocation. So the browser resets to BASE_URL 3 times here.
    ======================================================================
    */

    /**
     * FEATURE 1 — invocationCount
     * <p>
     * This method runs 3 times automatically.
     * TestNG creates 3 separate rows in the HTML report — one per invocation.
     * If any invocation fails, that specific one is marked FAILED in the report.
     */
    @Test(
            priority      = 1,
            groups        = { "advanced" },
            description   = "Demonstrates invocationCount: runs the same test 3 times",
            invocationCount = 3
    )
    public void invocationCountDemoTest() {

        // This assertion runs 3 separate times.
        // The title check must pass all 3 times for the test to fully pass.
        String title = Objects.requireNonNullElse(driver.getTitle(), "");

        Assert.assertTrue(
                title.contains("Google"),
                "Page title failed on one of the invocations, was: " + title
        );

        System.out.println("Invocation completed — title: " + title);
    }


    /*
    ======================================================================
    FEATURE 2 : invocationCount + successPercentage
    ======================================================================
    successPercentage defines what percentage of all invocations must PASS
    for the overall test to be considered PASSED.

    Example: invocationCount=10, successPercentage=80
    Means: at least 8 out of 10 invocations must pass.
    If 9 pass and 1 fails, the overall test is still PASSED.
    If only 7 pass (70%), the overall test is FAILED.

    WHEN TO USE:
    - Testing flaky external services where occasional failures are acceptable
    - Network-dependent tests in unstable environments
    - Performance tests that may occasionally hit slow response times

    DEFAULT: if you omit successPercentage, it is 100 — ALL invocations must pass.
    ======================================================================
    */

    /**
     * FEATURE 2 — invocationCount + successPercentage
     * <p>
     * Runs 5 times. At least 80% (4 out of 5) must pass.
     * If exactly 4 pass and 1 fails, the overall test result is PASSED.
     */
    @Test(
            priority          = 2,
            groups            = { "advanced" },
            description       = "Demonstrates successPercentage: 80% of 5 runs must pass",
            invocationCount   = 5,
            successPercentage = 80
    )
    public void successPercentageDemoTest() {

        // In a real scenario, this might call an external API that occasionally
        // returns a timeout. 80% success rate is acceptable for that service.
        String url = Objects.requireNonNullElse(driver.getCurrentUrl(), "");

        Assert.assertTrue(
                url.contains("google"),
                "URL should contain 'google' but was: " + url
        );
    }


    /*
    ======================================================================
    FEATURE 3 : invocationCount + threadPoolSize
    ======================================================================
    threadPoolSize works TOGETHER with invocationCount.
    It specifies how many invocations run SIMULTANEOUSLY in parallel threads.

    Example: invocationCount=6, threadPoolSize=3
    Means: run 6 total invocations, with 3 running at the same time.
    Invocations 1+2+3 start together, then invocations 4+5+6 start together.

    WITHOUT threadPoolSize: invocations run one at a time (sequential).
    WITH threadPoolSize: invocations run N at a time (parallel).

    WARNING: This test does NOT use the browser because the shared driver
    field is not thread-safe for parallel access. In production, use
    ThreadLocal<WebDriver> when combining threadPoolSize with browser tests.
    ======================================================================
    */

    /**
     * FEATURE 3 — threadPoolSize
     * <p>
     * Runs 6 invocations with 3 running in parallel at a time.
     * This test uses only thread info (no browser) to avoid shared-driver conflicts.
     */
    @Test(
            priority        = 3,
            groups          = { "advanced" },
            description     = "Demonstrates threadPoolSize: 6 invocations, 3 in parallel",
            invocationCount = 6,
            threadPoolSize  = 3
    )
    public void threadPoolSizeDemoTest() {

        // Thread.currentThread().getName() shows which thread this invocation runs on.
        // With threadPoolSize=3, you will see 3 different thread names in the output.
        String threadName = Thread.currentThread().getName();

        System.out.println("Running on thread: " + threadName);

        // Simple assertion that does not require the browser.
        // The test just confirms the thread system is working.
        Assert.assertNotNull(
                threadName,
                "Thread name should not be null"
        );
    }


    /*
    ======================================================================
    FEATURE 4 : Reporter.log
    ======================================================================
    Reporter.log() writes custom messages that appear INSIDE the TestNG HTML
    report, directly underneath the test that called it.

    This is different from System.out.println():
    System.out.println() : only appears in the terminal console
    Reporter.log()       : appears in the terminal AND in the HTML report

    FORMAT:
    Reporter.log("message")           : writes to HTML report only
    Reporter.log("message", true)     : writes to HTML report AND console

    WHEN TO USE:
    - Log the URL being tested, the search term used, or the element found
    - Log step-by-step progress for a long multi-step test
    - Log environment details so the report is self-documenting
    - Log values that help diagnose failures without re-running the test
    ======================================================================
    */

    /**
     * FEATURE 4 — Reporter.log
     * <p>
     * Logs custom messages into the TestNG HTML report.
     * Open emailable-report.html after running to see these messages.
     */
    @Test(
            priority    = 4,
            groups      = { "advanced" },
            description = "Demonstrates Reporter.log: custom messages appear in HTML report"
    )
    public void reporterLogDemoTest() {

        // Log environment details at the start of the test.
        // These messages appear under this test in the HTML report.
        Reporter.log("Test started on URL: " + BASE_URL, true);
        Reporter.log("Browser: Chrome (managed by Selenium Manager)", true);

        String title = Objects.requireNonNullElse(driver.getTitle(), "");

        // Log the actual value being tested before asserting.
        // This is extremely useful for debugging failures without re-running.
        Reporter.log("Page title retrieved: " + title, true);

        Assert.assertTrue(
                title.contains("Google"),
                "Title should contain 'Google'"
        );

        // Log the result at the end of the test.
        Reporter.log("Title verification PASSED", true);

        // Log a multi-step test progress example.
        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("q"))
        );

        Reporter.log("Step 1 PASSED: search box found and clickable", true);

        Reporter.log("Step 2: search box tag is: " + searchBox.getTagName(), true);

        Assert.assertTrue(
                searchBox.isDisplayed(),
                "Search box should be displayed"
        );

        Reporter.log("Step 2 PASSED: search box is displayed", true);
    }


    /*
    ======================================================================
    FEATURE 5 : Assert.fail
    ======================================================================
    Assert.fail() explicitly and immediately fails a test with a message.
    It is the programmatic equivalent of "this should never happen."

    WHEN TO USE:
    - Inside an if/else block when an unexpected branch is reached
    - When you detect an invalid state that your other assertions cannot catch
    - To mark a test as failing when a specific condition is met at runtime
    - As a placeholder for test logic that is not yet written

    Assert.fail() throws AssertionError immediately — no more lines run after it.
    ======================================================================
    */

    /**
     * FEATURE 5 — Assert.fail
     * <p>
     * Demonstrates using Assert.fail() when a condition at runtime
     * indicates the test should fail, even without a boolean assertion.
     */
    @Test(
            priority    = 5,
            groups      = { "advanced" },
            description = "Demonstrates Assert.fail: explicitly failing a test based on runtime condition"
    )
    public void assertFailDemoTest() {

        String title = Objects.requireNonNullElse(driver.getTitle(), "");

        /*
        Scenario: you want to detect multiple possible error states and fail
        with a specific message for each one, rather than using one assertTrue.

        if/else chains with Assert.fail() give you very precise failure messages
        that tell the reader exactly which condition triggered the failure.
        */
        if (title.isEmpty()) {
            // Assert.fail() throws AssertionError immediately.
            // The message appears in the failure report and the console.
            Assert.fail(
                    "Page title is empty — page did not load or returned an error"
            );
        } else if (!title.contains("Google")) {
            Assert.fail(
                    "Wrong page loaded — expected Google but title was: " + title
            );
        }

        // If neither condition triggered, the test continues normally here.
        System.out.println("Title check passed: " + title);

        Assert.assertTrue(
                title.contains("Google"),
                "Final title assertion — should contain 'Google'"
        );
    }


    /*
    ======================================================================
    FEATURE 6 : alwaysRun = true on @Test
    ======================================================================
    @Test(alwaysRun = true) means this test runs even if the tests or groups
    it depends on have FAILED or been SKIPPED.

    WITHOUT alwaysRun:
    If testA (which testB depends on) fails, testB is automatically SKIPPED.
    This is the default safe behavior.

    WITH alwaysRun = true:
    testB runs regardless of whether testA passed, failed, or was skipped.

    WHEN TO USE:
    - Cleanup tests that must run to restore state, even after a failure
    - Teardown-style @Test methods (though @AfterMethod is usually better)
    - Reporting tests that must always run to complete the report
    ======================================================================
    */

    /**
     * FEATURE 6 — alwaysRun on @Test
     * <p>
     * This test belongs to the "cleanup" group and runs regardless of
     * whether the tests it depends on passed or failed.
     */
    @Test(
            priority    = 6,
            groups      = { "cleanup", "advanced" },
            description = "Demonstrates alwaysRun: this test runs even if dependencies failed",
            alwaysRun   = true
    )
    public void alwaysRunDemoTest() {

        /*
        This test is designed to represent a "cleanup" step.
        Even if the smoke tests above failed, this cleanup still runs
        because alwaysRun = true overrides the dependency-skip behavior.
        */

        // A cleanup step might navigate back to home, clear cookies,
        // or reset application state. Here we just verify the browser is alive.
        String currentUrl = Objects.requireNonNullElse(driver.getCurrentUrl(), "");

        Assert.assertNotNull(
                currentUrl,
                "Browser session should still be active for cleanup"
        );

        System.out.println("Cleanup step executed on: " + currentUrl);
    }


    /*
    ======================================================================
    FEATURE 7 : dependsOnGroups
    ======================================================================
    dependsOnGroups is like dependsOnMethods, but instead of depending on a
    single method, this test depends on an ENTIRE GROUP of tests.

    If ALL tests in the specified group have passed, this test runs.
    If ANY test in the specified group has failed, this test is SKIPPED.

    WHEN TO USE:
    - When a downstream test requires multiple upstream tests to all pass
    - Modeling workflows: "only run payment if BOTH login AND cart pass"
    - Cleaner than listing 5 individual dependsOnMethods

    DIFFERENCE:
    dependsOnMethods = { "loginTest" }         : depends on one specific method
    dependsOnGroups  = { "smoke" }             : depends on ALL methods in "smoke"
    ======================================================================
    */

    /**
     * FEATURE 7 — dependsOnGroups
     * <p>
     * This test only runs if ALL tests in the "smoke" group have passed.
     * If any smoke test failed, this test is automatically SKIPPED.
     */
    @Test(
            priority         = 7,
            groups           = { "advanced" },
            description      = "Demonstrates dependsOnGroups: runs only if all smoke tests passed",
            dependsOnGroups  = { "smoke" }
    )
    public void dependsOnGroupsDemoTest() {

        /*
        If you run only the "advanced" group without running "smoke" tests first,
        this test would normally be SKIPPED because its dependency is missing.
        You would handle that with ignoreMissingDependencies (see Feature 8).

        Here we run the "smoke" group in the same suite, so this test will run.
        */
        String title = Objects.requireNonNullElse(driver.getTitle(), "");

        Assert.assertTrue(
                title.contains("Google"),
                "dependsOnGroups demo: title should contain 'Google', was: " + title
        );

        System.out.println("dependsOnGroups: smoke group passed, this test is now running");
    }


    /*
    ======================================================================
    FEATURE 8 : ignoreMissingDependencies
    ======================================================================
    By default, if the method or group that a test depends on does NOT EXIST
    in the current test run, TestNG throws an error and the test FAILS.

    ignoreMissingDependencies = true tells TestNG:
    "If the dependency does not exist in this run, just ignore it and run me."

    WHEN TO USE:
    - When you run a subset of tests (e.g. only "advanced" group) but some
      tests in that subset depend on methods from other groups not being run
    - In CI/CD pipelines where different jobs run different test groups
    - When a test is reused across multiple suites with different dependencies
    ======================================================================
    */

    /**
     * FEATURE 8 — ignoreMissingDependencies
     * <p>
     * This test declares a dependency on a method that does not exist in
     * this class. Normally that would cause a TestNGException.
     * With ignoreMissingDependencies=true, TestNG simply ignores the missing
     * dependency and runs this test normally.
     */
    @Test(
            priority                  = 8,
            groups                    = { "advanced" },
            description               = "Demonstrates ignoreMissingDependencies: runs even if dependency is absent",
            dependsOnMethods          = { "nonExistentLoginTest" },
            ignoreMissingDependencies = true
    )
    public void ignoreMissingDependenciesDemoTest() {

        /*
        "nonExistentLoginTest" does not exist in this class.
        Without ignoreMissingDependencies = true, TestNG would throw:
        "org.testng.TestNGException: Method nonExistentLoginTest not found"

        With ignoreMissingDependencies = true, TestNG says:
        "The dependency does not exist — I will ignore it and run this test."
        */

        String url = Objects.requireNonNullElse(driver.getCurrentUrl(), "");

        Assert.assertTrue(
                url.contains("google"),
                "URL should contain 'google' but was: " + url
        );

        System.out.println("ignoreMissingDependencies: ran successfully despite missing dependency");
    }


    /*
    ======================================================================
    FEATURE 9 : presenceOfElementLocated vs visibilityOfElementLocated
    ======================================================================
    Two commonly confused wait conditions:

    presenceOfElementLocated(By)
    - Waits until the element EXISTS in the HTML DOM
    - The element may be INVISIBLE (hidden by CSS: display:none, visibility:hidden)
    - Use when: you only need to know the element is in the page structure,
      not necessarily visible to the user

    visibilityOfElementLocated(By)
    - Waits until the element EXISTS in DOM AND is VISIBLE to the user
    - The element must have non-zero dimensions and not be hidden
    - Use when: you need to interact with or verify a visible element

    RULE OF THUMB:
    - For assertions about what the user can see: use visibilityOfElementLocated
    - For checking that an error message element exists (may be hidden): use presenceOfElementLocated
    - Before clicking or typing: use elementToBeClickable (strongest condition)
    ======================================================================
    */

    /**
     * FEATURE 9 — presenceOfElementLocated vs visibilityOfElementLocated
     * <p>
     * Demonstrates both wait conditions and explains when to use each.
     */
    @Test(
            priority    = 9,
            groups      = { "advanced" },
            description = "Demonstrates presenceOfElementLocated vs visibilityOfElementLocated"
    )
    public void waitConditionsDemoTest() {

        /*
        presenceOfElementLocated:
        - Waits until the element appears in the DOM structure
        - Does NOT check if it is visible to the user
        - Returns a WebElement reference once it is in the DOM
        */
        WebElement searchBoxPresence = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("q"))
        );

        Assert.assertNotNull(
                searchBoxPresence,
                "Search box should be present in the DOM"
        );

        Reporter.log("presenceOfElementLocated: search box found in DOM", true);

        /*
        visibilityOfElementLocated:
        - Waits until the element is in the DOM AND is visible to the user
        - Checks that dimensions are non-zero and CSS does not hide it
        - Stronger condition — use this for elements users need to see
        */
        WebElement searchBoxVisible = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("q"))
        );

        Assert.assertTrue(
                searchBoxVisible.isDisplayed(),
                "Search box should be visible to the user"
        );

        Reporter.log("visibilityOfElementLocated: search box confirmed visible", true);
    }


    /*
    ======================================================================
    FEATURE 10 : urlContains and textToBePresentInElement
    ======================================================================
    Two more ExpectedConditions used frequently in real projects:

    urlContains(String fragment)
    - Waits until the browser's current URL contains the given text
    - Useful after clicking a link or submitting a form that navigates
    - More reliable than titleContains when the title does not change

    textToBePresentInElement(WebElement, String)
    - Waits until a specific element contains the given text
    - Useful for dynamic elements that update their text after an action
    - Example: a status message that changes from "Loading..." to "Done"
    ======================================================================
    */

    /**
     * FEATURE 10 — urlContains and textToBePresentInElement
     * <p>
     * Demonstrates advanced wait conditions for URL and element text changes.
     */
    @Test(
            priority    = 10,
            groups      = { "advanced" },
            description = "Demonstrates urlContains and textToBePresentInElement wait conditions"
    )
    public void advancedWaitConditionsDemoTest() {

        // Perform a search to trigger a URL change.
        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("q"))
        );
        searchBox.sendKeys("Advanced TestNG 2026");
        searchBox.sendKeys(Keys.ENTER);

        /*
        urlContains("search"):
        After submitting the search, Google redirects to:
        https://www.google.com/search?q=...

        We wait for the URL to contain "search" as a reliable signal that
        the results page has loaded and the URL has updated.
        This is more stable than titleContains in some edge cases.
        */
        wait.until(ExpectedConditions.urlContains("search"));

        String currentUrl = Objects.requireNonNullElse(driver.getCurrentUrl(), "");

        Reporter.log("URL after search: " + currentUrl, true);

        Assert.assertTrue(
                currentUrl.contains("google.com/search"),
                "URL should contain 'google.com/search' but was: " + currentUrl
        );

        /*
        textToBePresentInElementLocated(By, String):
        Waits until the element found by the locator contains the specified text.
        Useful for dynamic elements that update their content after an action.

        Here we verify the page title element contains our search term.
        Note: textToBePresentInElementLocated looks at the element's TEXT CONTENT,
        not its attribute values.
        */
        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.tagName("title"),
                        "Advanced"
                )
        );

        Reporter.log("textToBePresentInElementLocated: title contains 'Advanced'", true);
    }


    /*
    ======================================================================
    FEATURE 11 : @DataProvider(parallel = true)
    ======================================================================
    A normal @DataProvider runs its data rows sequentially — one at a time.
    Adding parallel = true makes the rows run in PARALLEL across threads.

    Example: DataProvider returns 4 rows.
    parallel = false : rows run one after another (sequential, slower)
    parallel = true  : all 4 rows start at the same time (parallel, faster)

    IMPORTANT WARNING:
    The same shared "driver" object is not thread-safe for parallel DataProvider.
    In production, use ThreadLocal<WebDriver> when using parallel DataProvider.
    This demo uses simple assertions without browser interaction for safety.

    WHEN TO USE:
    - When each data row represents a completely independent test scenario
    - When you want to speed up data-driven tests significantly
    - Combined with ThreadLocal driver management in a real framework
    ======================================================================
    */

    /**
     * FEATURE 11 — @DataProvider(parallel = true)
     * <p>
     * Each row from the data provider runs in its own thread simultaneously.
     * This demo avoids browser interaction to prevent shared-driver conflicts.
     *
     * @param keyword     the search keyword for this data row
     * @param minLength   the minimum expected length of the keyword
     */
    @Test(
            priority     = 11,
            groups       = { "advanced" },
            description  = "Demonstrates parallel DataProvider execution",
            dataProvider = "parallelKeywordsProvider"
    )
    public void parallelDataProviderTest(String keyword, int minLength) {

        String threadName = Thread.currentThread().getName();

        System.out.println(
                "Thread: " + threadName + " | Keyword: " + keyword
        );

        // Verify the keyword is not null.
        Assert.assertNotNull(
                keyword,
                "Keyword should not be null"
        );

        // Verify the keyword meets the minimum length requirement.
        Assert.assertTrue(
                keyword.length() >= minLength,
                "Keyword '" + keyword + "' should have at least " + minLength + " characters"
        );

        Reporter.log(
                "Parallel row completed — keyword: " + keyword + " on thread: " + threadName,
                true
        );
    }


    /*
    ======================================================================
    FEATURE 12 : Multiple @DataProvider methods in one class
    ======================================================================
    A single class can have as many @DataProvider methods as you need.
    Each @DataProvider must have a UNIQUE name.
    Each @Test method references one @DataProvider by its name string.

    This is how real projects organize test data:
    - searchTermsProvider    : data for search tests
    - loginCredentials       : data for login tests
    - productSkus            : data for product page tests
    - browserConfigurations  : data for cross-browser tests
    ======================================================================
    */

    /**
     * FEATURE 12 — Multiple @DataProvider methods
     * <p>
     * Uses a second data provider that supplies browser-like config strings.
     * Demonstrates that one class can have many independently named providers.
     *
     * @param environment  the environment name for this row
     * @param expectedHost the host the URL should contain for this environment
     */
    @Test(
            priority     = 12,
            groups       = { "advanced" },
            description  = "Demonstrates multiple DataProviders in one class",
            dataProvider = "environmentProvider"
    )
    public void multipleDataProvidersDemoTest(String environment, String expectedHost) {

        System.out.println("Testing environment: " + environment);

        // In a real framework, you would navigate to the environment URL here.
        // For this demo we verify the data was injected correctly.
        Assert.assertNotNull(
                environment,
                "Environment name should not be null"
        );

        Assert.assertNotNull(
                expectedHost,
                "Expected host should not be null"
        );

        Reporter.log(
                "Environment row: " + environment + " expects host: " + expectedHost,
                true
        );
    }


    /*
    ======================================================================
    FEATURE 13 : @Parameters and @Optional
    ======================================================================
    @Parameters injects values from the <parameter> tags in testng.xml
    directly into your test method as Java parameters.

    In testng.xml you define:
        <parameter name="browser" value="chrome"/>

    In your Java test you read it with:
        @Parameters({"browser"})
        public void myTest(String browser) { ... }

    @Optional("defaultValue")
    If the parameter is NOT defined in testng.xml, TestNG throws:
    "Parameter 'browser' is required by @Parameters but not defined."

    Adding @Optional("chrome") provides a fallback default value.
    This makes the test runnable even without testng.xml parameters —
    it will use "chrome" as the default.

    WHEN TO USE @Parameters:
    - Cross-browser testing: pass "chrome", "firefox", "edge" from XML
    - Environment switching: pass "staging", "production", "local"
    - Test data: pass simple string values from testng.xml to tests
    ======================================================================
    */

    /**
     * FEATURE 13 — @Parameters with @Optional
     * <p>
     * Reads a "browser" parameter from testng.xml.
     * If testng.xml does not define the parameter, falls back to "chrome".
     * <p>
     * To supply the parameter from testng.xml, add inside the suite tag:
     * {@code <parameter name="browser" value="firefox"/>}
     *
     * @param browser the browser name injected from testng.xml (default: "chrome")
     */
    @Test(
            priority    = 13,
            groups      = { "advanced" },
            description = "Demonstrates @Parameters with @Optional fallback"
    )
    @Parameters({ "browser" })
    public void parametersDemoTest(@Optional("chrome") String browser) {

        /*
        At runtime, "browser" will contain:
        - The value from testng.xml <parameter name="browser" value="..."/>
          if that parameter is defined in testng.xml.
        - "chrome" (the @Optional default) if the parameter is NOT defined.

        In a real cross-browser framework you would use this value to decide
        which WebDriver to instantiate:
            if (browser.equals("firefox")) { driver = new FirefoxDriver(); }
            if (browser.equals("edge"))    { driver = new EdgeDriver();    }
        */

        System.out.println("Parameter received — browser: " + browser);

        Reporter.log("Browser parameter value: " + browser, true);

        // Validate the parameter received is one of the valid browsers.
        boolean isValidBrowser = browser.equals("chrome")
                || browser.equals("firefox")
                || browser.equals("edge")
                || browser.equals("safari");

        Assert.assertTrue(
                isValidBrowser,
                "Browser parameter should be one of: chrome, firefox, edge, safari. Got: " + browser
        );
    }


    /*
    ======================================================================
    FEATURE 14 : retryAnalyzer
    ======================================================================
    retryAnalyzer automatically re-runs a failed test a set number of times
    before marking it as truly FAILED in the report.

    This solves the "flaky test" problem.
    A flaky test is one that sometimes passes and sometimes fails for reasons
    unrelated to actual bugs:
    - Slow network causing a timeout
    - Browser taking longer than usual to render an element
    - An external API that occasionally returns a 500 error

    How retryAnalyzer works:
    1. Test fails on attempt 1
    2. TestNG calls retryAnalyzer.retry(result)
    3. If retry() returns TRUE, TestNG re-runs the test
    4. If retry() returns FALSE, TestNG marks the test as FAILED
    5. If any retry attempt PASSES, the test is marked as PASSED

    To use retryAnalyzer:
    1. Create a class that implements IRetryAnalyzer (see inner class below)
    2. Reference it in the @Test annotation:
       retryAnalyzer = YourClass.YourRetryAnalyzer.class

    The RetryAnalyzer inner class is defined at the bottom of this file.
    ======================================================================
    */

    /**
     * FEATURE 14 — retryAnalyzer
     * <p>
     * This test fails on attempt 1 and passes on attempt 2.
     * The GoogleRetryAnalyzer inner class (defined below) allows up to
     * 2 retries, so the test eventually passes and is reported as PASSED.
     * <p>
     * The AtomicInteger counter increments on each attempt in a thread-safe way.
     */
    @Test(
            priority       = 14,
            groups         = { "advanced" },
            description    = "Demonstrates retryAnalyzer: auto-retries a failed test",
            retryAnalyzer  = AdvancedTestNGFeaturesTest.GoogleRetryAnalyzer.class
    )
    public void retryAnalyzerDemoTest() {

        /*
        retryAttemptCount is an AtomicInteger shared across all invocations.
        incrementAndGet() adds 1 and returns the NEW value atomically.
        This is thread-safe — no race condition even if run in parallel.

        Attempt 1: count becomes 1 → fails intentionally
        Attempt 2: count becomes 2 → passes

        GoogleRetryAnalyzer.retry() returns true on attempt 1 (triggering retry).
        On attempt 2 it passes, so retry() is never called again.
        */
        int attempt = retryAttemptCount.incrementAndGet();

        System.out.println("RetryAnalyzer demo — attempt number: " + attempt);
        Reporter.log("RetryAnalyzer demo — attempt: " + attempt, true);

        if (attempt == 1) {
            /*
            Simulate a failure on the first attempt.
            In a real scenario this would be a genuine intermittent failure
            such as an element not found due to a slow page load.
            */
            Assert.fail(
                    "Simulated failure on attempt 1 — RetryAnalyzer will retry this test"
            );
        }

        // Attempt 2 reaches here and passes.
        Assert.assertTrue(
                true,
                "Test passed on attempt: " + attempt
        );

        Reporter.log("RetryAnalyzer demo PASSED on attempt: " + attempt, true);
    }


    /*
    ======================================================================
    DATA PROVIDER METHODS
    (Placed at the bottom by convention — they are supplier methods, not tests)
    ======================================================================
    */

    /**
     * Supplies keyword and minimum-length pairs for {@code parallelDataProviderTest}.
     * <p>
     * parallel = true makes each row run in its own thread simultaneously.
     * This speeds up data-driven test execution significantly.
     *
     * @return 2D array where each row is one parallel test invocation
     */
    @DataProvider(name = "parallelKeywordsProvider", parallel = true)
    public Object[][] parallelKeywordsProvider() {
        return new Object[][] {
                // { keyword,       minLength }
                { "Selenium",       5         },
                { "TestNG",         5         },
                { "Java",           4         },
                { "Automation",     8         }
        };
        /*
        With parallel = true, all 4 rows start simultaneously on 4 threads.
        Without parallel = true, they run sequentially: Selenium, TestNG, Java, Automation.
        */
    }

    /**
     * Supplies environment name and expected host pairs.
     * <p>
     * Demonstrates that one class can have multiple @DataProvider methods.
     * Each must have a unique name string.
     *
     * @return 2D array where each row represents one environment to test
     */
    @DataProvider(name = "environmentProvider")
    public Object[][] environmentProvider() {
        return new Object[][] {
                // { environment,   expectedHost     }
                { "Production",    "google.com"      },
                { "Staging",       "google.com"      },
                { "Development",   "google.com"      }
        };
    }


    /*
    ======================================================================
    INNER CLASS : GoogleRetryAnalyzer
    ======================================================================
    An inner class is a class defined INSIDE another class.
    Here we define the RetryAnalyzer inside AdvancedTestNGFeaturesTest so
    everything stays in one file — convenient for learning and small projects.

    In a real project, RetryAnalyzer would be in its own file:
        src/test/java/framework/retry/GoogleRetryAnalyzer.java

    WHY "public static"?
    - public  : TestNG needs to access it from outside this class via reflection
    - static  : TestNG instantiates it without needing an outer class instance

    HOW IRetryAnalyzer WORKS:
    TestNG calls retry(ITestResult result) after every failed @Test.
    - Return true  : TestNG re-runs the test (retry is triggered)
    - Return false : TestNG marks the test as FAILED (no more retries)

    The retry count is tracked as an INSTANCE variable (not static).
    This means each test method gets its OWN RetryAnalyzer instance,
    so the retry count for testA does not affect testB.
    ======================================================================
    */

    /**
     * Retry analyzer implementation — allows up to 2 retry attempts.
     * <p>
     * Reference this class in any {@code @Test} annotation:
     * {@code retryAnalyzer = AdvancedTestNGFeaturesTest.GoogleRetryAnalyzer.class}
     */
    public static class GoogleRetryAnalyzer implements IRetryAnalyzer {

        // Current retry attempt number for this specific test instance.
        private int currentRetryCount = 0;

        // Maximum number of retries allowed before marking as FAILED.
        // Set to 2: if a test fails, TestNG will retry it up to 2 more times.
        private static final int MAX_RETRY_COUNT = 2;

        /**
         * Called by TestNG after each test failure.
         * <p>
         * Returns true to retry, false to stop retrying.
         *
         * @param result the ITestResult of the failed test
         * @return true if the test should be retried, false if it should fail
         */
        @Override
        public boolean retry(ITestResult result) {

            if (currentRetryCount < MAX_RETRY_COUNT) {
                currentRetryCount++;

                System.out.println(
                        "RetryAnalyzer: retrying '"
                                + result.getName()
                                + "' — attempt "
                                + (currentRetryCount + 1)
                                + " of "
                                + (MAX_RETRY_COUNT + 1)
                );

                // Return true = TestNG will re-run the test immediately.
                return true;
            }

            // Return false = no more retries. TestNG marks the test as FAILED.
            System.out.println(
                    "RetryAnalyzer: max retries reached for '"
                            + result.getName()
                            + "' — marking as FAILED"
            );
            return false;
        }
    }
}