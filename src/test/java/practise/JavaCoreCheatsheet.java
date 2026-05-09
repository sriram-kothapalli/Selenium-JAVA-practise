package practise;

import java.util.*;
import java.util.Hashtable;

/*
====================================================
JAVA COMPLETE CORE CHEATSHEET FOR TESTERS (SDET)
====================================================
COVERS:
✔ Variables, Methods, OOP
✔ Constructors
✔ Static vs Non-static
✔ Arrays, Strings
✔ StringBuilder / StringBuffer
✔ Wrapper Classes
✔ Type Casting
✔ Collections (List, Set, Map, Queue)
✔ Hashtable / HashMap
✔ Exception Handling
✔ equals vs ==
✔ final keyword
✔ loops
✔ common automation logic
====================================================
*/


// =====================================================
// MAIN CLASS
// =====================================================
public class JavaCoreCheatsheet {

    // =====================================================
    // 1. VARIABLES (TEST DATA STORAGE)
    // =====================================================

    int id = 101;
    String name = "Tester";
    boolean isActive = true;

    /*
    BREAKDOWN:

    int → numeric test data
    String → UI / API data
    boolean → validation result
    */


    // =====================================================
    // 2. STATIC vs NON-STATIC
    // =====================================================

    static String company = "Infosys";

    /*
    STATIC:
    - belongs to class
    - shared across all objects
    */

    void showInstance() {

        /*
        NON-STATIC:
        - belongs to object
        */

        System.out.println("Instance method");
    }


    // =====================================================
    // 3. CONSTRUCTOR (VERY IMPORTANT)
    // =====================================================

    JavaCoreCheatsheet() {

        /*
        CONSTRUCTOR BREAKDOWN:

        same name as class
        no return type
        runs automatically on object creation
        */

        System.out.println("Constructor Called");
    }


    // =====================================================
    // 4. METHOD OVERLOADING (POLYMORPHISM)
    // =====================================================

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }


    // =====================================================
    // 5. STRING OPERATIONS
    // =====================================================

    void stringDemo() {

        String s = "Java";

        System.out.println(s.length());
        System.out.println(s.charAt(1));
        System.out.println(s.contains("av"));

        /*
        STRING METHODS:
        length() → size
        charAt() → index access
        contains() → check substring
        */
    }


    // =====================================================
    // 6. STRINGBUILDER vs STRINGBUFFER
    // =====================================================

    void stringMutableDemo() {

        StringBuilder sb = new StringBuilder("Java");
        sb.append("Code");

        /*
        StringBuilder → faster, NOT thread-safe
        StringBuffer → slower, thread-safe
        */

        System.out.println(sb);
    }


    // =====================================================
    // 7. ARRAY OPERATIONS
    // =====================================================

    int[] arr = {10, 20, 30};

    void arrayDemo() {

        System.out.println(arr[0]);
        System.out.println(arr.length);

        /*
        array:
        fixed size
        index starts from 0
        */
    }


    // =====================================================
    // 8. WRAPPER CLASSES
    // =====================================================

    void wrapperDemo() {

        Integer a = 10;
        Double b = 10.5;
        Boolean c = true;

        /*
        WRAPPER CLASSES:

        int → Integer
        double → Double
        boolean → Boolean

        USED IN:
        Collections (only objects allowed)
        */
    }


    // =====================================================
    // 9. TYPE CASTING
    // =====================================================

    void castingDemo() {

        int a = 10;
        double b = a; // implicit casting

        double x = 10.5;
        int y = (int) x; // explicit casting

        /*
        IMPLICIT → small to large
        EXPLICIT → large to small
        */
    }


    // =====================================================
    // 10. COLLECTIONS
    // =====================================================

    void collectionsDemo() {

        // LIST
        List<String> list = new ArrayList<>();
        list.add("Selenium");
        list.add("Java");

        // SET (no duplicates)
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);

        // MAP (key-value)
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Chrome");

        // HASHTABLE
        Hashtable<Integer, String> ht = new Hashtable<>();
        ht.put(1, "Edge");

        /*
        COLLECTION SUMMARY:

        List → ordered, allows duplicates
        Set → no duplicates
        Map → key-value
        Hashtable → synchronized map
        */
    }


    // =====================================================
    // 11. EXCEPTION HANDLING
    // =====================================================

    void exceptionDemo() {

        try {

            int a = 10 / 0;

        } catch (Exception e) {

            System.out.println("Handled Exception");
        }

        /*
        try → risky code
        catch → handles error
        */
    }


    // =====================================================
    // 12. FINAL KEYWORD
    // =====================================================

    final int MAX = 100;

    /*
    FINAL:

    variable → cannot change
    method → cannot override
    class → cannot inherit
    */


    // =====================================================
    // 13. EQUALS vs ==
    // =====================================================

    void compareDemo() {

        String a = "Java";
        String b = "Java";

        System.out.println(a == b);        // reference compare
        System.out.println(a.equals(b));   // value compare

        /*
        == → memory reference
        equals() → content comparison
        */
    }


    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        JavaCoreCheatsheet obj = new JavaCoreCheatsheet();

        obj.showInstance();
        obj.stringDemo();
        obj.stringMutableDemo();
        obj.arrayDemo();
        obj.wrapperDemo();
        obj.castingDemo();
        obj.collectionsDemo();
        obj.exceptionDemo();
        obj.compareDemo();

        System.out.println(company);
    }
}