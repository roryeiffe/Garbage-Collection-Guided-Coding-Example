package com.revature;

public class Equality {
    public static void main(String[] args) {
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

    }
}
