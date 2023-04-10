package com.revature.non_acess_modifiers.final_ex;

public class Dog extends Animal {
    public final String species = "dog";

    // Can't override a final method:
    @Override
    public void speak() {
            System.out.println("I am a talking dog");
        }

    public static void main(String[] args) {
        final Dog dog = new Dog();

        // Because dog object was declared final, we can't reassign it:
        // dog = new Dog();

        // That doesn't mean we can't change properties of dog:
        dog.setName("Clifford");

        // As long as the property isn't declared as final:
        // dog.species = "cat";


    }
}
