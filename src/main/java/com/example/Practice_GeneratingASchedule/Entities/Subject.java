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

    public String toString() {
        return nameOfSubject + " " + subjectID;
    }
}
