package com.example.Practice_GeneratingASchedule.adapters.presentation;

import java.util.List;

public class ResourceDTO {
    public ResourceDTO() {
    }

    public ResourceDTO(String name, String id, List<SubjectDTO> subjects, Integer timeTableId) {
        this.name = name;
        this.id = id;
        this.subjects = subjects;
        this.timeTableId = timeTableId;
    }

    public ResourceDTO(String name, String id) {
        this.name = name;
        this.id = id;
    }

    private String name;
    private String id;
    private List<SubjectDTO> subjects;
    private Integer timeTableId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }

    public Integer getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(Integer timeTableId) {
        this.timeTableId = timeTableId;
    }
}
