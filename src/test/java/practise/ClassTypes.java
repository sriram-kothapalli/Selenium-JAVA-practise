package practise;

/*
====================================================
JAVA TYPES OF CLASSES (WITH EXPLANATION + EXAMPLES)
====================================================
*/


// =====================================================
// 1. CONCRETE CLASS (NORMAL CLASS)
// =====================================================
class Employee {

    int id = 101;
    String name = "John";

    /*
    BREAKDOWN:

    class → blueprint keyword
    Employee → class name

    VARIABLES:
    id   → stores employee ID
    name → stores employee name

    WHY USED:
    - To create objects
    - To store real data
    */

    void show() {
        System.out.println("Employee: " + id + " " + name);
    }

    /*
    METHOD BREAKDOWN:
    void → returns nothing
    show → method name
    prints employee details
    */
}


// =====================================================
// 2. ABSTRACT CLASS
// =====================================================
abstract class Vehicle {

    /*
    BREAKDOWN:

    abstract → keyword used for incomplete class
    Vehicle → class name

    FEATURE:
    - Cannot create object directly
    - Used as base class
    */

    abstract void run();

    /*
    ABSTRACT METHOD:
    - No body (no implementation)
    - Must be implemented in child class
    */

    void fuel() {
        System.out.println("Vehicle needs fuel");
    }

    /*
    NORMAL METHOD:
    - Has implementation
    - Can be reused
    */
}


// =====================================================
// 3. FINAL CLASS
// =====================================================
final class Bank {

    /*
    BREAKDOWN:

    final → prevents inheritance
    Bank → class name

    MEANING:
    - Cannot be extended by other classes
    - Used for security / fixed behavior
    */

    void show() {
        System.out.println("Bank details");
    }
}


// =====================================================
// MAIN CLASS (ENTRY POINT)
// =====================================================
public class ClassTypes {

    // =====================================================
    // 4. STATIC NESTED CLASS
    // =====================================================
    static class StaticInner {

        /*
        BREAKDOWN:

        static → belongs to class, not object
        inner class → class inside another class

        FEATURE:
        - Can be accessed without outer class object
        */

        void display() {
            System.out.println("Static Inner Class Example");
        }
    }


    // =====================================================
    // 5. INNER CLASS (NON-STATIC)
// =====================================================
    class Inner {

        /*
        BREAKDOWN:

        Inner class → depends on outer class object
        */

        void show() {
            System.out.println("Inner Class Example");
        }
    }


    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {

        /*
        MAIN METHOD BREAKDOWN:

        public → accessible everywhere
        static → no object needed
        void → no return value
        main → program start point
        String[] args → command line inputs
        */


        // =====================================================
        // 1. CONCRETE CLASS OBJECT
        // =====================================================
        Employee emp = new Employee();

        /*
        BREAKDOWN:

        Employee → class name
        emp → object reference variable
        new → memory allocation in heap
        Employee() → constructor call
        */

        emp.show();

        /*
        OUTPUT:
        Employee: 101 John
        */


        // =====================================================
        // 2. ABSTRACT CLASS (USING ANONYMOUS CLASS)
// =====================================================
        Vehicle v = new Vehicle() {

            /*
            BREAKDOWN:

            Cannot create object of abstract class directly
            So we use anonymous class (temporary implementation)
            */

            void run() {
                System.out.println("Vehicle is running");
            }
        };

        v.run();
        v.fuel();

        /*
        OUTPUT:
        Vehicle is running
        Vehicle needs fuel
        */


        // =====================================================
        // 3. FINAL CLASS OBJECT
        // =====================================================
        Bank b = new Bank();

        /*
        BREAKDOWN:

        final class → can create object BUT cannot extend it
        */

        b.show();

        /*
        OUTPUT:
        Bank details
        */


        // =====================================================
        // 4. STATIC INNER CLASS OBJECT
        // =====================================================
        ClassTypes.StaticInner si = new ClassTypes.StaticInner();

        /*
        BREAKDOWN:

        StaticInner → nested static class
        no need of outer class object
        */

        si.display();

        /*
        OUTPUT:
        Static Inner Class Example
        */


        // =====================================================
        // 5. INNER CLASS OBJECT
        // =====================================================
        ClassTypes outer = new ClassTypes();
        ClassTypes.Inner in = outer.new Inner();

        /*
        BREAKDOWN:

        Inner class → depends on outer class object
        step 1 → create outer object
        step 2 → create inner object using outer
        */

        in.show();

        /*
        OUTPUT:
        Inner Class Example
        */
    }
}