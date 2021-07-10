package com.example.Practice_GeneratingASchedule.Entities;

public class Auditorium {
    private final int auditoriumNumber;
    private final TimeTable timeTable;

    public Auditorium(int auditoriumNumber, int timeTableID) {
        timeTable = new TimeTable(timeTableID);
        this.auditoriumNumber = auditoriumNumber;
    }

    public int getAuditoriumNumber() {
        return auditoriumNumber;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void editTimeTable(Lesson... lessons) {
        timeTable.addLessons(lessons);
    }
}
