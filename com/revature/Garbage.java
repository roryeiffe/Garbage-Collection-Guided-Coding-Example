package com.revature;

import java.util.Scanner;

public class Garbage {
    Garbage field;

    public static void main(String[] args) {
        System.out.println("Creating 2 new garbage objects");
        Garbage garbage = new Garbage();
        Garbage garbage1 = new Garbage();

        System.out.print("Enter 1 - 5 => ");
        int choice = new Scanner(System.in).nextInt();

        switch(choice) {
            case 1:
                 System.out.println("Assigning garbage to null:");
                 garbage = null;
                 break;
            case 2:
                System.out.println("Assigning garbage to a new value:");
                garbage = new Garbage();
                break;
            case 3:
                System.out.println("Creating a Garbage object in a method:");
                helper();
                break;
            case 4:
                System.out.println("Anonymous object:");
                new Garbage();
                break;
            case 5:
                System.out.println("Island of Isolation:");
                System.out.println("\tMaking the 2 garbage objects reference each other");
                // Because these objects are only referenced by each other, and nowhere else in the application, they can be
                // eligible for garbage collection
                garbage.field = garbage1;
                garbage1.field = garbage;
                garbage = null;
                garbage1 = null;
                break;
            default:
                System.out.println("Invalid choice.");
                System.exit(0);
        }

        // Invoke the garbage collection method:
        System.gc();
    }

    public static void helper() {
        Garbage garbage = new Garbage();
    }

    @Override
    protected void finalize() {
        System.out.println("\tThis object is being deleted");
    }

}
