package tests;
import utils.ConfigReader;


/*
==========================================================================
FILE : GoogleTest.java
PACKAGE : tests

PURPOSE
==========================================================================
This file is a COMPLETE TestNG reference for beginners.
Every TestNG annotation, assertion type, and feature is demonstrated
with working code and a detailed explanation of what it does and why.

WHAT YOU WILL LEARN FROM THIS FILE
==========================================================================
1.  All 8 TestNG lifecycle annotations and their execution order
2.  All @Test attributes (priority, groups, description, timeOut, etc.)
3.  Hard assertions (Assert) vs Soft assertions (SoftAssert)
4.  All assertion methods (assertTrue, assertEquals, assertNull, etc.)
5.  @DataProvider for data-driven testing
6.  dependsOnMethods to link test dependencies
7.  expectedExceptions to test error handling
8.  SkipException to intentionally skip a test
9.  ITestResult to read pass/fail/skip results after each test
10. Method parameter injection in @BeforeMethod

EXECUTION ORDER (most important concept for beginners)
==========================================================================

    @BeforeSuite         runs once before the whole suite
        |
    @BeforeTest          runs once before each <test> tag in testng.xml
        |
    @BeforeClass         runs once before all tests in this class
        |
    @BeforeMethod        runs before EVERY single @Test method
        |
    @Test                the actual test method
        |
    @AfterMethod         runs after EVERY single @Test method
        |
    @AfterClass          runs once after all tests in this class
        |
    @AfterTest           runs once after each <test> tag in testng.xml
        |
    @AfterSuite          runs once after the whole suite ends

==========================================================================
*/


/*
==========================================================================
JAVA STANDARD LIBRARY IMPORTS
==========================================================================
These come built into Java. No need to add them to pom.xml.
==========================================================================
*/

// Duration represents a length of time such as 10 seconds or 500 milliseconds.
// WebDriverWait requires a Duration object to know how long to wait.
import java.time.Duration;

// Objects is a utility class with null-safety helper methods.
// We use Objects.requireNonNullElse() to avoid NullPointerException.
import java.util.Objects;

// Method is a reflection class that holds information about a Java method.
// TestNG injects this into @BeforeMethod so you can read the current test name.
import java.lang.reflect.Method;


/*
==========================================================================
SELENIUM IMPORTS
==========================================================================
These come from the selenium-java dependency in pom.xml.
==========================================================================
*/

// By is the class used to locate HTML elements on a page.
// Strategies: By.id(), By.name(), By.cssSelector(), By.xpath(), By.tagName()
import org.openqa.selenium.By;

// Keys represents physical keyboard keys.
// Common uses: Keys.ENTER to submit a form, Keys.TAB to move focus, Keys.ESCAPE
import org.openqa.selenium.Keys;

// WebDriver is the main interface that represents a browser session.
// All browser interactions go through this object.
import org.openqa.selenium.WebDriver;

// WebElement represents a single HTML element on the page.
// Methods: click(), sendKeys(), getText(), isDisplayed(), isEnabled(), getTagName()
import org.openqa.selenium.WebElement;

// ChromeDriver is the Chrome-specific implementation of WebDriver.
// Creating new ChromeDriver() opens a real Chrome browser window.
import org.openqa.selenium.chrome.ChromeDriver;

// ChromeOptions lets you configure Chrome before launching it.
// You can pass flags like --headless, --disable-notifications, --start-maximized
import org.openqa.selenium.chrome.ChromeOptions;

// WebDriverWait performs smart waiting by polling a condition repeatedly.
// It waits until the condition is true or the timeout is reached.
import org.openqa.selenium.support.ui.WebDriverWait;

// ExpectedConditions is a library of pre-built conditions to wait for.
// Examples: visibilityOfElementLocated, elementToBeClickable, titleContains
import org.openqa.selenium.support.ui.ExpectedConditions;


/*
==========================================================================
TESTNG IMPORTS
==========================================================================
These come from the testng dependency in pom.xml.
==========================================================================
*/

// Assert provides hard assertions that stop the test immediately on failure.
import org.testng.Assert;

// SoftAssert collects all failures and reports them together at the end.
import org.testng.asserts.SoftAssert;

// SkipException tells TestNG to mark this test as SKIPPED (not failed).
import org.testng.SkipException;

// ITestResult holds the outcome of a completed test method.
// It gives you the test name, status (pass/fail/skip), and any exception.
import org.testng.ITestResult;

// @Test marks a method as a test case that TestNG will discover and run.
import org.testng.annotations.Test;

// Lifecycle annotations control WHEN each method runs relative to tests.
import org.testng.annotations.BeforeSuite;   // once before the entire suite
import org.testng.annotations.AfterSuite;    // once after the entire suite
import org.testng.annotations.BeforeTest;    // once before each <test> in testng.xml
import org.testng.annotations.AfterTest;     // once after each <test> in testng.xml
import org.testng.annotations.BeforeClass;   // once before all tests in this class
import org.testng.annotations.AfterClass;    // once after all tests in this class
import org.testng.annotations.BeforeMethod;  // before every single @Test method
import org.testng.annotations.AfterMethod;   // after every single @Test method

// @DataProvider marks a method that supplies test data to a @Test method.
import org.testng.annotations.DataProvider;


/*
==========================================================================
CLASS DECLARATION
==========================================================================
IMPORTANT RULE: The class name MUST match the file name exactly.
File name  : GoogleTest.java
Class name : GoogleTest

If they do not match, IntelliJ shows the error:
"Class 'X' is public, should be declared in a file named 'X.java'"
==========================================================================
*/

