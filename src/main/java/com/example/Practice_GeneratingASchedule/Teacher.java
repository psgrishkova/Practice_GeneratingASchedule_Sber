package com.example.Practice_GeneratingASchedule;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private static int teacherID=0;
    private String teacherName;
    private List<Subject> subjects;

    public Teacher(String teacherName) {
        this.teacherName = teacherName;
        subjects=new ArrayList<>();
        teacherID++;
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

    public String toString(){
        String res=teacherName+" "+teacherID+": ";
        for (Subject s:
                subjects) {
            res+=s.getNameOfSubject()+"\t";
        }

        return res;
    }
}
