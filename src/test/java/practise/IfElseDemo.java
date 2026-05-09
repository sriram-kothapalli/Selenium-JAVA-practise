package practise;

/*
====================================================
JAVA IF - ELSE (COMPLETE GUIDE WITH BREAKDOWN + EXAMPLES)
====================================================
*/

public class IfElseDemo {

    public static void main(String[] args) {

        /*
        MAIN METHOD BREAKDOWN:

        public → accessible by JVM
        static → no object needed to run
        void → returns nothing
        main → program entry point
        String[] args → command line inputs
        */


        // =====================================================
        // 1. SIMPLE IF - ELSE
        // =====================================================

        int age = 20;

        /*
        BREAKDOWN:
        int → data type (whole number)
        age → variable name
        20 → value stored in memory
        */

        if (age >= 18) {

            /*
            IF BREAKDOWN:
            if → keyword for condition checking

            condition:
            age >= 18 → checks if age is greater than or equal to 18

            >= → comparison operator
            */

            System.out.println("Eligible to vote");
        }
        else {

            /*
            ELSE BREAKDOWN:
            else → executes when IF condition is false
            */

            System.out.println("Not eligible to vote");
        }


        // =====================================================
        // 2. IF - ELSE IF - ELSE LADDER
        // =====================================================

        int marks = 75;

        /*
        BREAKDOWN:
        marks → variable storing student score
        */

        if (marks >= 90) {

            /*
            CONDITION 1:
            marks >= 90 → Excellent
            */

            System.out.println("Grade A");
        }
        else if (marks >= 75) {

            /*
            ELSE IF BREAKDOWN:
            else if → second condition check

            condition:
            marks >= 75 → Good performance
            */

            System.out.println("Grade B");
        }
        else if (marks >= 50) {

            System.out.println("Grade C");
        }
        else {

            System.out.println("Fail");
        }


        // =====================================================
        // 3. IF WITH BOOLEAN
        // =====================================================

        boolean isLoggedIn = true;

        /*
        BREAKDOWN:
        boolean → true/false type
        isLoggedIn → variable name
        */

        if (isLoggedIn) {

            /*
            if (true) → block executes
            */

            System.out.println("User is logged in");
        }
        else {

            System.out.println("User is not logged in");
        }


        // =====================================================
        // 4. NESTED IF (IF INSIDE IF)
        // =====================================================

        int age2 = 25;
        boolean hasID = true;

        /*
        NESTED IF BREAKDOWN:
        - if inside another if
        - used for multiple conditions
        */

        if (age2 >= 18) {

            if (hasID) {

                System.out.println("Allowed entry");
            }
            else {

                System.out.println("ID required");
            }
        }
        else {

            System.out.println("Not allowed (underage)");
        }


        // =====================================================
        // 5. REAL-TIME STYLE EXAMPLE
        // =====================================================

        int salary = 50000;

        /*
        USE CASE:
        - salary validation
        - bonus logic
        */

        if (salary > 60000) {
            System.out.println("High salary package");
        }
        else if (salary >= 40000) {
            System.out.println("Medium salary package");
        }
        else {
            System.out.println("Low salary package");
        }
    }
}