/**
 * GoogleTest — Complete TestNG Feature Reference
 * <p>
 * Tests Google homepage functionality while demonstrating every major
 * TestNG feature. Each section is clearly labeled so you can study
 * one concept at a time.
 */
public class GoogleTest {


    /*
    ======================================================================
    SECTION A : INSTANCE VARIABLES (Class-Level Fields)
    ======================================================================
    Variables declared here are accessible to ALL methods in this class.
    This is essential because @BeforeClass creates the driver and every
    @Test method needs to use that same driver.

    If you declared driver INSIDE setUp(), it would be a local variable.
    Local variables only exist inside the method that created them.
    Other methods could not see it and IntelliJ would show:
    "Cannot resolve symbol 'driver'"

    WHY "private"?
    "private" means only this class can read or change these variables.
    This prevents other test classes from accidentally modifying your
    browser session. It is called encapsulation.
    ======================================================================
    */

    // The browser session. Created once in @BeforeClass and used by all tests.
    private WebDriver driver;

    // The smart wait object. Created once in @BeforeClass and reused everywhere.
    private WebDriverWait wait;

    // SoftAssert instance. Recreated fresh in @BeforeMethod for every test.
    // IMPORTANT: Never reuse the same SoftAssert across tests. Old failures
    // from a previous test would carry over and corrupt your results.
    private SoftAssert softAssert;


    /*
    ======================================================================
    SECTION B : CONSTANTS
    ======================================================================
    "private static final" means:
    private  : only this class can access it
    static   : belongs to the class itself, not to any instance
    final    : the value can never be changed after it is set

    Convention: constant names are written in UPPER_SNAKE_CASE.

    Storing values as constants means you change them in one place only.
    If you want to test a staging environment, just change BASE_URL here.
    ======================================================================
    */

    // The URL of the application under test.
    private static final String BASE_URL = "https://www.google.com";
    String url = ConfigReader.getProperty("url");

    // Maximum wait time in seconds before WebDriverWait gives up.
    // 10 seconds is a good balance between speed and reliability.
    private static final int TIMEOUT_SECONDS = 10;


    /*
    ======================================================================
    SECTION 1 : SUITE LIFECYCLE
    @BeforeSuite and @AfterSuite
    ======================================================================
    These run ONCE per suite — one time for the entire test run.
    A "suite" = one complete execution of testng.xml.

    @BeforeSuite is the first thing that runs before anything else.
    @AfterSuite is the last thing that runs after everything is done.

    REAL COMPANY USES:
    @BeforeSuite : start a Selenium Grid, connect to a database,
                   read environment config from a properties file,
                   initialize a reporting framework like ExtentReports
    @AfterSuite  : shut down the Grid, close DB connections,
                   generate the final HTML report, send a Slack notification

    DO NOT open the browser here.
    If you have 5 test classes running in parallel, each needs its own
    browser. Opening one browser in @BeforeSuite and sharing it across
    parallel threads causes race conditions and test failures.
    ======================================================================
    */

    /**
     * Runs ONCE before the entire suite starts.
     * <p>
     * Use this for global infrastructure setup that applies to all tests.
     */
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("==============================================");
        System.out.println("SUITE STARTED");
        System.out.println("Target URL : " + BASE_URL);
        System.out.println("==============================================");

