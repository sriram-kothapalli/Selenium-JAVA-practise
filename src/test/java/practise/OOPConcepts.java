package practise;

/*
====================================================
JAVA OOP CONCEPTS (COMPLETE GUIDE WITH FULL BREAKDOWN)
====================================================
Covers:
1. Inheritance
2. Polymorphism
3. Encapsulation
4. Abstraction
====================================================
*/


// =====================================================
// 1. INHERITANCE (IS-A RELATIONSHIP)
// =====================================================

class Parent {

    /*
    CLASS BREAKDOWN:

    class → blueprint keyword
    Parent → class name

    PURPOSE:
    - provides common functionality to child classes
    */

    void show() {

        /*
        METHOD BREAKDOWN:

        void → no return value
        show → method name

        PURPOSE:
        prints parent behavior
        */

        System.out.println("This is Parent class method");
    }
}


class Child extends Parent {

    /*
    INHERITANCE BREAKDOWN:

    extends → keyword used for inheritance
    Child → subclass (child class)
    Parent → superclass (parent class)

    MEANING:
    Child IS-A Parent
    */

    void display() {

        /*
        METHOD BREAKDOWN:
        void → no return value
        display → child method
        */

        System.out.println("This is Child class method");
    }
}


// =====================================================
// 2. POLYMORPHISM (ONE NAME MANY FORMS)
// =====================================================

class Animal {

    /*
    CLASS BREAKDOWN:
    Animal → base class

    PURPOSE:
    defines common behavior
    */

    void sound() {
        System.out.println("Animal makes sound");
    }
}


class Dog extends Animal {

    /*
    METHOD OVERRIDING BREAKDOWN:

    @Override → ensures method is overridden correctly
    sound() → same method name as parent

    THIS IS:
    Runtime Polymorphism
    */

    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}


// =====================================================
// 3. ENCAPSULATION (DATA HIDING)
// =====================================================

class BankAccount {

    /*
    ENCAPSULATION BREAKDOWN:

    private → restrict direct access
    getter/setter → controlled access methods

    PURPOSE:
    - protect data
    - control modification
    */

    private double balance = 1000;

    /*
    private variable:
    - cannot be accessed outside class directly
    */


    public double getBalance() {

        /*
        GETTER BREAKDOWN:

        public → accessible outside class
        getBalance → method name
        returns balance value
        */

        return balance;
    }


    public void setBalance(double balance) {

        /*
        SETTER BREAKDOWN:

        void → no return value
        setBalance → method name
        this.balance → current object variable
        */

        this.balance = balance;
    }
}


// =====================================================
// 4. ABSTRACTION (HIDING IMPLEMENTATION)
// =====================================================

abstract class Shape {

    /*
    ABSTRACT CLASS BREAKDOWN:

    abstract → cannot create object directly
    Shape → class name

    PURPOSE:
    - hides implementation details
    - shows only essential behavior
    */


    abstract void draw();

    /*
    ABSTRACT METHOD BREAKDOWN:

    abstract → no body (no implementation)
    must be implemented in child class
    */
}


class Circle extends Shape {

    /*
    IMPLEMENTATION CLASS BREAKDOWN:

    Circle → concrete class
    Shape → abstract class

    PURPOSE:
    provides implementation of abstract method
    */


    void draw() {
        System.out.println("Drawing Circle");
    }
}


// =====================================================
// MAIN CLASS
// =====================================================

public class OOPConcepts {

    public static void main(String[] args) {

        /*
        MAIN METHOD BREAKDOWN:

        public → JVM access
        static → no object needed
        void → no return value
        main → execution start point
        String[] args → command line arguments
        */


        // =====================================================
        // 1. INHERITANCE OBJECT EXAMPLE
        // =====================================================

        Child c = new Child();

        /*
        OBJECT BREAKDOWN:

        Child → class name
        c → object reference
        new → memory allocation in heap
        Child() → constructor call
        */

        c.show();     // Parent method
        c.display();  // Child method


        // =====================================================
        // 2. POLYMORPHISM EXAMPLE
        // =====================================================

        Animal a = new Dog();

        /*
        POLYMORPHISM BREAKDOWN:

        Animal → reference type (parent)
        Dog → actual object type (child)

        CONCEPT:
        - same method name
        - different behavior at runtime
        */

        a.sound();


        // =====================================================
        // 3. ENCAPSULATION EXAMPLE
        // =====================================================

        BankAccount acc = new BankAccount();

        /*
        OBJECT BREAKDOWN:
        acc → object reference
        */

        System.out.println(acc.getBalance());

        /*
        getter → accessing private variable safely
        */

        acc.setBalance(2000);

        /*
        setter → modifying private variable safely
        */

        System.out.println(acc.getBalance());


        // =====================================================
        // 4. ABSTRACTION EXAMPLE
        // =====================================================

        Shape s = new Circle();

        /*
        ABSTRACTION BREAKDOWN:

        Shape → abstract reference
        Circle → concrete implementation
        */

        s.draw();
    }
}