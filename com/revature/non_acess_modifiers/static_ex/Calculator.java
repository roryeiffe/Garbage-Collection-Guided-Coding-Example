package com.revature.non_acess_modifiers.static_ex;

public class Calculator {
    // id will vary across instances, so it is not static:
    private int id;
    // PI is the same across all instances, so we give it the static scope:
    public static double PI = 3.14159265;

    public Calculator(int id) {
        this.id = id;
    }

    // This add method does not use any non-static fields, so we can make it static:
    public static int add(int a, int b) {
        return a + b;
    }

    // getId needs to access the non-static id field, so it must be non-static:
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        // Static referenced from static context:
        System.out.println(Calculator.PI);
        System.out.println(Calculator.add(3,4));

        // Non-static referenced from non-static context:
        Calculator calculator = new Calculator(13);
        System.out.println(calculator.getId());

        // Static referenced from non-static context:
        System.out.println(calculator.PI);
        System.out.println(calculator.add(5,6));

        // Non-static referenced from static context (impossible):
        // System.out.println(Calculator.getId());
    }
}
