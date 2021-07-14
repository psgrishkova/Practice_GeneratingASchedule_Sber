package com.example.Practice_GeneratingASchedule.adapters.presentation;

import java.util.List;

public class TimeTableResponse {


    List<LessonDTO> lessonDTOS;
    private Integer efficiency;

    public List<LessonDTO> getLessonDTOS() {
        return lessonDTOS;
    }

    public void setLessonDTOS(List<LessonDTO> lessonDTOS) {
        this.lessonDTOS = lessonDTOS;
    }

    public Integer getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Integer efficiency) {
        this.efficiency = efficiency;
    }
}
