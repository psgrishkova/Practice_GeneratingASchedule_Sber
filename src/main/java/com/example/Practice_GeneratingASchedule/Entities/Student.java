package com.example.Practice_GeneratingASchedule.Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student implements User {
    private final String name;
    private static int stud_id = 600;
    private final List<Subject> subjects;
    private final TimeTable timeTable;

    public Student(String name) {
        this.name = name;
        stud_id++;
        subjects = new ArrayList<>();
        timeTable = new TimeTable();
    }

    public String getName() {
        return name;
    }

    public static int getStud_id() {
        return stud_id;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void addSubjects(Subject... subjects) {
        Collections.addAll(this.subjects, subjects);
    }

    public void addLessonsInTimeTable(Lesson... lessons) {
        timeTable.addLessons(lessons);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(name + " " + stud_id + ": ");
        for (Subject s :
                subjects) {
            res.append(s.getNameOfSubject()).append("\t");
        }

        return res.toString();
    }
}
