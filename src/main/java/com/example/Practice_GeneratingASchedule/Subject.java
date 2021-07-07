package com.example.Practice_GeneratingASchedule;

public class Subject {
    private String nameOfSubject;
    private static int subjectID=0;

    public Subject(String nameOfSubject) {
        this.nameOfSubject = nameOfSubject;
        subjectID++;
    }

    public String getNameOfSubject() {
        return nameOfSubject;
    }

    public static int getSubjectID() {
        return subjectID;
    }

    public String toString(){
        return nameOfSubject+" "+subjectID;
    }
}
