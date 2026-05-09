package practise;

/*
====================================================
JAVA SWITCH STATEMENT (COMPLETE GUIDE WITH BREAKDOWN)
====================================================
*/

public class SwitchDemo {

    public static void main(String[] args) {

        /*
        MAIN METHOD BREAKDOWN:

        public → accessible by JVM
        static → no object needed to run
        void → returns nothing
        main → execution starting point
        String[] args → command line inputs
        */


        // =====================================================
        // 1. BASIC SWITCH EXAMPLE
        // =====================================================

        int day = 3;

        /*
        BREAKDOWN:
        int → whole number data type
        day → variable name
        3 → value stored in memory
        */


        switch (day) {

            /*
            SWITCH BREAKDOWN:

            switch → keyword used for multiple condition checking
            (day) → expression to evaluate

            WORKING:
            - compares value of 'day' with cases
            */

            case 1:
                System.out.println("Monday");
                break;

            /*
            CASE BREAKDOWN:
            case 1 → matches value 1

            break → stops execution after match
            */

            case 2:
                System.out.println("Tuesday");
                break;

            case 3:
                System.out.println("Wednesday");
                break;

            case 4:
                System.out.println("Thursday");
                break;

            case 5:
                System.out.println("Friday");
                break;

            case 6:
                System.out.println("Saturday");
                break;

            case 7:
                System.out.println("Sunday");
                break;

            default:
                System.out.println("Invalid day");

            /*
            DEFAULT BREAKDOWN:
            default → executes if no case matches
            */
        }


        // =====================================================
        // 2. SWITCH WITH STRING
        // =====================================================

        String browser = "chrome";

        /*
        BREAKDOWN:
        String → text type
        browser → variable name
        "chrome" → value
        */


        switch (browser) {

            case "chrome":
                System.out.println("Chrome Browser launched");
                break;

            case "firefox":
                System.out.println("Firefox Browser launched");
                break;

            case "edge":
                System.out.println("Edge Browser launched");
                break;

            default:
                System.out.println("Unsupported browser");
        }


        // =====================================================
        // 3. SWITCH FALL-THROUGH CONCEPT
        // =====================================================

        int num = 2;

        switch (num) {

            case 1:
                System.out.println("One");

            case 2:
                System.out.println("Two");

            case 3:
                System.out.println("Three");

            default:
                System.out.println("Done");

            /*
            FALL-THROUGH BREAKDOWN:

            - if break is missing
            - execution continues to next cases
            */
        }


        // =====================================================
        // 4. REAL-TIME QA EXAMPLE
        // =====================================================

        String status = "PASS";

        switch (status) {

            case "PASS":
                System.out.println("Test Passed");
                break;

            case "FAIL":
                System.out.println("Test Failed");
                break;

            case "SKIP":
                System.out.println("Test Skipped");
                break;

            default:
                System.out.println("Unknown Status");
        }
    }
}