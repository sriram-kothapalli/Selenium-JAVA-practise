package practise;

/*
====================================================
JAVA FLOW CONTROL KEYWORDS (break, continue, return)
WITH COMPLETE BREAKDOWN + EXAMPLES
====================================================
*/

public class FlowControlDemo {

    // =====================================================
    // 1. BREAK KEYWORD
    // =====================================================

    void breakExample() {

        /*
        BREAKDOWN:

        void → no return value
        breakExample → method name

        PURPOSE:
        Demonstrates break keyword usage
        */

        for (int i = 0; i < 10; i++) {

            /*
            FOR LOOP BREAKDOWN:
            i = 0 → initialization
            i < 10 → condition
            i++ → increment
            */

            if (i == 5) {

                /*
                BREAK KEYWORD:
                - used to STOP loop immediately
                - exits loop fully
                */

                break;
            }

            System.out.println("Break Example i = " + i);
        }
    }


    // =====================================================
    // 2. CONTINUE KEYWORD
    // =====================================================

    void continueExample() {

        /*
        CONTINUE BREAKDOWN:

        continue → skips current iteration only
        loop continues with next value
        */

        for (int i = 0; i < 5; i++) {

            if (i == 2) {

                /*
                CONTINUE:
                - skips only this iteration
                - does NOT stop loop
                */

                continue;
            }

            System.out.println("Continue Example i = " + i);
        }
    }


    // =====================================================
    // 3. RETURN KEYWORD
    // =====================================================

    int returnExample(int a, int b) {

        /*
        RETURN BREAKDOWN:

        int → return type (must match return value)
        returnExample → method name
        (int a, int b) → input parameters

        return → sends value back to caller
        */

        int sum = a + b;

        /*
        sum → local variable storing result
        */

        return sum;

        /*
        RETURN RULE:
        - immediately exits method
        - sends value back
        */
    }


    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        /*
        MAIN METHOD BREAKDOWN:

        public → accessible by JVM
        static → no object needed
        void → no return value
        main → entry point
        String[] args → command line inputs
        */

        FlowControlDemo obj = new FlowControlDemo();

        /*
        obj → object creation
        new → memory allocation in heap
        */


        // =====================================================
        // BREAK DEMO
        // =====================================================

        obj.breakExample();


        // =====================================================
        // CONTINUE DEMO
        // =====================================================

        obj.continueExample();


        // =====================================================
        // RETURN DEMO
        // =====================================================

        int result = obj.returnExample(10, 20);

        /*
        result → stores returned value
        */

        System.out.println("Return Example Result = " + result);
    }
}