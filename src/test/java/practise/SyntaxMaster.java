package practise;

/*
====================================================
JAVA SYNTAX MASTER GUIDE (ALL IN ONE CLASS)
====================================================
Covers:
1. Class
2. Main method
3. Variables
4. Methods
5. If-Else
6. Switch
7. Loops
8. Arrays
9. Objects
10. Interface concept (explained inside)
====================================================
*/

public class SyntaxMaster {

    /*
    ====================================================
    1. CLASS BREAKDOWN
    ====================================================

    class → blueprint keyword
    SyntaxMaster → class name

    NOTE:
    This is the MAIN class (entry container)
    */


    // =====================================================
    // 2. VARIABLES
    // =====================================================

    int age = 25;
    String name = "Java";

    /*
    VARIABLE BREAKDOWN:

    int → whole number type
    age → variable name
    25 → value

    String → text type
    name → variable name
    "Java" → value
    */


    // =====================================================
    // 3. METHOD EXAMPLE
    // =====================================================

    void show() {

        /*
        METHOD BREAKDOWN:

        void → no return value
        show → method name
        */

        System.out.println("Method called");
    }


    int add(int a, int b) {

        /*
        METHOD BREAKDOWN:

        int → return type
        add → method name
        a, b → parameters
        */

        return a + b;
    }


    // =====================================================
    // 4. IF-ELSE
    // =====================================================

    void checkAge(int age) {

        if (age >= 18) {

            /*
            if → condition check
            */
            System.out.println("Eligible");
        }
        else {
            System.out.println("Not Eligible");
        }
    }


    // =====================================================
    // 5. SWITCH
    // =====================================================

    void checkDay(int day) {

        switch (day) {

            case 1:
                System.out.println("Monday");
                break;

            case 2:
                System.out.println("Tuesday");
                break;

            default:
                System.out.println("Invalid Day");
        }
    }


    // =====================================================
    // 6. LOOPS
    // =====================================================

    void loops() {

        // FOR LOOP
        for (int i = 0; i < 3; i++) {

            /*
            i = 0 → start
            i < 3 → condition
            i++ → increment
            */

            System.out.println("For Loop: " + i);
        }


        // WHILE LOOP
        int j = 0;

        while (j < 3) {

            System.out.println("While Loop: " + j);
            j++;
        }


        // DO-WHILE LOOP
        int k = 0;

        do {
            System.out.println("Do While: " + k);
            k++;
        } while (k < 3);
    }


    // =====================================================
    // 7. ARRAY
    // =====================================================

    int[] arr = {10, 20, 30};

    /*
    ARRAY BREAKDOWN:

    int[] → array type
    arr → variable name
    {10,20,30} → values
    */

    void arrayExample() {

        System.out.println(arr[0]); // access first element
    }


    // =====================================================
    // 8. OBJECT CONCEPT (SELF OBJECT)
    // =====================================================

    void objectExample() {

        SyntaxMaster obj = new SyntaxMaster();

        /*
        OBJECT BREAKDOWN:

        SyntaxMaster → class name
        obj → object reference
        new → memory allocation
        */

        obj.show();
    }


    // =====================================================
    // 9. INTERFACE CONCEPT (EXPLAINED)
    // =====================================================

    /*
    INTERFACE NOTE:

    interface → blueprint for rules
    cannot be used directly inside same class file execution
    used for abstraction

    Example:
    interface Demo {
        void show();
    }
    */


    // =====================================================
    // 10. MAIN METHOD (ENTRY POINT)
    // =====================================================

    public static void main(String[] args) {

        /*
        MAIN METHOD BREAKDOWN:

        public → JVM access
        static → no object needed
        void → no return
        main → program start
        String[] args → inputs
        */


        SyntaxMaster obj = new SyntaxMaster();

        obj.show();

        System.out.println("Add = " + obj.add(10, 20));

        obj.checkAge(20);

        obj.checkDay(2);

        obj.loops();

        obj.arrayExample();

        obj.objectExample();
    }
}