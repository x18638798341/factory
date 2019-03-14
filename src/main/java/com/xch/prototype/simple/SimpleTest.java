package com.xch.prototype.simple;

import java.util.ArrayList;

public class SimpleTest {

    public static void main(String[] args) {
        PrototypeSimple simple = new PrototypeSimple();
        simple.setData("this is a data");
        simple.setList(new ArrayList<>());
        System.out.println("clone before : --------------");
        System.out.println(simple);
        simple.getList().add("this add list");
        Prototype simple2 = simple.clone();
        System.out.println("clone after : --------------");
        System.out.println("simple2 : -> " + simple2);
        System.out.println("simple  : -> " + simple);
    }
}
