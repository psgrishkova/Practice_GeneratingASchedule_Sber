package com.example.Practice_GeneratingASchedule.Entities;

public class Auditorium extends Lesson {
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

    @Override
    public String toString(){
        char dm=(char)34;

        return "{"+
                dm+"number"+dm+":"+auditoriumNumber+
                ","+dm+"timeTableId"+dm+":"+timeTable.getTimeTableID()+"}";
    }
}
