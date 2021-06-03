package com.dove.pattern.prototype.shallowcopy;

import java.util.List;

public class Prototype implements Cloneable {
    private String name;

    private List<String> list;

    private Target target;

    private List<Target> targetList;

    public List<Target> getTargetList() {
        return this.targetList;
    }

    public void setTargetList(final List<Target> targetList) {
        this.targetList = targetList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Target getTarget() {
        return this.target;
    }

    public void setTarget(final Target target) {
        this.target = target;
    }

    public List<String> getList() {
        return this.list;
    }

    public void setList(final List<String> list) {
        this.list = list;
    }
}
