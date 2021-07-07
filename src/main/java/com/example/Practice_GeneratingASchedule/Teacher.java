package com.example.Practice_GeneratingASchedule;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private static int teacherID=0;
    private String teacherName;
    private List<Subject> subjects;
    private TimeTable timeTable;

    public Teacher(String teacherName) {
        this.teacherName = teacherName;
        subjects=new ArrayList<>();
        teacherID++;
        timeTable=new TimeTable();
    }
    public void setSubjects(Subject...subjects){
        for (Subject s:
             subjects) {
            this.subjects.add(s);
        }
    }

    public static int getTeacherID() {
        return teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void editTimeTable(Lesson...lessons){
        timeTable.setLessons(lessons);
    }

    public String toString(){
        String res=teacherName+" "+teacherID+": ";
        for (Subject s:
                subjects) {
            res+=s.getNameOfSubject()+"\t";
        }

        return res;
    }
}
