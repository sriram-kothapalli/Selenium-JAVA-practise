package practise;

/*
====================================================
JAVA LOOPS (COMPLETE GUIDE WITH BREAKDOWN + EXAMPLES)
====================================================
*/

public class LoopsDemo {

    public static void main(String[] args) {

        /*
        MAIN METHOD BREAKDOWN:

        public → accessible by JVM
        static → no object needed
        void → returns nothing
        main → program entry point
        String[] args → command line arguments
        */


        // =====================================================
        // 1. FOR LOOP
        // =====================================================

        for (int i = 0; i < 5; i++) {

            /*
            FOR LOOP BREAKDOWN:

            for → keyword for loop

            (initialization; condition; increment)

            int i = 0 → start value
            i < 5 → condition (loop runs until false)
            i++ → increment (i = i + 1)

            PURPOSE:
            - used when number of iterations is known
            */

            System.out.println("For Loop i = " + i);
        }


        // =====================================================
        // 2. WHILE LOOP
        // =====================================================

        int j = 0;

        /*
        BREAKDOWN:
        int j = 0 → initialization outside loop
        */

        while (j < 5) {

            /*
            WHILE LOOP BREAKDOWN:

            while → checks condition first
            (j < 5) → condition

            WORKING:
            - runs until condition becomes false
            */

            System.out.println("While Loop j = " + j);
            j++;

            /*
            j++ → increment to avoid infinite loop
            */
        }


        // =====================================================
        // 3. DO-WHILE LOOP
        // =====================================================

        int k = 0;

        do {

            /*
            DO-WHILE BREAKDOWN:

            do → executes block first
            while → checks condition later

            SPECIAL FEATURE:
            - runs at least ONCE
            */

            System.out.println("Do While k = " + k);
            k++;

        } while (k < 5);


        // =====================================================
        // 4. ENHANCED FOR LOOP (FOR-EACH LOOP)
        // =====================================================

        int[] numbers = {10, 20, 30, 40, 50};

        /*
        BREAKDOWN:
        int[] → array of integers
        numbers → variable name
        */

        for (int num : numbers) {

            /*
            FOR-EACH BREAKDOWN:

            int num → temporary variable
            : → reads each element
            numbers → array source

            PURPOSE:
            - used for arrays and collections
            - no index required
            */

            System.out.println("Value = " + num);
        }


        // =====================================================
        // 5. LOOP WITH BREAK
        // =====================================================

        for (int i = 0; i < 10; i++) {

            if (i == 5) {

                /*
                BREAK BREAKDOWN:
                break → exits loop completely
                */

                break;
            }

            System.out.println("Break Loop i = " + i);
        }


        // =====================================================
        // 6. LOOP WITH CONTINUE
        // =====================================================

        for (int i = 0; i < 5; i++) {

            if (i == 2) {

                /*
                CONTINUE BREAKDOWN:
                continue → skips current iteration
                */

                continue;
            }

            System.out.println("Continue Loop i = " + i);
        }
    }
}