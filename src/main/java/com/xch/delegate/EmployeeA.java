package com.xch.delegate;

public class EmployeeA implements IEmployee {
    @Override
    public void doSomething(String command) {
        System.out.println("我是员工A, 我擅长: " + command);
    }
}
