package com.example.Practice_GeneratingASchedule.DataProcessing;

import com.example.Practice_GeneratingASchedule.Entities.Auditorium;
import com.example.Practice_GeneratingASchedule.Entities.Subject;
import com.example.Practice_GeneratingASchedule.Entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Data {
    private final List<User> students;
    private final List<User> teachers;
    private final List<Subject> subjects;
    private final List<Auditorium> auditoriums;
    private final LocalDateTime startDay;

    public Data(LocalDateTime startDay) {
        this.startDay = startDay;
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        subjects = new ArrayList<>();
        auditoriums = new ArrayList<>();
    }

    public List<User> getStudents() {
        return students;
    }

    public List<User> getTeachers() {
        return teachers;
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public LocalDateTime getStartDay() {
        return startDay;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void editStudents(User... students) {
        Collections.addAll(this.students, students);
    }

    public void editTeachers(User... teachers) {
        Collections.addAll(this.teachers, teachers);
    }

    public void editAuditoriums(Auditorium... auditoriums) {
        Collections.addAll(this.auditoriums, auditoriums);
    }

    public void editSubjects(Subject... subjects) {
        Collections.addAll(this.subjects, subjects);
    }

/*
    void testingData() {
        Faker faker = new Faker();
        //количество студентов
        int n = 20;
        int subjectsStartID = 0;
        int auditoriumsStartID = 0;
        int studentsStartID = 600;
        int teachersStartID = 100;
        int timeTableStartID = 0;


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

        for (User student : students) {
            subNumbers = createMas( new Random().nextInt(subjects.size()) + 1);
            for (Integer subNumber : subNumbers) {
                student.addSubjects(subjects.get(subNumber));
            }
        }
    }

    private List<Integer> createMas(int length) {
        List<Integer> mass = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            mass.add(rnd(subjects.size() - 1));
        }
        return mass;
    }

    private static int rnd(int max) {
        return (int) (Math.random() * ++max);
    }
    */
}
