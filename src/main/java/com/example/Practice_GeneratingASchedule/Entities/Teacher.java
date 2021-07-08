package com.example.Practice_GeneratingASchedule.Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Teacher implements User {
    private static int teacherID = 0;
    private final String teacherName;
    private final List<Subject> subjects;
    private final TimeTable timeTable;

    public Teacher(String teacherName) {
        teacherID++;
        this.teacherName = teacherName;
        subjects = new ArrayList<>();
        timeTable = new TimeTable();
    }

    public void addSubjects(Subject... subjects) {
        Collections.addAll(this.subjects, subjects);
    }

    public static int getTeacherID() {
        return teacherID;
    }

    public String getName() {
        return teacherName;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void addLessonsInTimeTable(Lesson... lessons) {
        timeTable.addLessons(lessons);
    }

    public String toString() {
        StringBuilder res = new StringBuilder(teacherName + " " + teacherID + ": ");
        for (Subject s :
                subjects) {
            res.append(s.getNameOfSubject()).append("\t");
        }

        return res.toString();
    }
}
