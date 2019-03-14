package com.xch.prototype.deep;

import com.xch.prototype.simple.Prototype;

import java.util.ArrayList;

public class DeepTest {
    public static void main(String[] args) {
        PrototypeDeep deep = new PrototypeDeep();
        deep.setData("this is a data");
        deep.setList(new ArrayList<>());
        System.out.println("deep clone before : --------------");
        System.out.println(deep);
        deep.getList().add("this add list");
        Prototype simple2 = deep.clone();
        System.out.println("deep clone after : --------------");
        System.out.println("deep2 : -> " + simple2);
        System.out.println("simple  : -> " + deep);
    }
}
