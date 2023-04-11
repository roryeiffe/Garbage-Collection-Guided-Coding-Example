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

## The Object Class Example

### 1. Create an entity with at least 2 fields and override the methods that are inherited from the object class:
- Some IDE's have an option to auto-generate these methods but we can also type out the methods manually
- Either way, make sure to explain what is happening in the methods:

#### Movie.java (Constructors, getters, setters removed for brevity)
```java
public class Movie {
    private String title;
    private String director;
    private int year;
    private double rating;

    @Override
    public boolean equals(Object o) {
        // first, check the reference:
        if (this == o) return true;
        // make sure the object isn't null and ensure the classes match:
        if (o == null || getClass() != o.getClass()) return false;
        // cast the object to a Movie object:
        Movie movie = (Movie) o;
        // compare the individual fields:
        return year == movie.year && Double.compare(movie.rating, rating) == 0 && Objects.equals(title, movie.title) && Objects.equals(director, movie.director);
    }

    @Override
    public int hashCode() {
        // Use the Objects.hash method on all of the fields
        // This is explain in further detail down below:
        return Objects.hash(title, director, year, rating);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                '}';
    }
}
```

### 2. Create a main method where you can instantiate an object and invoke the overridden/inherited methods:
```java
        Movie movie = new Movie("Star Wars", "George Lucas", 1977, 8.6);
        // Print out the object as a string:
        System.out.println(movie.toString());
        // Print the object's hash code:
        System.out.println(movie.hashCode());
        // Print the object's class:
        System.out.println(movie.getClass());
        // Test out the equals method:
        System.out.println(movie.equals(new Movie("Star Wars", "George Lucas", 1977, 8.6)));
        System.out.println(movie.equals(new Movie("Jurassic Park", "Steven Spielberg", 1993, 8.2)));
```

Some things to note:
- The toString call is not necessary, it is called implicitly when the object is passed into the System.out.println method.

### 3. Inspect the Objects.hash and Arrays.hashCode method:
- Some IDE's, like IntelliJ, let you right-click a method and go right to the declaration. Otherwise, the methods are also included here:
#### Objects hash method:
```java
public class Objects {

    public static int hash(Object... values) {
            return Arrays.hashCode(values);
        }
}

```

#### Arrays hashCode method:
```java
public class Arrays{

    public static int hashCode(Object a[]) {
            if (a == null)
                return 0;

            int result = 1;

            for (Object element : a)
                result = 31 * result + (element == null ? 0 : element.hashCode());

            return result;
        }
}
```


## Equality Example

### 1. If you haven't already, create a class with at least 2 fields and override the .equals and .hashCode methods
- The Movie class from the [previous example](#the-object-class-example) will work

### 2. Create a main method where you invoke the .equals and .hashCode methods
```java
        // Run this code once with the Movie .equals and.hashCode methods overridden and once without those methods being overridden
        Movie movie1 = new Movie("Frozen", "Jennifer Lee", 2013, 7.4);
        Movie movie2 = new Movie("Frozen", "Jennifer Lee", 2013, 7.4);
        Movie movie3 = new Movie("Monsters Inc.", "Pete Doctor", 2001, 8.1);

        // equals:
        System.out.println("Movie 1 to Movie 1: " + movie1.equals(movie1));
        System.out.println("Movie 1 to Movie 2: " + movie1.equals(movie2));
        System.out.println("Movie 1 to Movie 3: " + movie1.equals(movie3));
        System.out.println("Movie 2 to Movie 3: " + movie2.equals(movie3));

        // Hash Code:
        System.out.println("Movie 1 hash: " +  movie1.hashCode());
        System.out.println("Movie 2 hash: " +  movie2.hashCode());
        System.out.println("Movie 3 hash: " +  movie3.hashCode());
```

### 3. Run the code and examine the output
i. First, run the code as-is and note which objects are "equal to" each other and how this relates to the hash code values.

ii. Next, comment out the .equals and .hashCode methods in the Movie class and note how the results of the .equals change. The only .equals invocation that should still return true is comparing movie1 to itself because it's comparing the memory reference. 

iii. Now, try commenting out just the .equals method and just .hashCode and see how the results change.

Make sure to emphasize that it is not good practice to override only one of hashCode/equals due to the general contract for the hashCode method, which states that equal objects must have equal hash codes.

## Non-Access Modifiers

### 1. Create a Calculator class with some static and non-static fields and methods:

#### Calculator.java
```java
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
}
```

### 2. Create a main method to show off static vs non-static contexts:
```java
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
    
```

### 3. Create an abstract class to show off the abstract keyword:
#### Shape
```java
// The abstract keyword, when used on a class, indicates that
// the class is abstract and therefore can contain abstract methods
// An abstract class cannot be instantiated
public abstract class Shape {

    // An abstract method doesn't have a body, just a declaration:
    public abstract int getArea();
}
```

### 4. Create 2 classes and have one class extend the other:

#### Animal.java
```java
// If we add the final keyword here, the Dog class will break because we can't
// extend a final class:
public class Animal {
    private String name;

    // If we add the final keyword here, the Dog class will break because we can't
    // override a final method:
    public void speak() {
        System.out.println("I am a talking animal.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

#### Dog.java
```java
public class Dog extends Animal{
    public final String species = "dog";

    // Can't override a final method:
       @Override
       public void speak() {
           System.out.println("I am a talking dog");
       }
}
```

### 3. Create a main method that demonstrates the final keyword:
```java
    final Dog dog = new Dog();

    // Because dog object was declared final, we can't reassign it:
    // dog = new Dog();

    // That doesn't mean we can't change properties of dog:
    dog.setName("Clifford");

    // As long as the property isn't declared as final:
    // dog.species = "cat";
```

### 4. Demonstrate class-level and method-level uses of final:
i. Add the final keyword to the Animal class and note how the Dog class is unable to extend the Animal class.
#### Animal.java
```java
public final class Animal {
    ...
}
```

ii. Add the final keyword to the speak method in the Animal class and note how the Dog class is unable to override a final method.
#### Animal.java
```java
public class Animal {
    public final void speak() {
        System.out.println("I am a talking animal.");
    }
}
```



## Garbage Collection Example:

### 1. Create a new class called Garbage

We are creating a class called Garbage so we can demonstrate the different ways to make an object eligible for garbage collection.

#### Garbage.java
```java
public class Garbage {

}
```

### 2. Create a main method

The main method will be where we create objects and make them eligible for garbage collection in different ways.

#### Garbage.java
```java
public class Garbage {
    public static void main(String[] args) {

    }
}
```

### 3. Override the finalize method

In the finalize method, we print out a descriptive message. We can also point out this is one of the methods that is inherited from the Object class.

#### Garbage.java
```java
public class Garbage {

    @Override
    protected void finalize() {
        System.out.println("This object is being deleted");
    }
}
```

### 4. Add a helper method and a field member:

The helper method is used to demonstrate objects going out of scope and the field member is used to demonstrate island of isolation. 

#### Garbage.java
```java
public class Garbage {
    Garbage field;

    public static void helper() {
        Garbage garbage = new Garbage();
    }

}

```

### 5. Fill out the main method:

In the main method, we use a Scanner object to accept a choice from the user. Using a switch block, we can show off the different ways to make an object eligible for garbage collection. In each case, we make the Garbage object eligible for garbage collection and print out an informative message. 

After the switch block, we call System.gc() to start the garbage collection.

#### Garbage.java
```java
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
        // Note that this is not recommended in real-world Java programs because garbage collection is an automatic process
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


### Link to the project code:
[Root Directory](com/revature/)