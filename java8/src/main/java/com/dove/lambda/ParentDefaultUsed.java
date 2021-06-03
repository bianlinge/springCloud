package com.dove.lambda;

public class ParentDefaultUsed {
    public static void parentDefaultUsed() {
        Parent parent = new ParentImpl();
        parent.welcome();

        ChildImpl child = new ChildImpl();
        child.welcome();

    }
    public static void main(String[] args) {
        parentDefaultUsed();
    }
}
