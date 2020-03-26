package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        File myFile = new File("C:\\Users\\DCV\\Documents\\Coding\\Java IO\\Texte\\Abteilungen.txt");
        FileReader fileReader = new FileReader(myFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        Person[] employees = new Person[100];
        int counterEmployees = 0;
        Department[] departments = new Department[100];
        int counterDepartments = 0;

        String line;

        //skip first line
        bufferedReader.skip(bufferedReader.readLine().length() + 1);
        //read all lines after till null
        while ((line = bufferedReader.readLine()) != null) {
            String[] splittedValues = line.split(";");
            String name = splittedValues[0].strip();
            String nameDepartment = splittedValues[1].strip();
            //check if Person already exists:
            Person person = null;
            for (Person employee : employees) {
                if (employee != null) {
                    //if so go on with this existing Person
                    if (employee.name.equals(name)) {
                        person = employee;
                        break;
                    }
                    //if not, add a new one to the array
                } else {
                    person = new Person(name);
                    employees[counterEmployees] = person;
                    counterEmployees++;
                    break;
                }
            }
            //check if Department already exists:
            Department department = null;
            for (Department department1 : departments) {
                if (department1 != null) {
                    //if so go on with this existing department
                    if (department1.name.equals(nameDepartment)) {
                        department = department1;
                        break;
                    }
                    //if not add a new one to the array
                } else {
                    department = new Department(nameDepartment);
                    departments[counterDepartments] = department;
                    counterDepartments++;
                    break;
                }
            }
            //connect both:
            assert person != null;
            person.worksAt(department);
            assert department != null;
            department.employed(person);
        }
        System.out.println("ready");
    }
}
