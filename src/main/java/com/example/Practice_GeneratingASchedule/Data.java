package com.example.Practice_GeneratingASchedule;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Calendar;
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

        students = new ArrayList<>();
        teachers = new ArrayList<>();
        subjects = new ArrayList<>();
        auditoriums = new ArrayList<>();

        students.add(new Student(faker.name().name()));
        teachers.add(new Teacher(faker.name().name()));
        subjects.add(new Subject("Math"));
        auditoriums.add(new Auditorium());

        students.get(0).setStudyPlan(subjects.get(0));
        teachers.get(0).setSubjects(subjects.get(0));



        //System.out.println();
        //System.out.println(students.get(0).getTimeTable().getLessons().get(0));


        //Subject s1 = new Subject("Math");
        // List<Lesson> lessonsList = timeTable.getLessons().stream()
        //        .filter(q -> q.getSubject().getNameOfSubject().equals("Math")).collect(Collectors.toList());


    }
}
