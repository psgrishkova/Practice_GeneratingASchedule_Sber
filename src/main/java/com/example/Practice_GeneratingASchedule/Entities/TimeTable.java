package com.example.Practice_GeneratingASchedule.Entities;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeTable {
    private static int timeTableID = 0;
    private final List<Lesson> lessons;

    public TimeTable(Lesson... lessons) {
        timeTableID++;
        this.lessons = new ArrayList<>();
        addLessons(lessons);
    }

    public static int getTimeTableID() {
        return timeTableID;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void addLessons(Lesson... lessons) {
        Collections.addAll(this.lessons, lessons);
    }

    public String toString() {
        StringBuilder result = new StringBuilder(timeTableID + "\t");
        for (Lesson l :
                lessons) {
            result.append(l.toString()).append("\n");
        }
        return result.toString();
    }
}
