package com.company;

import java.time.LocalDate;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws IllegalArgumentException {
        // write your code here
        Student delStudent = new Student(
                "Ro", "Ja", 206360232, LocalDate.parse("2016-09-20"));
        Student[] keys = {
                delStudent,
                new Student("Rank", "Mkm", 306360232, LocalDate.parse("2016-09-20")),
                new Student("Das", "Fkcu", 106360232, LocalDate.parse("2016-09-20"))
        };
        String[] values = {"0505055050", "23232323232", "545454545"};
        AssociationTable<Student, String> assoc = new AssociationTable<>(keys, values);

        // Add new student
        Student newStudent = new Student(
                "Shimon", "MM", 506360232, LocalDate.parse("2016-09-20"));
        assoc.add(
                newStudent,
                "040403232324"
        );

        // Update phone number
        assoc.remove(newStudent);
        assoc.add(newStudent, "00000000000");

        // Delete student
        assoc.remove(delStudent);

        // Iterate over all the students.
        for (Iterator it = assoc.keyIterator(); it.hasNext(); ) {
            Student key = (Student) it.next();
            System.out.println(key);
        }
    }
}
