package com.company;

public class Department {
    String name;
    Person[] staff = new Person[100];
    int counterStaff = 0;

    public Department (String name){
        this.name = name;
    }

    public void employed (Person person){
        staff[counterStaff] = person;
        counterStaff++;
    }
}
