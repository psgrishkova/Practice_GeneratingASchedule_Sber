package com.example.Practice_GeneratingASchedule.DataProcessing;

import com.example.Practice_GeneratingASchedule.Entities.*;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<Student> students;
    private List<Teacher> teachers;
    private List<Subject> subjects;
    private List<Auditorium> auditoriums;

    public Data() {
        dataFilling();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
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

        students.get(0).addSubjects(subjects.get(0),subjects.get(1));
        students.get(1).addSubjects(subjects.get(0));

        teachers.get(0).addSubjects(subjects.get(0));
        teachers.get(1).addSubjects(subjects.get(0),subjects.get(1));

        //System.out.println();
        //System.out.println(students.get(0).getTimeTable().getLessons().get(0));


        //Subject s1 = new Subject("Math");
        // List<Lesson> lessonsList = timeTable.getLessons().stream()
        //        .filter(q -> q.getSubject().getNameOfSubject().equals("Math")).collect(Collectors.toList());


    }
}
