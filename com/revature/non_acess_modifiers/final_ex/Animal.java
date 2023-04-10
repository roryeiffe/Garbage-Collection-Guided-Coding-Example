package com.revature.non_acess_modifiers.final_ex;

// If we add the final keyword here, Dog class will break because we can't
// extend a final class:
public class Animal {
    private String name;

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
