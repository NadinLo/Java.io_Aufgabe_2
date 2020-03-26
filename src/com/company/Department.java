package com.company;

public class Department {
    String name;
    Person[] staff = new Person[100];
    int counterStaff = 0;
    int amountSubDeps;
    int counterSubDeps;
    Department[] subDeps;
    Department subDep;
    Department parentDep;

    public Department (String name){
        this.name = name;
        //this.amountSubDeps = amountSubDeps;
        this.subDeps = new Department[5];             //zunächst immer 5
    }

    public void employed (Person person){
        staff[counterStaff] = person;
        counterStaff++;
    }

    public void createSubDep (Department department){
        this.subDep = department;
        this.subDeps[this.counterSubDeps] = this.subDep;
        this.counterSubDeps++;
    }

    public void createParentDep (Department department){
        this.parentDep = department;
    }

    public  void printSubDep(String tab) {
        for (int i = 0; i < this.subDeps.length; i++) {
            if (this.subDeps[i] != null) {
                System.out.print(tab + this.subDeps[i].name + " (");
                for (int j = 0; j < this.subDeps[i].staff.length; j++) {
                    if (this.subDeps[i].staff[j] != null){
                        System.out.print(this.subDeps[i].staff[j].name);
                    }
                    System.out.println(")");
                    break;

                }
                tab = "\t\t" + tab;
                this.subDeps[i].printSubDep(tab);
            }
        }
    }
}
