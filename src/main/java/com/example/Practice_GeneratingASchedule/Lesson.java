package com.example.Practice_GeneratingASchedule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Lesson {
    private LocalDateTime startLessonDate;
    private Auditorium auditorium;
    private List<Student> students;
    private Teacher teacher;
    private Subject subject;

    public Lesson() {
    }

    public LocalDateTime getStartLessonDate() {
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

    public void setStartLessonDate(LocalDateTime startLessonDate) {
        this.startLessonDate = startLessonDate;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
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
        String result="time:"+startLessonDate.toString()+"\n"
                +"auditorium №: "+auditorium.getAuditoriumNumber()+"\n"+
                "TeacherName: "+teacher.getTeacherName()+"\n"+"Subject: "+subject.getNameOfSubject()+"\n"
                +"Studs:";
        for (Student s:
             students) {
            result+="\n"+s.getName();
        }
        return result;
    }
}
