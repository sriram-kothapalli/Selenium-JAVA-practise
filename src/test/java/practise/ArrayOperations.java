// =====================================================
// IMPORT STATEMENT
// =====================================================

import java.util.Arrays;

/*
IMPORT BREAKDOWN:

import → keyword used to bring external classes into current file

java.util → package name
- java → root package
- util → utility package (contains helper classes)

Arrays → class inside java.util package
- used for array operations like:
  → converting array to string
  → sorting arrays
  → searching arrays

WHY IMPORT IS NEEDED:
- Arrays class is NOT part of default java.lang package
- So we must import it manually to use Arrays.toString()
*/


// =====================================================
// CLASS DEFINITION
// =====================================================

public class ArrayOperations {

    /*
    CLASS BREAKDOWN:

    public → access modifier (accessible everywhere)
    class → keyword to define blueprint
    ArrayOperations → class name

    PURPOSE:
    This class performs array operations like:
    - access
    - update
    - delete
    - push
    */


    // =====================================================
    // ARRAY DECLARATION
    // =====================================================

    int[] arr = {10, 20, 30, 40, 50};

    /*
    BREAKDOWN:

    int[] → array of integers (non-primitive type)
    arr → variable name (reference to array object)
    {10,20,30,40,50} → stored values

    IMPORTANT:
    Arrays are FIXED SIZE in Java
    */


    // =====================================================
    // METHOD 1: ACCESS ELEMENT
    // =====================================================

    public void accessElement(int index) {

        /*
        METHOD BREAKDOWN:

        public → accessible everywhere
        void → returns nothing
        accessElement → method name

        PARAMETER:
        int index → position in array (starts from 0)
        */

        System.out.println("Value at index " + index + " = " + arr[index]);

        /*
        arr[index] → fetch value from array position

        EXAMPLE:
        arr = {10,20,30,40,50}
        index = 2 → output = 30
        */
    }


    // =====================================================
    // METHOD 2: UPDATE ELEMENT
    // =====================================================

    public void updateElement(int index, int value) {

        /*
        BREAKDOWN:

        index → position to update
        value → new value to store
        arr[index] = value → replaces old value
        */

        arr[index] = value;

        System.out.println("Array after update: " + Arrays.toString(arr));

        /*
        Arrays.toString(arr) →
        converts array into readable format
        */
    }


    // =====================================================
    // METHOD 3: DELETE ELEMENT (SIMULATION)
    // =====================================================

    public void deleteElement(int index) {

        /*
        IMPORTANT:
        Arrays are FIXED SIZE → cannot delete directly

        SOLUTION:
        - create new array (size - 1)
        - copy all except index
        */

        int[] newArr = new int[arr.length - 1];

        /*
        newArr → new array
        arr.length - 1 → reduced size
        */

        for (int i = 0, j = 0; i < arr.length; i++) {

            /*
            i → original array index
            j → new array index
            */

            if (i != index) {
                newArr[j] = arr[i];
                j++;
            }
        }

        arr = newArr;

        System.out.println("After delete: " + Arrays.toString(arr));
    }


    // =====================================================
    // METHOD 4: PUSH ELEMENT
    // =====================================================

    public void pushElement(int value) {

        /*
        PUSH MEANING:
        Add element at end of array

        ISSUE:
        Arrays are fixed size → cannot directly add

        SOLUTION:
        - create new array (+1 size)
        - copy old values
        - add new value at last
        */

        int[] newArr = new int[arr.length + 1];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }

        newArr[newArr.length - 1] = value;

        arr = newArr;

        System.out.println("After push: " + Arrays.toString(arr));
    }


    // =====================================================
    // METHOD 5: DISPLAY ARRAY
    // =====================================================

    public void display() {

        /*
        Arrays.toString(arr) →
        converts array into readable format
        */

        System.out.println("Current Array: " + Arrays.toString(arr));
    }


    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(String[] args) {

        /*
        MAIN METHOD BREAKDOWN:

        public → accessible by JVM
        static → no object required
        void → no return value
        main → program entry point
        String[] args → command line arguments
        */

        ArrayOperations obj = new ArrayOperations();

        /*
        obj → object creation
        new → allocates memory in heap
        */

        obj.display();
        obj.accessElement(2);
        obj.updateElement(1, 99);
        obj.deleteElement(3);
        obj.pushElement(100);
    }
}