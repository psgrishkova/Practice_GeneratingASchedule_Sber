package com.example.Practice_GeneratingASchedule;

import java.util.Date;
import java.util.List;

public class Auditorium {
    private static int auditoriumNumber = 1;
    private TimeTable timeTable;

    public Auditorium() {
        timeTable = new TimeTable();
        auditoriumNumber++;
    }

    public static int getAuditoriumNumber() {
        return auditoriumNumber;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }



    public void editTimeTable(Lesson...lessons){
        timeTable.setLessons(lessons);
    }

}
