package com.example.bigbigboy.bean;

/**
 * Created by BigBigBoy on 2015/3/11.
 */
public class PersonBean {

    private int id;
    private String name;
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "id--->" + id + "\nname--->" + name + "\nsex--->" + sex;
    }
}
