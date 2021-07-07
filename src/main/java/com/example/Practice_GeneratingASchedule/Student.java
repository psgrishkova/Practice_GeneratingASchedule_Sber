package com.example.Practice_GeneratingASchedule;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private static int stud_id = 600;
    private List<Subject> studyPlan;
    private TimeTable timeTable;

    public Student(String name) {
        this.name = name;
        studyPlan=new ArrayList<>();
        stud_id++;
    }

    public void setStudyPlan(Subject... subjects) {
        for (Subject s : subjects) {
            this.studyPlan.add(s);
        }

    }

    public String getName() {
        return name;
    }

    public static int getStud_id() {
        return stud_id;
    }

    public List<Subject> getStudyPlan() {
        return studyPlan;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    @Override
   public String toString(){
     String res=name+" "+stud_id+": ";
        for (Subject s:
             studyPlan) {
            res+=s.getNameOfSubject()+"\t";
        }

        return res;
   }
}
