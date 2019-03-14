package com.xch.delegate;

import java.util.HashMap;
import java.util.Map;

public class Leader {

    public static final Map<String, IEmployee> EMPLOYEE_REGISTRY = new HashMap<>(4);

    static {
        EMPLOYEE_REGISTRY.put("修理", new EmployeeA());
        EMPLOYEE_REGISTRY.put("架构", new EmployeeB());
    }

    public Leader() {
    }

    public void doing(String thing){
        EMPLOYEE_REGISTRY.get(thing).doSomething(thing);
    }
}
