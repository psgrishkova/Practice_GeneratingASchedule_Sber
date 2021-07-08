package com.example.Practice_GeneratingASchedule.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lesson {
    private LocalDateTime startLessonDate;
    private Auditorium auditorium;
    private final List<Student> students;
    private final Teacher teacher;
    private final Subject subject;

    public Lesson(Teacher teacher, Subject subject, Student... students) {
        this.teacher = teacher;
        this.subject = subject;
        this.students = new ArrayList<>();
        Collections.addAll(this.students, students);
    }

    public Lesson(Auditorium auditorium, LocalDateTime startLessonDate,Teacher teacher, Subject subject, Student... students){
       this.auditorium=auditorium;
       this.startLessonDate=startLessonDate;
       this.teacher=teacher;
       this.subject=subject;
       this.students=new ArrayList<>();
        Collections.addAll(this.students, students);
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("time:" + startLessonDate.toString() + "\n"
                + "auditorium №: " + auditorium.getAuditoriumNumber() + "\n" +
                "TeacherName: " + teacher.getName() + "\n" + "Subject: " + subject.getNameOfSubject() + "\n"
                + "Studs:");
        for (Student s :
                students) {
            result.append("\n").append(s.getName());
        }
        return result.toString();
    }
}
