package com.example.Practice_GeneratingASchedule.adapters.presentation;

import java.time.LocalDateTime;
import java.util.List;

public class TimeTableRequest {
    private List<ResourceDTO> students;
    private List<ResourceDTO> teachers;
    private List<SubjectDTO> subjects;
    private List<AuditoriumDTO> auditoriums;
    private LocalDateTime startDay;

    public List<ResourceDTO> getStudents() {
        return students;
    }

    public void setStudents(List<ResourceDTO> students) {
        this.students = students;
    }

    public List<ResourceDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<ResourceDTO> teachers) {
        this.teachers = teachers;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }

    public List<AuditoriumDTO> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(List<AuditoriumDTO> auditoriums) {
        this.auditoriums = auditoriums;
    }

    public LocalDateTime getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDateTime startDay) {
        this.startDay = startDay;
    }
}
