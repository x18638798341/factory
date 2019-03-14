package com.xch.delegate;

public class EmployeeB implements IEmployee {
    @Override
    public void doSomething(String command) {
        System.out.println("我是员工B, 我擅长: " + command);
    }
}
