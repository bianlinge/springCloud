package com.dove.pattern.prototype.shallowcopy;

public class Target {
    public Target() {
    }

    public Target(final String name) {
        this.name = name;
    }

    private String name = "jerry";

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
