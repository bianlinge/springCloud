package com.dove.pattern.prototype.shallowcopy;

import java.util.ArrayList;

public class ProtoTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Prototype prototype = new Prototype();
        System.out.println("resource:"+prototype);
        prototype.setName("tom");
        ArrayList<String> list = new ArrayList<>();
        list.add("111111");
        prototype.setList(list);
        Target target = new Target();
        target.setName("target");
        prototype.setTarget(target);
        ArrayList<Target> targets = new ArrayList<>();
        targets.add(new Target("22222222222"));
        Prototype clone = (Prototype) prototype.clone();


        System.out.println("clone: "+clone);
        System.out.println(clone.getName());
        System.out.println(clone.getList().get(0));
        System.out.println(clone.getTarget().getName());
        //System.out.println(clone.getTargetList().get(0).getName());
        System.out.println(clone.getTargetList());
    }
}
