package practise;

/*
====================================================
JAVA OBJECTS (COMPLETE GUIDE WITH BREAKDOWN + EXAMPLES)
====================================================
*/

class Student {

    String name = "John";
    int age = 20;

    /*
    BREAKDOWN:
    String → text data type
    name → variable holding student name
    "John" → default value

    int → whole number type
    age → variable holding student age
    20 → default value
    */

    void show() {

        /*
        METHOD BREAKDOWN:
        void → returns nothing
        show → method name
        purpose → prints student details
        */

        System.out.println(name + " " + age);
    }
}


// =====================================================
// MAIN CLASS
// =====================================================
public class ObjectsDemo {

    public static void main(String[] args) {

        /*
        MAIN METHOD BREAKDOWN:

        public → accessible by JVM
        static → no object needed to run main
        void → no return value
        main → execution starting point
        String[] args → command line inputs
        */


        // =====================================================
        // 1. OBJECT CREATION
        // =====================================================

        Student s = new Student();

        /*
        OBJECT BREAKDOWN:

        Student → class name (blueprint)
        s → object reference variable
        = → assignment operator
        new → creates object in heap memory
        Student() → constructor call

        RESULT:
        Object is created in heap memory
        */


        // =====================================================
        // 2. ACCESSING VARIABLES USING OBJECT
        // =====================================================

        System.out.println(s.name);
        System.out.println(s.age);

        /*
        BREAKDOWN:

        s → object reference
        . (dot operator) → used to access class members
        name/age → variables inside Student class
        */


        // =====================================================
        // 3. MODIFYING OBJECT VALUES
        // =====================================================

        s.name = "Sriram";
        s.age = 22;

        /*
        BREAKDOWN:

        - values inside object can be changed at runtime
        - this modifies heap memory data
        */


        // =====================================================
        // 4. CALLING METHOD USING OBJECT
        // =====================================================

        s.show();

        /*
        BREAKDOWN:

        s → object
        show() → method inside Student class

        RESULT:
        prints updated values
        */


        // =====================================================
        // 5. MULTIPLE OBJECTS
        // =====================================================

        Student s1 = new Student();
        Student s2 = new Student();

        /*
        BREAKDOWN:

        - s1 and s2 are different objects
        - each object has separate memory
        */


        s1.name = "A";
        s2.name = "B";

        System.out.println(s1.name); // A
        System.out.println(s2.name); // B


        // =====================================================
        // 6. MEMORY CONCEPT
        // =====================================================

        /*
        STACK MEMORY:
        - stores reference variables (s, s1, s2)

        HEAP MEMORY:
        - stores actual object data (name, age)

        IMPORTANT:
        Each new object = new memory allocation
        */
    }
}