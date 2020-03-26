package com.company;

public class Person {
    String name;
    Department[] workPlaces = new Department[100];
    int counterWorkplaces = 0;

    public Person (String name){
        this.name = name;
    }
    public void worksAt (Department department){
        this.workPlaces[counterWorkplaces] = department;
    }
}
