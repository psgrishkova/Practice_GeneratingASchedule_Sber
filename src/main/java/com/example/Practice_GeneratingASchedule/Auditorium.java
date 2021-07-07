package com.example.Practice_GeneratingASchedule;

import java.util.Date;
import java.util.List;

public class Auditorium {
    private static int auditoriumNumber=1;
    private List<Date> occupied;

    public  static int getAuditoriumNumber() {
        return auditoriumNumber;
    }

    public List<Date> getOccupied() {
        return occupied;
    }
}
