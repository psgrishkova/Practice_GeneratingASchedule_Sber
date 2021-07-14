package com.example.Practice_GeneratingASchedule.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lesson extends TimeTable {
    private LocalDateTime startLessonDate;
    private Auditorium auditorium;
    private  List<User> students;
    private  User teacher;
    private  Subject subject;

    public Lesson(User teacher, Subject subject, User... students) {
        this.teacher = teacher;
        this.subject = subject;
        this.students = new ArrayList<>();
        Collections.addAll(this.students, students);
    }

    public Lesson() {
    }

    public LocalDateTime getStartLessonDate() {
        return startLessonDate;
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
        return "time:" + startLessonDate.toString() + "\n"
                + "auditorium №: " + auditorium.getAuditoriumNumber() + "\n" +
                "TeacherName: " + teacher.getName() + "\n" + "Subject: " + subject.getNameOfSubject();
    }
}
