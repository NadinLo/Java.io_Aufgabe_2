package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        File myFile = new File("C:\\Users\\DCV\\Documents\\Coding\\Java IO\\Texte\\Abteilungen1.txt");
        FileReader fileReader = new FileReader(myFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        Person[] employees = new Person[100];
        int counterEmployees = 0;
        Department[] departments = new Department[100];
        int counterDepartments = 0;

        //skip first line
        bufferedReader.readLine();
        String line = null;

        //read all lines after first line till null
        while ((line = bufferedReader.readLine()) != null) {
            String[] splittedValues = line.split(";");
            String name = splittedValues[0].strip();
            String nameDepartment = splittedValues[1].strip();

            //check if Person already exists:
            Person person = null;
            for (int i = 0; i < employees.length; i++) {
                //if not create new Person
                if (employees[i] == null) {
                    person = new Person(name);
                    employees[i] = person;
                    counterEmployees++;
                    break;
                    //if so, go on with this person
                }
                if (employees[i].name.equals(name)) {
                    person = employees[i];
                    break;
                }

            }
            //check if Department already exists:
            Department department = null;
            for (int i = 0; i < departments.length; i++) {
                //if not create a new one
                if (departments[i] == null) {
                    department = new Department(nameDepartment);
                    departments[i] = department;
                    counterDepartments++;
                    break;
                    //if so, go on with this department
                }
                if (departments[i].name.equals(nameDepartment)) {
                    department = departments[i];
                    break;
                }

            }

            //connect both:
            person.worksAt(department);
            department.employed(person);

            if (splittedValues.length == 3) {
                String nameParentDepartment = splittedValues[2].strip();
                //check if ParentDepartment already exists:
                Department parentDep = null;
                for (int i = 0; i < departments.length; i++) {
                    //if not create a new one
                    if (departments[i] == null) {
                        parentDep = new Department(nameParentDepartment);
                        departments[i] = parentDep;
                        counterDepartments++;
                        break;
                        //if so, go on with this department
                    }
                    if (departments[i].name.equals(nameParentDepartment)) {
                        parentDep = departments[i];
                        break;
                    }
                }
                //connecting ParentDepartment with SubDepartment:
                //is connection already given?
                for (int i = 0; i < parentDep.subDeps.length; i++) {
                    if (parentDep.subDeps[i] != null) {
                        if (parentDep.subDeps[i].name.equals(department.name)) {
                            break;
                        }
                    } else {
                        parentDep.createSubDep(department);
                        department.createParentDep(parentDep);
                        break;
                    }
                }
            }
        }

        String printDepartment = "";
        //check which department has no parentDepartment:
        for (Department department : departments) {
            if (department.parentDep == null) {
                printDepartment = printDepartment + department.name + ": (";
                String printStaff = "";
                for (int j = 0; j < department.staff.length; j++) {
                    if (department.staff[j] != null) {
                        printStaff = printStaff + department.staff[j].name + " ";
                    }
                }
                System.out.println(printDepartment + printStaff + ")");
                department.printSubDep("\t|_");
                break;

            }
        }
    }
}

