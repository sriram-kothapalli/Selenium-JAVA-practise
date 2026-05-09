# TestNG + Maven Execution Commands
## Complete Beginner Reference Guide — 2026

---

## BEFORE YOU START — Understanding the Basics

### What is Maven?
Maven is a build tool for Java projects. It downloads all your libraries
(Selenium, TestNG, etc.) automatically, compiles your Java code, runs your
tests, and generates reports. You control Maven by typing `mvn` commands
in the terminal.

### What is TestNG?
TestNG is the testing framework. It reads your Java test classes and decides
how to run them based on annotations, groups, priorities, and testng.xml.

### How They Connect

```
mvn test
    |
pom.xml          (project config, dependencies, plugin versions)
    |
Surefire Plugin  (Maven plugin that triggers test execution)
    |
testng.xml       (master control: which classes, groups, parallel settings)
    |
GoogleTest.java  (your actual test methods)
    |
target/surefire-reports/   (HTML and XML reports generated after the run)
```

---

## TESTNG LIFECYCLE — Execution Order

This is the most important concept to understand in TestNG.
Every annotation runs in a fixed order no matter how your tests are written.

```
@BeforeSuite       runs ONCE before the entire suite starts
    |
@BeforeTest        runs ONCE before each <test> block in testng.xml
    |
@BeforeClass       runs ONCE before all @Test methods in this class
    |
@BeforeMethod      runs before EVERY single @Test method
    |
@Test              the actual test method runs here
    |
@AfterMethod       runs after EVERY single @Test method
    |
@AfterClass        runs ONCE after all @Test methods in this class
    |
@AfterTest         runs ONCE after each <test> block in testng.xml
    |
@AfterSuite        runs ONCE after the entire suite finishes
```

---

## WHAT EACH ANNOTATION IS USED FOR IN REAL COMPANIES

| Annotation        | Real Company Usage                                      |
|-------------------|---------------------------------------------------------|
| `@BeforeSuite`    | Start reporting framework, connect to database          |
| `@AfterSuite`     | Generate final report, send Slack notification          |
| `@BeforeTest`     | Set environment-specific configuration per test block   |
| `@AfterTest`      | Generate sub-report for that test block                 |
| `@BeforeClass`    | Open the browser once for all tests in the class        |
| `@AfterClass`     | Close the browser after all tests are done              |
| `@BeforeMethod`   | Reset app state, create fresh SoftAssert, log test name |
| `@AfterMethod`    | Capture screenshot on failure, update report per test   |

---

## YOUR CURRENT TEST STRUCTURE

All tests are inside `GoogleTest.java`. Here is the complete test map:

| Method                             | Group(s)              | Priority | Feature Demonstrated          |
|------------------------------------|-----------------------|----------|-------------------------------|
| `verifyPageTitleTest`              | smoke, regression     | 1        | Hard Assert, getTitle         |
| `verifyLogoVisibleTest`            | smoke                 | 2        | visibilityOfElementLocated    |
| `verifySearchFunctionalityTest`    | regression            | 3        | sendKeys, Keys.ENTER, waits   |
| `verifyAllAssertTypesTest`         | regression            | 4        | All 6 Assert methods          |
| `verifyPageElementsWithSoftAssert` | regression, sanity    | 5        | SoftAssert, assertAll         |
| `verifySearchResultsUrlTest`       | regression            | 6        | dependsOnMethods              |
| `verifySearchWithDataProviderTest` | regression            | 7        | @DataProvider (runs 3 times)  |
| `verifyExpectedExceptionTest`      | regression            | 8        | expectedExceptions            |
| `verifySearchBoxAttributesTest`    | sanity                | 9        | Multiple assertions           |
| `verifyFullSearchJourneyTest`      | e2e                   | 10       | Multi-step user journey       |
| `verifyPageLoadWithinTimeoutTest`  | e2e                   | 11       | timeOut attribute             |
| `verifyVoiceSearchFeatureTest`     | e2e                   | 12       | SkipException                 |

---

## ASSERTION TYPES COVERED

### Hard Assert (stops test immediately on first failure)

