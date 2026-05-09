// =====================================================
// CLASS (BLUEPRINT)
// =====================================================
class Student {
    String name = "John";
    int age = 20;
}

/*
CLASS BREAKDOWN:

class → keyword used to create blueprint
Student → class name (custom user-defined type)

VARIABLES INSIDE CLASS:
name → stores student name (String type)
age  → stores student age (int type)

IMPORTANT CONCEPT:
- Class is only a blueprint
- It does NOT consume memory until object is created
- It defines structure of objects
*/


// =====================================================
// MAIN CLASS
// =====================================================
public class DataTypes {

    /*
    CLASS BREAKDOWN:

    public → access modifier (accessible everywhere)
    class → keyword to define class
    DataTypes → class name

    PURPOSE:
    This class demonstrates:
    - Primitive data types
    - Non-primitive data types
    - Arrays
    - Objects
    */


    // =====================================================
    // PRIMITIVE DATA TYPES
    // =====================================================

    int count = 100;

    /*
    BREAKDOWN:
    int → primitive data type (whole number)
    count → variable name
    100 → value stored in memory
    */


    int age = 25;

    /*
    int → stores integer values
    age → variable name
    25 → value
    */


    double price = 49.99;

    /*
    double → decimal number type (high precision)
    price → variable name
    49.99 → value
    */


    double pi = 3.14159;

    /*
    double → decimal type
    pi → variable name
    3.14159 → value (mathematical constant)
    */


    boolean isActive = true;

    /*
    boolean → logical type
    isActive → variable name
    true → value (true/false only)
    */


    boolean isLoggedIn = false;

    /*
    false → condition not satisfied
    */


    char grade = 'A';

    /*
    char → single character type
    grade → variable name
    'A' → value (single quotes required)
    */


    char symbol = '@';

    /*
    stores special character
    */


    long population = 15000000000L;

    /*
    long → large number type
    L → mandatory suffix for long values
    */


    float tax = 0.05f;

    /*
    float → small decimal type
    f → mandatory suffix
    */


    // =====================================================
    // NON-PRIMITIVE DATA TYPES
    // =====================================================

    String school = "Tech Academy";

    /*
    String → class type (non-primitive)
    school → variable name
    "Tech Academy" → text value
    */


    String city = "Hyderabad";

    /*
    stores string data (text)
    */


    int[] grades = {95, 88, 72, 60};

    /*
    int[] → array of integers
    grades → variable name
    {95,88,72,60} → stored values

    IMPORTANT:
    Arrays start from index 0
    */


    int[] numbers = {10, 20, 30, 40};

    /*
    array stores multiple values of same type
    */


    Student myStudent = new Student();

    /*
    Student → class name
    myStudent → object reference variable
    new → keyword to allocate memory in heap
    Student() → constructor call

    RESULT:
    Object of Student class is created in memory
    */


    // =====================================================
    // MAIN METHOD (ENTRY POINT)
    // =====================================================
    public static void main(String[] args) {

        /*
        MAIN METHOD BREAKDOWN:

        public → accessible by JVM
        static → no object required to call main
        void → returns nothing
        main → program entry point
        String[] args → command line arguments
        */


        DataTypes obj = new DataTypes();

        /*
        obj → object reference of DataTypes class
        new DataTypes() → creates object in heap memory

        PURPOSE:
        Used to access non-static variables and methods
        */


        // =====================================================
        // PRIMITIVE OUTPUTS
        // =====================================================

        System.out.println(obj.count);

        /*
        System → built-in class
        out → output stream
        println → prints and moves to next line

        obj.count → accessing variable using object
        */


        System.out.println(obj.age);
        System.out.println(obj.price);
        System.out.println(obj.pi);
        System.out.println(obj.isActive);
        System.out.println(obj.grade);
        System.out.println(obj.population);
        System.out.println(obj.tax);


        // =====================================================
        // STRING OUTPUTS
        // =====================================================

        System.out.println(obj.school);
        System.out.println(obj.city);


        // =====================================================
        // ARRAY ACCESS
        // =====================================================

        System.out.println(obj.grades[0]);

        /*
        grades[0] → first element (index starts at 0)
        output: 95
        */


        System.out.println(obj.grades[2]);

        /*
        output: 72
        */


        System.out.println(obj.numbers[1]);

        /*
        output: 20
        */


        // =====================================================
        // ARRAY LOOP
        // =====================================================

        for (int i = 0; i < obj.grades.length; i++) {

            /*
            i → loop counter
            length → total size of array
            */

            System.out.println(obj.grades[i]);
        }


        // =====================================================
        // OBJECT ACCESS
        // =====================================================

        System.out.println(obj.myStudent.name);

        /*
        obj → DataTypes object
        myStudent → Student object inside DataTypes
        name → variable inside Student class
        */


        System.out.println(obj.myStudent.age);


        // UPDATE OBJECT VALUES
        obj.myStudent.name = "Sriram";
        obj.myStudent.age = 22;

        /*
        updating object values at runtime
        */


        System.out.println(obj.myStudent.name);
        System.out.println(obj.myStudent.age);
    }
}