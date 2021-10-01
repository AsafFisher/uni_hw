package com.company;

import java.util.Calendar;

public abstract class Employee {
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    private final BirthDate birthDate;

    // constructor
    public Employee(String firstName, String lastName, String socialSecurityNumber, BirthDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.birthDate = birthDate;
    }
    public BirthDate getBirthDate(){
        return this.birthDate;
    }
    // return first name
    public String getFirstName() {
        return firstName;
    }

    // return last name
    public String getLastName() {
        return lastName;
    }

    // return social security number
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    // return String representation of Employee object
    @Override
    public String toString() {
        return String.format("%s %s\nsocial security number: %s\nBirthdate(Y-M-D) %s-%s-%s",
                getFirstName(), getLastName(), getSocialSecurityNumber(),
                getBirthDate().date.get(0), getBirthDate().date.get(1), getBirthDate().date.get(2));
    }

    // abstract method must be overridden by concrete subclasses
    public abstract double earnings(); // no implementation here
    
    public double full_earnings(){
        // If the employee was born on this month add 200$.
        return this.earnings() + (this.birthDate.getMonth() == Calendar.getInstance().get(Calendar.MONTH) ? 200:0);
    }
}