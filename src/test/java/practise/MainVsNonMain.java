package practise;

/*
====================================================
MAIN METHOD vs NON-MAIN METHODS (COMPLETE GUIDE)
WITH SYNTAX + DIFFERENCE + BREAKDOWN
====================================================
*/

public class MainVsNonMain {

    /*
    ====================================================
    1. MAIN METHOD (SPECIAL METHOD)
    ====================================================
    */

    public static void main(String[] args) {

        /*
        MAIN METHOD BREAKDOWN:

        public → JVM can access it from anywhere
        static → no object required to run
        void → returns nothing
        main → predefined name (JVM looks for this)
        String[] args → command line arguments

        WHY MAIN IS SPECIAL?
        - It is ENTRY POINT of Java program
        - JVM starts execution from here
        */

        System.out.println("Program Started from Main Method");

        /*
        JVM FLOW:
        1. Finds main method
        2. Starts execution here
        3. Executes line by line
        */


        // CALLING NON-MAIN METHODS USING OBJECT
        MainVsNonMain obj = new MainVsNonMain();

        obj.nonMainMethod();

        int result = obj.add(10, 20);

        System.out.println("Result = " + result);
    }


    /*
    ====================================================
    2. NON-MAIN METHOD (NORMAL METHOD)
    ====================================================
    */

    void nonMainMethod() {

        /*
        NON-MAIN METHOD BREAKDOWN:

        void → no return value
        nonMainMethod → user-defined method name
        () → no parameters

        HOW IT WORKS:
        - JVM does NOT call this automatically
        - Must be called using object
        */

        System.out.println("This is Non-Main Method");
    }


    /*
    ====================================================
    3. NON-MAIN METHOD WITH RETURN TYPE
    ====================================================
    */

    int add(int a, int b) {

        /*
        BREAKDOWN:

        int → return type (returns integer)
        add → method name

        PARAMETERS:
        a → first input
        b → second input

        PURPOSE:
        - performs logic
        - returns result to caller
        */

        return a + b;
    }
}