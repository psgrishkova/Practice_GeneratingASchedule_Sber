package com.example.Practice_GeneratingASchedule.Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student extends Lesson implements User {
    private final String name;
    private final int stud_id;
    private final List<Subject> subjects;
    private final TimeTable timeTable;

    public Student(String name, int stud_id, int timeTableID) {
        this.name = name;
        this.stud_id = stud_id;
        subjects = new ArrayList<>();
        timeTable = new TimeTable(timeTableID);
    }

    public String getName() {
        return name;
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
/*
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(name + " " + stud_id + ": ");
        for (Subject s :
                subjects) {
            res.append(s.getNameOfSubject()).append("\t");
        }

        return res.toString();
    }
    */

    @Override
    public String toString() {
        char dm=(char)34;
        StringBuilder res;
        res = new StringBuilder("{" +
                dm + "name" + dm + ":" + dm + name + dm + "," +
                dm + "id" + dm +":"+dm+ stud_id + dm + "," +
                dm + "subjects" + dm + ":["+subjects.get(0));
        for (int i=1;i<subjects.size();i++){
            res.append(",").append(subjects.get(i));
        }
        res.append("],").append(dm).append("timeTableId").append(dm).append(":").append(timeTable.getTimeTableID()).append("}");
        return res.toString();
    }

}
