package com.company;

import java.time.LocalDate;

public class Student implements Comparable<Student>{
    private String firstName;
    private String lastName;
    private int id;
    private LocalDate birthDate;
    public Student(String firstName, String lastName, int id, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.getId() - o.getId();
    }
}
