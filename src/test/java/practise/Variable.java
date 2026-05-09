package tests;

public class Variable {

    /*
    CLASS BREAKDOWN:

    public → access modifier (accessible everywhere)
    class → keyword to define blueprint
    Variable → class name

    PURPOSE:
    This class demonstrates Java variables and data types
    */


    // =====================================================
    // 1. STRING (TEXT DATA)
    // =====================================================

    String username = "JavaCoder";
    String city = "Hyderabad";
    String language = "Java";

    /*
    STRING BREAKDOWN:

    String → non-primitive data type (class)
    username/city/language → variable names
    "JavaCoder", "Hyderabad", "Java" → string values

    RULE:
    - Must be enclosed in double quotes ""
    - Used for storing text data

    EXAMPLES:
    username → JavaCoder
    city → Hyderabad
    language → Java
    */


    // =====================================================
    // 2. INT (WHOLE NUMBERS)
    // =====================================================

    int level = 10;
    int age = 25;
    int salary = 50000;

    /*
    INT BREAKDOWN:

    int → primitive data type (whole numbers)
    level/age/salary → variable names
    values → numeric (no quotes)

    EXAMPLES:
    level → 10
    age → 25
    salary → 50000
    */


    // =====================================================
    // 3. DOUBLE (DECIMAL NUMBERS)
    // =====================================================

    double price = 19.99;
    double pi = 3.14159;
    double salaryPerDay = 1250.75;

    /*
    DOUBLE BREAKDOWN:

    double → decimal data type (high precision)
    price/pi/salaryPerDay → variable names
    values → decimal numbers

    EXAMPLES:
    price → 19.99
    pi → 3.14159
    salaryPerDay → 1250.75
    */


    // =====================================================
    // 4. BOOLEAN (TRUE / FALSE)
    // =====================================================

    boolean isLogged = true;
    boolean isActive = false;
    boolean isJavaEasy = true;

    /*
    BOOLEAN BREAKDOWN:

    boolean → logical data type
    variables → isLogged, isActive, isJavaEasy
    values → true or false only

    USE CASES:
    - login status
    - conditions
    - validations
    */


    // =====================================================
    // 5. CHAR (SINGLE CHARACTER)
    // =====================================================

    char section = 'A';
    char grade = 'B';
    char symbol = '@';

    /*
    CHAR BREAKDOWN:

    char → primitive data type (single character)
    variables → section, grade, symbol
    values → single character in single quotes ''

    EXAMPLES:
    section → 'A'
    grade → 'B'
    symbol → '@'
    */


    // =====================================================
    // 6. FINAL (CONSTANT VALUES)
    // =====================================================

    final double PI = 3.14;
    final int MAX_USERS = 100;
    final String COMPANY = "Infosys";

    /*
    FINAL BREAKDOWN:

    final → keyword used to make variable CONSTANT (cannot change value)

    VARIABLES:
    PI → mathematical constant
    MAX_USERS → fixed limit value
    COMPANY → constant string value

    RULES:
    - Value cannot be changed once assigned
    - Naming convention: UPPER_CASE
    */


    // =====================================================
    // SUMMARY (FULL REVISION)
    // =====================================================

    /*
    PRIMITIVE DATA TYPES:
    - int → whole numbers
    - double → decimal numbers
    - boolean → true/false
    - char → single character

    NON-PRIMITIVE DATA TYPES:
    - String → text data

    SPECIAL KEYWORD:
    - final → makes variable constant (cannot change)
    */
}