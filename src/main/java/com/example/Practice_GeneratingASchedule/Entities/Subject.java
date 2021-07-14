package com.example.Practice_GeneratingASchedule.Entities;

public class Subject {
    private final String nameOfSubject;
    private final int subjectID;

    public Subject(String nameOfSubject, int subjectID) {
        this.nameOfSubject = nameOfSubject;
        this.subjectID = subjectID;
    }

    public String getNameOfSubject() {
        return nameOfSubject;
    }

    @Override
    public String toString() {
        char dm=(char)34;
        return "{"+
                dm+"id"+dm+":"+dm+subjectID+dm+","+
                dm+"name"+dm+":"+dm+nameOfSubject+dm+"}";
    }

    public int getSubjectID() {
        return subjectID;
    }
}
