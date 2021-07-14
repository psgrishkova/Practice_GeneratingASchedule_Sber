package com.example.Practice_GeneratingASchedule.adapters.presentation;

import java.time.LocalDateTime;

public class LessonDTO {
    String auditoriumNumber;
    LocalDateTime startLessonDateTIme;
    ResourceDTO teacher;

    public String getAuditoriumNumber() {
        return auditoriumNumber;
    }

    public void setAuditoriumNumber(String auditoriumNumber) {
        this.auditoriumNumber = auditoriumNumber;
    }

    public LocalDateTime getStartLessonDateTIme() {
        return startLessonDateTIme;
    }

    public void setStartLessonDateTIme(LocalDateTime startLessonDateTIme) {
        this.startLessonDateTIme = startLessonDateTIme;
    }

    public ResourceDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(ResourceDTO teacher) {
        this.teacher = teacher;
    }
}
