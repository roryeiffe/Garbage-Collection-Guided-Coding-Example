package com.revature;

public class ObjectClassExample {
    public static void main(String[] args) {
        Movie movie = new Movie("Star Wars", "George Lucas", 1977, 8.6);
        // Convert the object to a string:
        System.out.println(movie.toString());
        // Print the object's hash code:
        System.out.println(movie.hashCode());
        // Print the object's class:
        System.out.println(movie.getClass());
        // Test out the equals method:
        System.out.println(movie.equals(new Movie("Star Wars", "George Lucas", 1977, 8.6)));
        System.out.println(movie.equals(new Movie("Jurassic Park", "Steven Spielberg", 1993, 8.2)));



    }
}
