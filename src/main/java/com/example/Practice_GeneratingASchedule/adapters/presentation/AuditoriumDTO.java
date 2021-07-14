package com.example.Practice_GeneratingASchedule.adapters.presentation;

public class AuditoriumDTO {
    public AuditoriumDTO() {
    }

    public AuditoriumDTO(Integer number, String timeTableId) {
        this.number = number;
        this.timeTableId = timeTableId;
    }

    Integer number;
    String timeTableId;
    String id;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(String timeTableId) {
        this.timeTableId = timeTableId;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }
}
