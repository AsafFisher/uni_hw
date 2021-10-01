package com.company;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Handler;

public class Main {

    public static void main(String[] args) {
        Employee[] employees = generateEmployees();
        for (Employee employee: employees) {
            System.out.println(employee);
            System.out.println("Wage including birthday bonus:");
            System.out.println(employee.full_earnings());
            System.out.println("===============");
        }
    }

    public static Employee[] generateEmployees() {
        return new Employee[]{
                new BasePlusCommissionEmployee(
                        "meme", "last", "1234",
                        1.0, 0.1, 1.0,
                        new BirthDate(1, 1, 1)),
                new CommissionEmployee(
                        "Meme", "ddd", "123",
                        1.2, 0.2,
                        new BirthDate(1,2,3)),
                new SalariedEmployee(
                        "Meme", "Mama", "123", 1.0,
                        new BirthDate(1,2,3)),
                new HourlyEmployee(
                        "UDB", "djdd", "123",
                        1.2, 1.2,
                        new BirthDate(1,8,3)),
                new PieceWorker("Body", "ddd", "123",
                        new BirthDate(1,2,3),
                        4, 3.2)};
    }
}

