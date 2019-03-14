package com.xch.prototype.simple;

import java.util.List;

public class PrototypeSimple implements Prototype {

    private String data;

    private List<String> list;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PrototypeSimple{" +
                "data='" + data + '\'' +
                ", list=" + list +
                '}';
    }

    @Override
    public Prototype clone() {
        PrototypeSimple simple = new PrototypeSimple();
        simple.data = this.data;
        simple.list = this.list;
        return simple;
    }
}
