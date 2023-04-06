# Java Classes 014 - Garbage Collection

### Contributors:

Authors:
- Rory Eiffe
    - With inspiration from [Wezley Singleton](https://gitlab.com/revature_training/java-team/-/blob/master/java-standard-examples/java/src/main/java/com/revature/javaBasics/garbagecollection/GarbageDriver.java)

Reviewers:
- TBD

## Prerequisites

- Will need JDK installed
    - Java Version 8 preferred because finalize is deprecated in version 9 and above
- Will need a code editor

# How to Complete

## 1. Create a new class called Garbage

We are creating a class called Garbage so we can demonstrate the different ways to make an object eligible for garbage collection.

### Garbage.java
```java
public class Garbage {

}
```

## 2. Create a main method

The main method will be where we create objects and make them eligible for garbage collection in different ways.

### Garbage.java
```java
public class Garbage {
    public static void main(String[] args) {

    }
}
```

## 3. Override the finalize method

In the finalize method, we print out a descriptive message and exit the program:

### Garbage.java
```java
public class Garbage {

    @Override
    protected void finalize() {
        System.out.println("This object is being deleted");
    }
}
```

## 4. Add a helper method and a field member:

The helper method is used to demonstrate objects being used in a method scope and the field member is used to demonstrate island of isolation. 

```java
public class Garbage {
    Garbage field;

    public static void helper() {
        Garbage garbage = new Garbage();
    }

}

```

## 5. Fill out the main method:

In the main method, we use a Scanner object to accept a choice from the user. Using a switch block, we can show off the different ways to make an object eligible for garbage collection. In each case, we make the Garbage object eligible for garbage collection and print out an informative message. 

After the switch block, we call System.gc() to start the garbage collection.

```java
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
```


### Link to the project code if not provided in the same repository as the Guided Coding Example rundown
[Full Code](Garbage.java)