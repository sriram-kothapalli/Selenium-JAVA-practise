package practise;

/*
====================================================
JAVA METHODS (COMPLETE GUIDE WITH BREAKDOWN + EXAMPLES)
====================================================
*/

public class MethodsDemo {

    /*
    CLASS BREAKDOWN:

    public → accessible everywhere
    class → blueprint keyword
    MethodsDemo → class name

    PURPOSE:
    Demonstrates all types of methods in Java
    */


    // =====================================================
    // 1. METHOD WITH NO RETURN TYPE (VOID METHOD)
    // =====================================================

    void showMessage() {

        /*
        METHOD BREAKDOWN:

        void → returns nothing
        showMessage → method name

        PURPOSE:
        - performs action only (print/log)
        */

        System.out.println("Hello from void method");
    }


    // =====================================================
    // 2. METHOD WITH RETURN TYPE
    // =====================================================

    int add(int a, int b) {

        /*
        METHOD BREAKDOWN:

        int → return type (returns integer)
        add → method name

        PARAMETERS:
        a → first input
        b → second input
        */

        return a + b;

        /*
        return → sends result back to caller
        */
    }


    // =====================================================
    // 3. METHOD WITHOUT PARAMETERS
    // =====================================================

    void greet() {

        /*
        BREAKDOWN:

        no parameters → no input required
        void → no return value
        */

        System.out.println("Good Morning");
    }


    // =====================================================
    // 4. METHOD WITH PARAMETERS
    // =====================================================

    void printName(String name) {

        /*
        BREAKDOWN:

        String name → input parameter

        name → variable used inside method
        */

        System.out.println("Name = " + name);
    }


    // =====================================================
    // 5. STATIC METHOD
    // =====================================================

    static void staticMethodExample() {

        /*
        STATIC BREAKDOWN:

        static → belongs to class, not object

        FEATURES:
        - called without creating object
        */

        System.out.println("Static Method Called");
    }


    // =====================================================
    // 6. METHOD OVERLOADING (COMPILE TIME POLYMORPHISM)
    // =====================================================

    int multiply(int a, int b) {
        return a * b;
    }

    double multiply(double a, double b) {
        return a * b;
    }

    /*
    METHOD OVERLOADING BREAKDOWN:

    - same method name
    - different parameters
    - compile-time polymorphism
    */


    // =====================================================
    // 7. MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        /*
        MAIN METHOD BREAKDOWN:

        public → accessible by JVM
        static → no object required
        void → returns nothing
        main → entry point
        String[] args → command line arguments
        */


        MethodsDemo obj = new MethodsDemo();

        /*
        obj → object creation
        new → memory allocation
        */


        // VOID METHOD
        obj.showMessage();

        // RETURN METHOD
        int result = obj.add(10, 20);
        System.out.println("Sum = " + result);

        // PARAMETER METHOD
        obj.printName("Sriram");

        // NO PARAMETER METHOD
        obj.greet();

        // STATIC METHOD (called using class name)
        MethodsDemo.staticMethodExample();

        // METHOD OVERLOADING
        System.out.println(obj.multiply(2, 3));
        System.out.println(obj.multiply(2.5, 3.5));
    }
}