```java
    Assert.assertTrue(condition, message)       // expects true
    Assert.assertFalse(condition, message)      // expects false
    Assert.assertEquals(actual, expected, msg)  // expects exact match
    Assert.assertNotEquals(actual, val, msg)    // expects values to differ
    Assert.assertNotNull(value, message)        // expects value to exist
    Assert.assertNull(value, message)           // expects value to be null
```

### Soft Assert (collects all failures, reports at the end)

```java
    softAssert.assertTrue(condition, message)
    softAssert.assertFalse(condition, message)
    softAssert.assertEquals(actual, expected, message)
    softAssert.assertNotNull(value, message)
    softAssert.assertAll();   // MANDATORY: throws all collected failures
```

Key rule: always call `softAssert.assertAll()` at the end of the test.
Without it, the test passes even when assertions fail.

---

## WHERE TO RUN THESE COMMANDS

Open the Terminal tab inside IntelliJ (shortcut: `Option + F12` on Mac).
Make sure you are in your project folder. You should see:

```
sriramkothapalli@MacBook Selenium-JAVA-practise %
```

---

## COMMAND 1 — Run All Tests

```bash
mvn test
```

Reads `testng.xml`, runs every test class listed, generates reports.

Expected output with the current GoogleTest.java:

```
Tests run: 14, Failures: 0, Errors: 0, Skipped: 1
BUILD SUCCESS
```

`Skipped: 1` is expected — `verifyVoiceSearchFeatureTest` throws `SkipException`.
`verifySearchWithDataProviderTest` runs 3 times (once per DataProvider row),
which is why the count is 14 even though there are 12 method names.

---

## COMMAND 2 — Clean Then Run (Recommended)

```bash
mvn clean test
```

Deletes the `target/` folder first, then compiles everything from scratch,
then runs all tests. Always use this before sharing results with your team.
Think of it like clearing the browser cache before testing a website.

---

## COMMAND 3 — Run Smoke Tests Only

```bash
mvn test -Dgroups=smoke
```

Runs only tests tagged with `groups = "smoke"` in your Java file.

Tests that run:
- `verifyPageTitleTest` (groups: smoke, regression)
- `verifyLogoVisibleTest` (groups: smoke)

Total: 2 tests. Fast check — runs on every code commit.

---

## COMMAND 4 — Run Regression Tests Only

```bash
mvn test -Dgroups=regression
```

Tests that run:
- `verifyPageTitleTest`
- `verifySearchFunctionalityTest`
- `verifyAllAssertTypesTest`
- `verifyPageElementsWithSoftAssertTest`
- `verifySearchResultsUrlTest`
- `verifySearchWithDataProviderTest` (runs 3 times via DataProvider)
- `verifyExpectedExceptionTest`

Runs nightly or after a developer merges a feature branch.

---

## COMMAND 5 — Run Sanity Tests Only

```bash
mvn test -Dgroups=sanity
```

Tests that run:
- `verifyPageElementsWithSoftAssertTest` (groups: regression, sanity)
- `verifySearchBoxAttributesTest` (groups: sanity)

Run after a specific bug fix to confirm that fix works without
running the entire regression suite.

---

## COMMAND 6 — Run E2E Tests Only

```bash
mvn test -Dgroups=e2e
```

Tests that run:
- `verifyFullSearchJourneyTest`
- `verifyPageLoadWithinTimeoutTest`
- `verifyVoiceSearchFeatureTest` (will be SKIPPED via SkipException)

Run before production releases to validate complete user workflows.

---

## COMMAND 7 — Run Multiple Groups Together

```bash
mvn test -Dgroups="smoke,regression"
```

```bash
mvn test -Dgroups="regression,e2e"
```

```bash
mvn test -Dgroups="smoke,regression,sanity,e2e"
```

TestNG combines all tests from each listed group and deduplicates.
A test tagged with both smoke and regression appears only once.

---

## COMMAND 8 — Run One Specific Class

```bash
mvn -Dtest=GoogleTest test
```

Ignores `testng.xml` entirely and runs only `GoogleTest.java`.
All `@Test` methods inside run in priority order.

---

## COMMAND 9 — Run One Specific Method

