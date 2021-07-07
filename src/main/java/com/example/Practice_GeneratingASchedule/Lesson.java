package com.example.Practice_GeneratingASchedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Lesson {
    private Calendar startLessonDate;
    private Auditorium auditorium;
    private List<Student> students;
    private Teacher teacher;
    private Subject subject;

    public Lesson() {
    }

    public Calendar getStartLessonDate() {
        return startLessonDate;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setStartLessonDate(Calendar startLessonDate) {
        this.startLessonDate = startLessonDate;
    }

    public Lesson(Teacher teacher, Subject subject, Student... students) {
        this.students=new ArrayList<>();
        for (Student s:
             students) {
            this.students.add(s);
        }
        this.teacher = teacher;
        this.subject = subject;
    }

    public String toString(){
        String result="time:"+startLessonDate.getTime().toString()+"\t"+"Au№"+auditorium.getAuditoriumNumber()+"\t"+
                "PrepName: "+teacher.getTeacherName()+"\t"+"Predm: "+subject.getNameOfSubject()+"\t"+"Studs:";
        for (Student s:
             students) {
            result+="\n"+s.getName();
        }
        return result;
    }
}
