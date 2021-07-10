package com.example.Practice_GeneratingASchedule.DataProcessing;

import com.example.Practice_GeneratingASchedule.Entities.*;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Data {

    Random random = new Random();
    private List<User> students;
    private List<User> teachers;
    private List<Subject> subjects;
    private List<Auditorium> auditoriums;
    private final LocalDateTime startDay;

    public Data(LocalDateTime startDay) {
        this.startDay = startDay;
        dataFilling();
    }

    public List<User> getStudents() {
        return students;
    }

    public List<User> getTeachers() {
        return teachers;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public LocalDateTime getStartDay() {
        return startDay;
    }

    void dataFilling() {
        Faker faker = new Faker();
        //количество студентов
        int n = 20;
        int subjectsStartID = 0;
        int auditoriumsStartID = 0;
        int studentsStartID = 600;
        int teachersStartID = 100;
        int timeTableStartID = 0;

        students = new ArrayList<>();
        teachers = new ArrayList<>();
        subjects = new ArrayList<>();
        auditoriums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            students.add(new Student(faker.name().name(), ++studentsStartID, ++timeTableStartID));
        }

        for (int i = 0; i < 4; i++) {
            teachers.add(new Teacher(faker.name().name(), ++teachersStartID, ++timeTableStartID));
            auditoriums.add(new Auditorium(++auditoriumsStartID, ++timeTableStartID));
        }

        subjects.add(new Subject("Math", ++subjectsStartID));
        subjects.add(new Subject("Geom", ++subjectsStartID));
        subjects.add(new Subject("Information Security", ++subjectsStartID));
        subjects.add(new Subject("OOP", ++subjectsStartID));
        subjects.add(new Subject("DB", ++subjectsStartID));

        addSubjectsForStudents();

        teachers.get(0).addSubjects(subjects.get(0), subjects.get(3));
        teachers.get(1).addSubjects(subjects.get(0), subjects.get(1));
        teachers.get(2).addSubjects(subjects.get(1), subjects.get(2), subjects.get(4));
        teachers.get(3).addSubjects(subjects.get(3), subjects.get(4));

    }

    private void addSubjectsForStudents() {
        List<Integer> subNumbers;

        for (int i = 0; i < students.size(); i++) {
            subNumbers = createMas(random.nextInt(subjects.size()) + 1);
            for (int j = 0; j < subNumbers.size(); j++) {
                students.get(i).addSubjects(subjects.get(subNumbers.get(j)));
            }
        }
    }

    private List<Integer> createMas(int length) {
        List<Integer> mass = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            mass.add(rnd(0, subjects.size()-1));
        }
        return mass;
    }

    private static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
