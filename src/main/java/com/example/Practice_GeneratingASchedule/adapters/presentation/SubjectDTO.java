package com.example.Practice_GeneratingASchedule.adapters.presentation;

public class SubjectDTO {
    public SubjectDTO() {
    }

    public SubjectDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    String id;
    String timeTableId;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(String timeTableId) {
        this.timeTableId = timeTableId;
    }
}
