package practise;

/*
====================================================
12 JAVA INTERVIEW QUESTIONS (CODE SNIPPETS + DEEP BREAKDOWN)
LEVEL: HIGH (INTERVIEW / SDET READY)
====================================================
*/

public class JavaInterviewSnippets {

    // =====================================================
    // 1. VARIABLE + DATA TYPE
    // =====================================================
    public void q1() {

        int a = 10;

        /*
        BREAKDOWN:
        int → primitive data type (whole number)
        a → variable name
        10 → value stored in stack memory

        KEY IDEA:
        Primitive types store actual value
        */

        System.out.println(a);
    }


    // =====================================================
    // 2. STRING IMMUTABILITY
    // =====================================================
    public void q2() {

        String s = "Java";
        s.concat("Code");

        /*
        BREAKDOWN:

        String → immutable class (cannot be changed)
        concat → creates NEW object, does NOT modify original

        MEMORY:
        "Java" stays unchanged
        "JavaCode" is ignored unless reassigned
        */

        System.out.println(s); // Java
    }


    // =====================================================
    // 3. STRINGBUFFER (MUTABLE)
    // =====================================================
    public void q3() {

        StringBuffer sb = new StringBuffer("Java");
        sb.append("Code");

        /*
        BREAKDOWN:

        StringBuffer → mutable class
        append → modifies same object in memory

        RESULT:
        JavaCode
        */

        System.out.println(sb);
    }


    // =====================================================
    // 4. ARRAY ACCESS
    // =====================================================
    public void q4() {

        int[] arr = {10, 20, 30};

        /*
        BREAKDOWN:

        int[] → array type
        arr → reference variable
        index starts from 0
        */

        System.out.println(arr[1]); // 20
    }


    // =====================================================
    // 5. ARRAY INDEX ERROR
    // =====================================================
    public void q5() {

        int[] arr = {1, 2, 3};

        /*
        arr[3] → invalid index
        valid range → 0 to 2

        RESULT:
        ArrayIndexOutOfBoundsException
        */

        // System.out.println(arr[3]);
    }


    // =====================================================
    // 6. METHOD OVERLOADING
    // =====================================================
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    /*
    BREAKDOWN:

    same method name → add
    different parameters → overloading
    compile-time polymorphism
    */


    // =====================================================
    // 7. METHOD OVERRIDING
    // =====================================================
    class Parent {
        void show() {
            System.out.println("Parent");
        }
    }

    class Child extends Parent {
        @Override
        void show() {
            System.out.println("Child");
        }
    }

    /*
    BREAKDOWN:

    same method name + same signature
    runtime polymorphism
    */


    // =====================================================
    // 8. NULL POINTER EXCEPTION
    // =====================================================
    public void q8() {

        String s = null;

        /*
        BREAKDOWN:

        null → no memory reference

        calling method → leads to exception
        */

        // System.out.println(s.length());
    }


    // =====================================================
    // 9. STATIC VARIABLE
    // =====================================================
    static int count = 0;

    /*
    BREAKDOWN:

    static → belongs to class, not object
    shared across all objects
    */


    // =====================================================
    // 10. CONSTRUCTOR
    // =====================================================
    JavaInterviewSnippets() {

        /*
        BREAKDOWN:

        constructor → same name as class
        no return type
        called automatically on object creation
        */

        System.out.println("Constructor Called");
    }


    // =====================================================
    // 11. THIS KEYWORD
    // =====================================================
    int x;

    void setX(int x) {

        /*
        BREAKDOWN:

        this.x → class variable
        x → local parameter

        used to resolve naming conflict
        */

        this.x = x;
    }


    // =====================================================
    // 12. OBJECT CREATION
    // =====================================================
    public static void main(String[] args) {

        /*
        MAIN METHOD BREAKDOWN:

        entry point of execution
        JVM starts here
        */

        JavaInterviewSnippets obj = new JavaInterviewSnippets();

        /*
        BREAKDOWN:

        new → memory allocation in heap
        obj → reference in stack
        constructor automatically called
        */

        obj.q1();
        obj.q2();
        obj.q3();
        obj.q4();
    }
}