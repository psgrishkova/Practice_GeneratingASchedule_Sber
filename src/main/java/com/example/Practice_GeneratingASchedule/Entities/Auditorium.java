package com.example.Practice_GeneratingASchedule.Entities;

public class Auditorium {
    private static int auditoriumNumber = 1;
    private final TimeTable timeTable;

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

    public void editTimeTable(Lesson... lessons) {
        timeTable.addLessons(lessons);
    }
}