```bash
mvn -Dtest=GoogleTest#verifyPageTitleTest test
```

```bash
mvn -Dtest=GoogleTest#verifySearchFunctionalityTest test
```

```bash
mvn -Dtest=GoogleTest#verifyFullSearchJourneyTest test
```

Runs only that one method. Nothing else runs at all.
Pattern: `ClassName#methodName`

---

## COMMAND 10 — Run a Specific Suite File

```bash
mvn test -DsuiteXmlFile=testng.xml
```

Useful when you have multiple XML files (smoke.xml, regression.xml, etc.)
and want to specify which one to use at runtime.

---

## COMMAND 11 — Skip Tests, Compile Only

```bash
mvn clean install -DskipTests
```

Compiles all code but does not run any tests.
Use in CI/CD pipelines when tests run as a separate job.

---

## COMMAND 12 — Verbose Debug Output

```bash
mvn test -Dverbose=5
```

Verbose levels:

```
0 : silent
1 : test names only
2 : test names and pass/fail (recommended default in testng.xml)
5 : everything including internal TestNG steps and thread info
```

Use this when tests are behaving unexpectedly and you need to trace
exactly what TestNG is doing internally.

---

## VIEWING REPORTS AFTER mvn test

Two reports are generated in `target/surefire-reports/` after every run.

### Open the human-readable HTML report

```bash
open target/surefire-reports/emailable-report.html
```

Shows: test names, pass/fail/skip status, duration, failure messages.
Share this file with your manager or team after a test run.

### The machine-readable XML report (for CI/CD tools)

```
target/surefire-reports/testng-results.xml
```

Jenkins, GitHub Actions, GitLab CI, and Allure Report all read this file
to display test history, trend charts, and failure analysis dashboards.

---

## CI/CD PIPELINE STANDARD COMMANDS

```bash
# Every commit and Pull Request — must be fast
mvn test -Dgroups=smoke

# Every night at midnight — full functional coverage
mvn clean test -Dgroups=regression

# Before every production release — complete validation
mvn clean test -Dgroups="smoke,regression,sanity,e2e"
```

---

## TESTNG FEATURES QUICK REFERENCE

| Feature             | How to Use                                          | Purpose                                  |
|---------------------|-----------------------------------------------------|------------------------------------------|
| priority            | `@Test(priority = 1)`                               | Controls execution order in class        |
| groups              | `@Test(groups = {"smoke"})`                         | Filters tests from terminal or XML       |
| description         | `@Test(description = "what this tests")`            | Documents test in HTML reports           |
| timeOut             | `@Test(timeOut = 15000)`                            | Fails test if it takes too long          |
| enabled             | `@Test(enabled = false)`                            | Disables test completely from reports    |
| dependsOnMethods    | `@Test(dependsOnMethods = {"loginTest"})`           | Skips test if dependency failed          |
| dataProvider        | `@Test(dataProvider = "myData")`                    | Runs test multiple times with data       |
| expectedExceptions  | `@Test(expectedExceptions = {IOException.class})`   | Passes when test throws this exception   |
| alwaysRun           | `@BeforeMethod(alwaysRun = true)`                   | Runs even if dependent test failed       |
| SkipException       | `throw new SkipException("reason")`                 | Marks test as SKIPPED with a reason      |
| SoftAssert          | `softAssert.assertTrue(...)` then `assertAll()`     | Collects all failures before reporting   |

---

## KEY RULES TO REMEMBER

**Priority** is set only in Java code. You cannot change execution order from the terminal.

**Groups** are controlled from the terminal with `-Dgroups=` or from `testng.xml`.

**SoftAssert** requires `assertAll()` at the end of every test that uses it.
Without `assertAll()`, the test always passes regardless of failures.

**`mvn clean test`** should always be used before sharing results.
Plain `mvn test` can reuse stale compiled files from a previous run.

**`Skipped: 1`** in the results is expected. `verifyVoiceSearchFeatureTest`
throws `SkipException` intentionally. Skipped is yellow in reports, not red.

**DataProvider** causes the test count to be higher than the method count.
`verifySearchWithDataProviderTest` runs 3 times, one per data row.