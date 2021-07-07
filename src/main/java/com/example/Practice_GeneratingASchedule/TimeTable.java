package com.example.Practice_GeneratingASchedule;

import java.util.ArrayList;
import java.util.List;

public class TimeTable {
    private static int timeTableID = 0;
    private List<Lesson> lessons;

    public TimeTable(Lesson... lessons) {
        timeTableID++;
        this.lessons = new ArrayList<>();
        setLessons(lessons);
    }

    public static int getTimeTableID() {
        return timeTableID;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }
    public void setLessons(Lesson...lessons){
        for (Lesson l :
                lessons) {
            this.lessons.add(l);
        }
    }

    public String toString() {
        String result = timeTableID + "\t";
        for (Lesson l:
             lessons) {
            result+=l.toString()+"\n";
        }
        return result;
    }
}
