package com.example.Practice_GeneratingASchedule;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class Data {

    List<Student> students;
    List<Teacher> teachers;
    List<Subject> subjects;
    List<Auditorium> auditoriums;

    public Data() {
        dataFilling();
    }

    void dataFilling() {
        Faker faker = new Faker();
        int n = 2;

        students = new ArrayList<>();
        teachers = new ArrayList<>();
        subjects = new ArrayList<>();
        auditoriums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            students.add(new Student(faker.name().name()));
            teachers.add(new Teacher(faker.name().name()));
            auditoriums.add(new Auditorium());
        }
        subjects.add(new Subject("Math"));
        subjects.add(new Subject("Geom"));
        subjects.add(new Subject("IS"));


        students.get(0).setStudyPlan(subjects.get(0),subjects.get(1));
        students.get(1).setStudyPlan(subjects.get(0),subjects.get(2));

        teachers.get(0).setSubjects(subjects.get(0),subjects.get(2));
        teachers.get(1).setSubjects(subjects.get(0),subjects.get(1));


        //System.out.println();
        //System.out.println(students.get(0).getTimeTable().getLessons().get(0));


        //Subject s1 = new Subject("Math");
        // List<Lesson> lessonsList = timeTable.getLessons().stream()
        //        .filter(q -> q.getSubject().getNameOfSubject().equals("Math")).collect(Collectors.toList());


    }
}