        /*
        In a real project this is where you would write:
            ExtentReports extent = new ExtentReports();
            ExtentSparkReporter reporter = new ExtentSparkReporter("report.html");
            extent.attachReporter(reporter);
        */
    }

    /**
     * Runs ONCE after the entire suite finishes, even if tests failed.
     * <p>
     * Use this for global cleanup and final reporting.
     */
    @AfterSuite
    public void afterSuite() {
        System.out.println("==============================================");
        System.out.println("SUITE COMPLETED");
        System.out.println("==============================================");

        /*
        In a real project this is where you would write:
            extent.flush(); // writes the final HTML report to disk
        */
    }


    /*
    ======================================================================
    SECTION 2 : TEST-BLOCK LIFECYCLE
    @BeforeTest and @AfterTest
    ======================================================================
    These run once per <test> block in testng.xml — NOT once per @Test method.

    A <test> block in testng.xml looks like this:
        <test name="Google Tests">
            <classes>
                <class name="tests.GoogleTest"/>
            </classes>
        </test>

    If your testng.xml has 3 <test> blocks, @BeforeTest runs 3 times.

    IMPORTANT DISTINCTION:
    @BeforeTest  : once per <test> XML tag
    @BeforeMethod: once per @Test Java method (much more frequent)

    REAL COMPANY USE:
    @BeforeTest  : set up environment-specific configuration that applies
                   to all classes inside one <test> block
    @AfterTest   : generate a sub-report for that specific <test> block
    ======================================================================
    */

    /**
     * Runs once before each {@code <test>} block in testng.xml begins.
     */
    @BeforeTest
    public void beforeTest() {
        System.out.println("--- BeforeTest : starting test block ---");
    }

    /**
     * Runs once after each {@code <test>} block in testng.xml finishes.
     */
    @AfterTest
    public void afterTest() {
        System.out.println("--- AfterTest : test block finished ---");
    }


    /*
    ======================================================================
    SECTION 3 : CLASS LIFECYCLE
    @BeforeClass and @AfterClass
    ======================================================================
    @BeforeClass runs ONCE before the first @Test method in this class.
    @AfterClass  runs ONCE after the last @Test method in this class.

    @BeforeClass is the correct place to open the browser because:
    - All tests in this class share the same browser session
    - Opening the browser once is much faster than opening it before every test
    - All @Test methods can then use the same driver and wait objects

    WHY NOT @BeforeMethod for browser setup?
    @BeforeMethod opens the browser before EVERY @Test method.
    If this class has 10 tests, that means 10 browser open + 10 browser close
    cycles. That is extremely slow and wasteful for tests that share state.
    Use @BeforeMethod only when each test truly needs a completely fresh browser.

    WHY @AfterClass for browser shutdown?
    @AfterClass is guaranteed to run even if some tests failed.
    This means the browser always closes cleanly and no zombie Chrome
    processes are left running in the background.
    ======================================================================
    */

    /**
     * Runs ONCE before any test method in this class executes.
     * <p>
     * Configures Chrome, opens the browser, sets up smart waiting,
     * and navigates to the application URL.
     */
    @BeforeClass
    public void setUp() {

        /*
        ChromeOptions — Configure Chrome Before Opening
        ================================================
        ChromeOptions accepts command-line flags that control how Chrome
        behaves when it starts. Think of these as Chrome's startup settings.

        --disable-notifications
        Prevents the "Allow notifications?" popup from appearing on sites.
        That popup can block your tests from finding elements underneath it.

        --disable-popup-blocking
        Prevents Chrome from blocking any popups that your test might need.

        --start-maximized
        Opens Chrome in full-screen mode immediately.
        This ensures all page elements are in their normal visible positions
        and nothing is hidden off-screen due to a small viewport.

        Headless mode for CI/CD (no screen available):
        Uncomment the line below when running in GitHub Actions or Jenkins.
        --headless=new is the modern headless flag for Chrome 112 and above.
        The old --headless flag is deprecated and should not be used anymore.
        */
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        // options.addArguments("--headless=new");

        /*
        new ChromeDriver(options)
        ==========================
        Selenium Manager (built into Selenium since version 4.6) automatically:
        1. Detects which version of Chrome is installed on your machine
        2. Downloads the exact matching chromedriver binary silently
        3. Opens a real Chrome window with your options applied

        You do NOT need to download chromedriver manually.
        You do NOT need WebDriverManager library anymore.
        */
        driver = new ChromeDriver(options);

        /*
        WebDriverWait — The Professional Way to Handle Page Loading
        ============================================================
        Web pages and their elements load at unpredictable speeds.
        The speed depends on network, server load, and JavaScript execution.

        BAD approach used by beginners:
            Thread.sleep(3000);
            This always waits exactly 3 seconds even if the element appeared
            in 100ms. It makes tests slow and still flaky on slow networks.

        GOOD approach used in production:
            WebDriverWait polls the condition every 500ms by default.
            The moment the condition becomes true, it moves on immediately.
            If the condition never becomes true within TIMEOUT_SECONDS, it
            throws TimeoutException and the test fails with a clear message.

        Result: tests run faster on fast machines and are still reliable
        on slow machines or slow network connections.
        */
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));

        /*
        driver.get(url)
        ================
        Navigates Chrome to the given URL.
        This is equivalent to typing the URL in the address bar and pressing Enter.
        driver.get() blocks until the page's readyState is "complete".
        It does NOT wait for JavaScript-rendered content — use WebDriverWait for that.
        */
        driver.get(BASE_URL);
    }

    /**
     * Runs ONCE after all test methods in this class have finished.
     * <p>
     * Always close the browser here to release system resources and
     * prevent orphan chromedriver processes from running in the background.
     */
    @AfterClass
    public void tearDown() {
        /*
        Null check before calling quit():
        If setUp() threw an exception (Chrome not found, network error, etc.),
        the driver variable was never assigned and it is still null.
        Calling null.quit() would throw NullPointerException.
        This check prevents that secondary error from hiding the real problem.

        driver.quit() vs driver.close()
        close() : closes only the currently active browser tab.
                  Other tabs remain open. The WebDriver session stays active.
        quit()  : closes ALL tabs and windows, ends the WebDriver session,
                  and terminates the chromedriver process completely.
        Always use quit() in teardown — never close().
        */
        if (driver != null) {
            driver.quit();
        }
    }


    /*
    ======================================================================
    SECTION 4 : METHOD LIFECYCLE
    @BeforeMethod and @AfterMethod
    ======================================================================
    @BeforeMethod runs before EVERY SINGLE @Test method.
    @AfterMethod  runs after  EVERY SINGLE @Test method, even on failure.

    EXECUTION PATTERN FOR 3 TESTS:
        beforeMethod()  <- runs
        testOne()       <- runs
        afterMethod()   <- runs

        beforeMethod()  <- runs
        testTwo()       <- runs
        afterMethod()   <- runs

        beforeMethod()  <- runs
        testThree()     <- runs
        afterMethod()   <- runs

    PARAMETER INJECTION:
    TestNG can inject parameters into these methods automatically.
    @BeforeMethod can accept: Method (gives you the test method details)
    @AfterMethod  can accept: ITestResult (gives you the test outcome)
    You do not call these methods yourself — TestNG handles injection.

    alwaysRun = true:
    By default, @BeforeMethod is skipped if a test it depends on failed.
    Setting alwaysRun = true forces it to always run no matter what.
    This ensures the browser is always reset to a clean state.
    ======================================================================
    */

    /**
     * Runs before every {@code @Test} method.
     * <p>
     * Creates a fresh SoftAssert and resets the browser to the home page.
     * The Method parameter is injected by TestNG and contains the name
     * and details of the test method that is about to run.
     *
     * @param method the reflection object for the upcoming @Test method
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeEachTest(Method method) {

        // method.getName() returns the exact name of the @Test method
        // that is about to run. Useful for logging and debugging.
        System.out.println("\nSTARTING : " + method.getName());

        /*
        Create a fresh SoftAssert before every test.
        SoftAssert stores failures in an internal list.
        If you reuse the same SoftAssert object across tests, failures
        from Test 1 will still be in the list when Test 2 runs.
        That causes Test 2 to report failures that belong to Test 1.
        Always create a new SoftAssert in @BeforeMethod.
        */
        softAssert = new SoftAssert();

        /*
        Reset the browser to the home page before every test.
        This ensures each test starts from a predictable, clean state.
        Without this, if testA navigates to a search results page and
        testB expects to be on the home page, testB will fail for the
        wrong reason.
        */
        driver.get(BASE_URL);

        // Wait until the home page title confirms we are on the right page.
        wait.until(ExpectedConditions.titleContains("Google"));
    }

    /**
     * Runs after every {@code @Test} method, even if the test failed.
     * <p>
     * ITestResult is injected by TestNG and contains the complete outcome
     * of the test that just finished, including its name, status, and
     * any exception that was thrown.
     *
     * @param result the TestNG result object containing test outcome data
     */
    @AfterMethod(alwaysRun = true)
    public void afterEachTest(ITestResult result) {

        /*
        ITestResult provides everything you need to know about a completed test.

        result.getName()            : the method name of the test
        result.getStatus()          : integer — 1=PASS, 2=FAIL, 3=SKIP
        result.getThrowable()       : the exception if the test failed, else null
        result.getStartMillis()     : timestamp when the test started
        result.getEndMillis()       : timestamp when the test ended
        result.getParameters()      : array of DataProvider values used
        result.getTestClass()       : the class the test belongs to

        ITestResult.SUCCESS = 1
        ITestResult.FAILURE = 2
        ITestResult.SKIP    = 3
        */
        String testName = result.getName();
        long duration   = result.getEndMillis() - result.getStartMillis();

        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("PASSED  : " + testName + " [" + duration + "ms]");

        } else if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("FAILED  : " + testName + " [" + duration + "ms]");
            System.out.println("REASON  : " + result.getThrowable().getMessage());
            /*
            In a real project this is where you capture a screenshot:
                File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("screenshots/" + testName + ".png"));
            */

        } else {
            System.out.println("SKIPPED : " + testName);
        }
    }


    /*
    ======================================================================
    SECTION 5 : SMOKE TESTS
    ======================================================================
    Smoke tests are the fastest, most essential checks in any test suite.
    They answer one question: "Is the application basically working?"

    Rules for smoke tests:
    - Must complete within 2 to 5 minutes for the whole group
    - Cover only the most critical user-facing features
    - Run on every single code commit in CI/CD pipelines
    - If smoke tests fail, stop immediately and do not run regression

    Run from terminal: mvn test -Dgroups=smoke
    ======================================================================
    */

    /**
     * SMOKE TEST 1 — Verify Page Title
     * <p>
     * The most basic sanity check. If this fails, the site is down or
     * the URL is wrong. No other test is worth running in that case.
     * <p>
     * @Test ATTRIBUTES EXPLAINED:
     * priority    : controls execution order. Lower number runs first.
     *               Tests with the same priority run in alphabetical order.
     * groups      : tags this test. Run with -Dgroups=smoke from terminal.
     *               A test can belong to multiple groups at the same time.
     * description : documents what this test checks. Appears in HTML reports.
     *               Always write a description — it makes reports self-explanatory.
     */
    @Test(
            priority    = 1,
            groups      = { "smoke", "regression" },
            description = "Verifies the browser tab title contains 'Google' after page load"
    )
    public void verifyPageTitleTest() {

        /*
        Objects.requireNonNullElse(value, defaultValue)
        ================================================
        driver.getTitle() can return null if the page failed to load.
        Calling .contains() on null throws NullPointerException.
        requireNonNullElse returns the defaultValue ("") if value is null.
        This way the assertion fails with your message instead of crashing.
        */
        String title = Objects.requireNonNullElse(driver.getTitle(), "");

        System.out.println("Page title : " + title);

        /*
        Assert.assertTrue(condition, failureMessage)
        =============================================
        HARD ASSERTION — stops this test immediately if the condition is false.
        The test is marked as FAILED and no more lines in this method run.

        Always include the failure message (second argument).
        Without it, IntelliJ and reports show only "expected true but was false"
        which tells you nothing about what actually went wrong.
        */
        Assert.assertTrue(
                title.contains("Google"),
                "Page title should contain 'Google' but was: " + title
        );
    }

    /**
     * SMOKE TEST 2 — Verify Google Logo is Visible
     * <p>
     * Confirms the main logo image is rendered on the home page.
     * A missing logo can indicate a broken CDN or failed asset delivery.
     */
    @Test(
            priority    = 2,
            groups      = { "smoke" },
            description = "Verifies the Google logo image is visible on the home page"
    )
    public void verifyLogoVisibleTest() {

        /*
        ExpectedConditions.visibilityOfElementLocated(By locator)
        ==========================================================
        This condition waits until two things are true:
        1. The element EXISTS in the page HTML (is in the DOM)
        2. The element is VISIBLE to the user (not hidden by CSS)

        An element can exist in the HTML but be invisible (display:none).
        visibilityOfElementLocated checks BOTH, which is what you want.

        By.cssSelector("img[alt='Google']")
        Finds an img element that has the attribute alt with value "Google".
        CSS selectors are preferred over XPath because they are shorter,
        faster to execute, and easier to read and maintain.
        XPath equivalent: //img[@alt='Google']
        */
        WebElement logo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("img[alt='Google']")
                )
        );

        /*
        element.isDisplayed()
        ======================
        Returns true if the element is currently visible on screen.
        It checks that the element has non-zero dimensions and is not
        hidden by CSS properties like display:none or visibility:hidden.
        */
        Assert.assertTrue(
                logo.isDisplayed(),
                "Google logo should be visible on the home page"
        );
    }


    /*
    ======================================================================
    SECTION 6 : REGRESSION TESTS
    ======================================================================
    Regression tests verify core functionality in depth.
    They are slower than smoke tests and typically run on a nightly schedule.

    Run from terminal: mvn test -Dgroups=regression
    ======================================================================
    */

    /**
     * REGRESSION TEST 1 — Verify Search Returns Results
     * <p>
     * Demonstrates: sendKeys, Keys.ENTER, elementToBeClickable, titleContains.
     * This is the most important functional test for a search application.
     */
    @Test(
            priority    = 3,
            groups      = { "regression" },
            description = "Verifies typing a query and pressing Enter shows results"
    )
    public void verifySearchFunctionalityTest() {

        /*
        ExpectedConditions.elementToBeClickable(By locator)
        =====================================================
        Stronger than visibilityOfElementLocated.
        It waits until the element is:
        1. Visible on screen (not hidden by CSS)
        2. Enabled (not disabled or read-only)

        Use elementToBeClickable before typing into inputs or clicking buttons.
        Use visibilityOfElementLocated just to confirm something is shown.
        */
        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("q"))
        );

        // Clear any text already in the box before typing your query.
        // In a shared browser session a previous test might have left text there.
        searchBox.clear();

        // sendKeys() simulates typing on the keyboard character by character.
        searchBox.sendKeys("Selenium WebDriver");

        /*
        Keys.ENTER
        ===========
        Simulates pressing the physical Enter key on the keyboard.
        This submits the search form. It is more realistic than finding
        and clicking the search button element separately.
        */
        searchBox.sendKeys(Keys.ENTER);

        /*
        Wait for the results page title to update.
        The title changes from "Google" to "Selenium WebDriver - Google Search".
        Waiting for titleContains is a reliable way to confirm the results
        page has fully loaded before making any assertions.
        */
        wait.until(ExpectedConditions.titleContains("Selenium"));

        String resultsTitle = Objects.requireNonNullElse(driver.getTitle(), "");

        /*
        .toLowerCase() makes the comparison case-insensitive.
        Page titles can be capitalized differently depending on browser, OS,
        or Google's own A/B testing of title formats.
        Converting to lowercase ensures the assertion works reliably everywhere.
        */
        Assert.assertTrue(
                resultsTitle.toLowerCase().contains("selenium"),
                "Results page title should contain 'selenium' but was: " + resultsTitle
        );
    }

    /**
     * REGRESSION TEST 2 — All Assert Methods Demonstrated
     * <p>
     * Demonstrates all major Assert methods in one test so you can see
     * each one in context and understand when to use each type.
     */
    @Test(
            priority    = 4,
            groups      = { "regression" },
            description = "Demonstrates all hard assertion types: assertTrue, assertFalse, assertEquals, assertNotNull, assertNull, assertNotEquals"
    )
    public void verifyAllAssertTypesTest() {

        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("q"))
        );

        /*
        ASSERT TYPE 1: Assert.assertTrue(condition, message)
        Use when: you expect something to be true.
        Passes if: condition == true
        Fails if:  condition == false
        */
        Assert.assertTrue(
                searchBox.isDisplayed(),
                "Search box should be visible"
        );

        /*
        ASSERT TYPE 2: Assert.assertFalse(condition, message)
        Use when: you expect something to be false.
        Passes if: condition == false
        Fails if:  condition == true
        Here we confirm the search box is not pre-filled with text on load.
        */
        String currentText = searchBox.getText();
        Assert.assertFalse(
                currentText.equals("pre-filled text"),
                "Search box should not contain pre-filled text on page load"
        );

        /*
        ASSERT TYPE 3: Assert.assertEquals(actual, expected, message)
        Use when: you need an exact value match.
        Passes if: actual.equals(expected)
        Fails if:  they are different

        IMPORTANT: always put actual first, expected second.
        Getting them backwards does not break the test but makes failure
        messages say the opposite of what you intended.

        Google renders the search box as a textarea element in 2026.
        */
        Assert.assertEquals(
                searchBox.getTagName(),
                "textarea",
                "Search element should be a textarea"
        );

        /*
        ASSERT TYPE 4: Assert.assertNotEquals(actual, notExpected, message)
        Use when: you want to confirm two values are different.
        Passes if: actual does NOT equal notExpected
        Fails if:  they are equal
        */
        Assert.assertNotEquals(
                searchBox.getTagName(),
                "div",
                "Search element should not be a div"
        );

        /*
        ASSERT TYPE 5: Assert.assertNotNull(value, message)
        Use when: you want to confirm a value was returned (not null).
        Passes if: value != null
        Fails if:  value == null
        Common use: check that getAttribute(), getText(), getTitle() returned something.
        */
        String placeholder = searchBox.getAttribute("placeholder");
        Assert.assertNotNull(
                driver.getTitle(),
                "Page title should not be null"
        );

        /*
        ASSERT TYPE 6: Assert.assertNull(value, message)
        Use when: you expect a value to be null (not exist).
        Passes if: value == null
        Fails if:  value != null
        Common use: check that an error message element does not exist yet.
        Here we verify a non-existent attribute returns null.
        */
        String nonExistentAttr = searchBox.getAttribute("data-nonexistent-attribute");
        Assert.assertNull(
                nonExistentAttr,
                "Non-existent attribute should return null"
        );
    }

    /**
     * REGRESSION TEST 3 — SoftAssert: Validate Multiple Elements
     * <p>
     * Demonstrates SoftAssert — the key alternative to hard Assert.
     * <p>
     * KEY DIFFERENCE:
     * Hard Assert (Assert.assertTrue) : stops the test on first failure.
     *                                   You only see one failure at a time.
     * Soft Assert (softAssert.assertTrue): collects all failures.
     *                                      You see all failures at once.
     * <p>
     * When to use SoftAssert:
     * Use it when you are validating multiple INDEPENDENT properties on a page
     * and you want to know WHICH ones failed, not just the first one.
     * Example: checking 6 UI elements on a dashboard page.
     * <p>
     * When to use hard Assert:
     * Use it when later assertions depend on the earlier one passing.
     * Example: if the page did not load, checking its elements is pointless.
     */
    @Test(
            priority    = 5,
            groups      = { "regression", "sanity" },
            description = "Validates multiple home page properties using SoftAssert"
    )
    public void verifyPageElementsWithSoftAssertTest() {

        // softAssert was created fresh in @BeforeMethod — ready to use.

        String title = Objects.requireNonNullElse(driver.getTitle(), "");
        String url   = Objects.requireNonNullElse(driver.getCurrentUrl(), "");

        // Check 1: page title contains "Google"
        softAssert.assertTrue(
                title.contains("Google"),
                "Title check failed, was: " + title
        );

        // Check 2: URL contains "google.com"
        softAssert.assertTrue(
                url.contains("google.com"),
                "URL check failed, was: " + url
        );

        // Check 3: search box is displayed
        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("q"))
        );
        softAssert.assertTrue(
                searchBox.isDisplayed(),
                "Search box should be visible"
        );

        // Check 4: search box is enabled
        softAssert.assertTrue(
                searchBox.isEnabled(),
                "Search box should be enabled"
        );

        // Check 5: Google logo is present
        WebElement logo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("img[alt='Google']")
                )
        );
        softAssert.assertTrue(
                logo.isDisplayed(),
                "Logo should be visible"
        );

        // Check 6: page title is not null
        softAssert.assertNotNull(
                driver.getTitle(),
                "Page title should not be null"
        );

        /*
        assertAll() IS MANDATORY.
        ==========================
        Without this line, even if ALL 6 checks above FAILED, the test
        would show as PASSED. softAssert collects failures in memory but
        does not throw them until you call assertAll().
        assertAll() throws one combined AssertionError that lists every
        check that failed.

        This is THE most common SoftAssert mistake made by beginners.
        */
        softAssert.assertAll();
    }

    /**
     * REGRESSION TEST 4 — dependsOnMethods
     * <p>
     * Demonstrates how to create a dependency between two test methods.
     * <p>
     * What dependsOnMethods does:
     * This test runs ONLY if verifySearchFunctionalityTest passes.
     * If verifySearchFunctionalityTest fails, this test is automatically SKIPPED.
     * <p>
     * Why is this useful?
     * It models real user flows: if login is broken, there is no point testing
     * the dashboard, cart, or checkout — all of those should be skipped too.
     * dependsOnMethods communicates this relationship to TestNG explicitly.
     */
    @Test(
            priority          = 6,
            groups            = { "regression" },
            description       = "Verifies the search results URL structure — depends on search test passing",
            dependsOnMethods  = { "verifySearchFunctionalityTest" }
    )
    public void verifySearchResultsUrlTest() {

        // Perform a fresh search since @BeforeMethod reset us to the home page.
        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("q"))
        );
        searchBox.sendKeys("TestNG tutorial");
        searchBox.sendKeys(Keys.ENTER);

        // Wait for the URL to update to a search results URL.
        wait.until(ExpectedConditions.urlContains("search"));

        String currentUrl = Objects.requireNonNullElse(driver.getCurrentUrl(), "");

        Assert.assertNotNull(
                currentUrl,
                "Current URL should not be null after search"
        );

        Assert.assertTrue(
                currentUrl.contains("google.com/search"),
                "URL after search should contain 'google.com/search' but was: " + currentUrl
        );
    }

    /**
     * REGRESSION TEST 5 — Data-Driven Testing with @DataProvider
     * <p>
     * Demonstrates @DataProvider — one of the most powerful TestNG features.
     * This single test method runs multiple times, once for each row of data
     * supplied by the searchTermsProvider() method defined below.
     * <p>
     * With the current provider supplying 3 rows, this test runs 3 times:
     * Run 1 : searchTerm = "Selenium",  expectedWord = "Selenium"
     * Run 2 : searchTerm = "Java",      expectedWord = "Java"
     * Run 3 : searchTerm = "TestNG",    expectedWord = "TestNG"
     * <p>
     * The dataProvider value must match the name in @DataProvider exactly.
     * The method parameters must match the Object array types in order.
     *
     * @param searchTerm    the keyword to type into the search box
     * @param expectedWord  the word expected to appear in the results page title
     */
    @Test(
            priority     = 7,
            groups       = { "regression" },
            description  = "Runs the same search test multiple times with different keywords using DataProvider",
            dataProvider = "searchTermsProvider"
    )
    public void verifySearchWithDataProviderTest(String searchTerm, String expectedWord) {

        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("q"))
        );

        searchBox.clear();
        searchBox.sendKeys(searchTerm);
        searchBox.sendKeys(Keys.ENTER);

        // Wait for a title that contains the expected word.
        wait.until(ExpectedConditions.titleContains(expectedWord));

        String resultsTitle = Objects.requireNonNullElse(driver.getTitle(), "");

        Assert.assertTrue(
                resultsTitle.toLowerCase().contains(expectedWord.toLowerCase()),
                "Search for '" + searchTerm + "' : title should contain '"
                        + expectedWord + "' but was: " + resultsTitle
        );
    }

    /**
     * REGRESSION TEST 6 — expectedExceptions
     * <p>
     * Demonstrates how to test that your code throws the correct exception.
     * <p>
     * What expectedExceptions does:
     * If the @Test method throws the specified exception, TestNG marks it PASSED.
     * If the method does NOT throw that exception, TestNG marks it FAILED.
     * If the method throws a DIFFERENT exception, TestNG marks it FAILED.
     * <p>
     * Real-world use: testing that your code throws IllegalArgumentException
     * when a user provides invalid input, or that it throws a custom
     * PageNotFoundException when a URL does not exist.
     */
    @Test(
            priority           = 8,
            groups             = { "regression" },
            description        = "Verifies that dividing by zero throws ArithmeticException",
            expectedExceptions = { ArithmeticException.class }
    )
    public void verifyExpectedExceptionTest() {

        /*
        This line deliberately causes an ArithmeticException (division by zero).
        Because expectedExceptions = { ArithmeticException.class } is declared
        in @Test, TestNG EXPECTS this exception to be thrown.
        When it IS thrown, the test is marked as PASSED.
        If this line did NOT throw an exception, the test would FAIL.

        Real project example:
            String result = myValidator.validate(null);
        If validate() is supposed to throw IllegalArgumentException on null input,
        you would set expectedExceptions = { IllegalArgumentException.class }.
        */
        int result = 10 / 0;
    }


    /*
    ======================================================================
    SECTION 7 : SANITY TESTS
    ======================================================================
    Sanity tests are a focused subset of regression tests.
    They run after a specific bug fix to confirm that fix works
    without running the entire regression suite.
    They are faster than full regression but deeper than smoke.

    Run from terminal: mvn test -Dgroups=sanity
    ======================================================================
    */

    /**
     * SANITY TEST — Verify Search Box Attributes
     * <p>
     * A quick sanity check after any change that could affect the search box.
     * Uses multiple assertions to cover all important properties at once.
     */
    @Test(
            priority    = 9,
            groups      = { "sanity" },
            description = "Verifies search box is displayed, enabled, and is a textarea element"
    )
    public void verifySearchBoxAttributesTest() {

        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("q"))
        );

        // Confirm the element is visible on screen.
        Assert.assertTrue(searchBox.isDisplayed(), "Search box should be visible");

        // Confirm the element is not disabled.
        Assert.assertTrue(searchBox.isEnabled(), "Search box should be enabled");

        // Confirm the element is a textarea (Google changed from input to textarea).
        Assert.assertEquals(
                searchBox.getTagName(),
                "textarea",
                "Search element tag should be textarea"
        );
    }


    /*
    ======================================================================
    SECTION 8 : E2E TESTS (End-to-End)
    ======================================================================
    E2E tests simulate a complete user journey through multiple steps.
    They test features working together, not in isolation.

    E2E tests are the slowest and most expensive to maintain.
    Run them before major releases, not on every commit.

    Run from terminal: mvn test -Dgroups=e2e
    ======================================================================
    */

    /**
     * E2E TEST 1 — Full User Search Journey
     * <p>
     * Simulates a real user: arrives at home page, searches, and verifies results.
     * This covers multiple steps working together as a complete workflow.
     */
    @Test(
            priority    = 10,
            groups      = { "e2e" },
            description = "Simulates a complete search journey: home page to results"
    )
    public void verifyFullSearchJourneyTest() {

        // Step 1: Confirm we are on the home page before starting.
        String homeTitle = Objects.requireNonNullElse(driver.getTitle(), "");
        Assert.assertTrue(
                homeTitle.contains("Google"),
                "Step 1 failed: should be on Google home page"
        );

        // Step 2: Find the search box and type a query.
        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("q"))
        );
        searchBox.clear();
        searchBox.sendKeys("Test Automation 2026");
        searchBox.sendKeys(Keys.ENTER);

        // Step 3: Wait for the results page to load.
        wait.until(ExpectedConditions.titleContains("Test Automation"));

        // Step 4: Confirm the title changed to a results page title.
        String resultsTitle = Objects.requireNonNullElse(driver.getTitle(), "");
        Assert.assertTrue(
                resultsTitle.toLowerCase().contains("automation"),
                "Step 4 failed: results title should contain 'automation', was: " + resultsTitle
        );

        // Step 5: Confirm the URL updated to a search results URL.
        String resultsUrl = Objects.requireNonNullElse(driver.getCurrentUrl(), "");
        Assert.assertTrue(
                resultsUrl.contains("google.com/search"),
                "Step 5 failed: URL should contain 'google.com/search', was: " + resultsUrl
        );
    }

    /**
     * E2E TEST 2 — Page Load Performance Guard (timeOut)
     * <p>
     * Demonstrates @Test(timeOut) — a built-in performance guard in TestNG.
     * <p>
     * timeOut is specified in MILLISECONDS (1000ms = 1 second).
     * If this entire test method takes longer than timeOut milliseconds,
     * TestNG interrupts it and marks it FAILED with the message:
     * "Method timed out after [timeOut] ms"
     * <p>
     * Use this as a basic performance guardrail inside functional tests.
     * It is not a replacement for dedicated performance tools like JMeter.
     * 15000ms = 15 seconds. Google loads in under 5 seconds normally,
     * so 15 seconds is a safe but meaningful limit.
     */
    @Test(
            priority    = 11,
            groups      = { "e2e" },
            description = "Verifies the Google home page loads within 15 seconds",
            timeOut     = 15000
    )
    public void verifyPageLoadWithinTimeoutTest() {

        long startTime = System.currentTimeMillis();

        // Navigate fresh to measure actual load time from scratch.
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.titleContains("Google"));

        long loadTime = System.currentTimeMillis() - startTime;
        System.out.println("Load time : " + loadTime + "ms");

        // The timeOut=15000 attribute handles the hard limit automatically.
        // This assertion adds a softer 10-second threshold for reporting detail.
        Assert.assertTrue(
                loadTime < 10000,
                "Page should load in under 10 seconds but took: " + loadTime + "ms"
        );
    }


    /*
    ======================================================================
    SECTION 9 : SKIPPED TEST
    ======================================================================
    Two ways to skip a test in TestNG:

    WAY 1: enabled = false on the @Test annotation
    The test is completely removed from the run.
    It does NOT appear in reports at all.
    Risk: you forget it exists. Use this very sparingly.
    Example: @Test(enabled = false)

    WAY 2: throw new SkipException("reason") inside the method
    The test appears in reports as SKIPPED (yellow).
    The reason message is visible to the whole team in the report.
    This is the PREFERRED approach in professional projects because
    the test remains visible as a reminder that it needs attention.
    ======================================================================
    */

    /**
     * SKIPPED TEST — Feature Not Yet Implemented
     * <p>
     * Demonstrates SkipException to intentionally mark a test as SKIPPED.
     * SKIPPED tests appear yellow in HTML reports — they are not failures.
     * <p>
     * When to skip a test:
     * - The feature being tested is not built yet.
     * - A known bug is blocking this test (ticket is filed).
     * - The test applies only to a specific environment not currently available.
     * - A third-party dependency is down in this environment.
     */
    @Test(
            priority    = 12,
            groups      = { "e2e" },
            description = "Placeholder for Voice Search feature not yet deployed to this environment"
    )
    public void verifyVoiceSearchFeatureTest() {

        /*
        throw new SkipException("reason")
        ===================================
        Throwing SkipException anywhere in a @Test method immediately stops
        the test and marks it as SKIPPED in the TestNG report.
        The message you provide is shown in the report next to the test name.

        Always write a specific, actionable reason so your team knows:
        - WHY it was skipped
        - WHAT ticket tracks the work
        - WHEN to re-enable it
        */
        throw new SkipException(
                "Skipping: Voice Search feature not yet deployed to staging environment. "
                        + "Tracking ticket: PROJ-9876. Re-enable once feature flag is enabled."
        );
    }


    /*
    ======================================================================
    SECTION 10 : DATA PROVIDER
    ======================================================================
    @DataProvider is a method that returns test data as a 2D Object array.
    TestNG calls the linked @Test method once for each row in the array.

    Object[][] structure:
    Each inner array { } represents one test run (one row of data).
    The values inside map to the @Test method parameters IN ORDER.

    For verifySearchWithDataProviderTest(String searchTerm, String expectedWord):
        { "Selenium", "Selenium" }
        means: searchTerm = "Selenium", expectedWord = "Selenium"

    The "name" value in @DataProvider must match the "dataProvider" value
    in @Test exactly. TestNG uses this name to connect them.

    @DataProvider methods are NOT test methods themselves.
    They are supplier methods that feed data into real test methods.
    ======================================================================
    */

    /**
     * Supplies search keyword and expected result title word pairs.
     * <p>
     * Called automatically by TestNG before each run of
     * {@code verifySearchWithDataProviderTest()}.
     * This method itself is not a test — it only provides data.
     *
     * @return a 2D array where each row contains one set of test inputs
     */
    @DataProvider(name = "searchTermsProvider")
    public Object[][] searchTermsProvider() {
        return new Object[][] {
                // { searchTerm,    expectedWord }
                { "Selenium",      "Selenium"   },
                { "Java",          "Java"       },
                { "TestNG",        "TestNG"     }
        };
        /*
        This causes verifySearchWithDataProviderTest to run 3 times:
        Run 1 : searchTerm = "Selenium",  expectedWord = "Selenium"
        Run 2 : searchTerm = "Java",      expectedWord = "Java"
        Run 3 : searchTerm = "TestNG",    expectedWord = "TestNG"

        Each run appears as a separate row in the HTML test report.
        */
    }
}