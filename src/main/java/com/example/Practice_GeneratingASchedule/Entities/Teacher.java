package com.example.Practice_GeneratingASchedule.Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Teacher extends Lesson implements User {
    private final int teacherID;
    private final String teacherName;
    private final List<Subject> subjects;
    private final TimeTable timeTable;

    public Teacher(String teacherName, int teacherID, int timeTableID) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        subjects = new ArrayList<>();
        timeTable = new TimeTable(timeTableID);
    }

    public void addSubjects(Subject... subjects) {
        Collections.addAll(this.subjects, subjects);
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

    /*
    public String toString() {
        StringBuilder res = new StringBuilder(teacherName + " " + teacherID + ": ");
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
                dm + "name" + dm + ":" + dm + teacherName + dm + "," +
                dm + "id" + dm +":"+dm+ teacherID + dm + "," +
                dm + "subjects" + dm + ":["+subjects.get(0));
        for (int i=1;i<subjects.size();i++){
            res.append(",").append(subjects.get(i));
        }
        res.append("],").append(dm).append("timeTableId").append(dm).append(":").append(timeTable.getTimeTableID()).append("}");
        return res.toString();
    }
}
