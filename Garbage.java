package com.revature;

import java.util.Scanner;

public class Garbage {
    Garbage field;

    public static void main(String[] args) {
        System.out.println("Creating a new garbage object");
        Garbage garbage = new Garbage();

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
                System.out.println("\tCreating 2 garbage objects that reference each other");
                Garbage garbage1 = new Garbage();
                Garbage garbage2 = new Garbage();
                garbage1.field = garbage2;
                garbage2.field = garbage1;
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
