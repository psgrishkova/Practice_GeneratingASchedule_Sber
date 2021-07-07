package com.example.Practice_GeneratingASchedule;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


import java.util.Calendar;

//@SpringBootTest
class PracticeGeneratingAScheduleApplicationTests {


    @Test
    void testingPassage() {
        Generating g = new Generating();
        g.getCurrentDate().set(2021, 7, 8, 8, 0, 0);
        g.setCurrentDate(g.getCurrentDate());
        g.passageOfTime();
        Calendar testCalendar = Calendar.getInstance();
        testCalendar.set(2021, 7, 8, 9, 30, 0);
        System.out.println(g.getCurrentDate().getTime());
        System.out.println(testCalendar.getTime());
        Assert.assertEquals(testCalendar.getTime().toString(), g.getCurrentDate().getTime().toString());
    }

    @Test
    void testingFreeTeachers(){
       Data data=new Data();
       Generating generating=new Generating();
       generating.setTeachers(data.teachers.get(0));

       Assert.assertEquals(1,generating.getFreeTeachers("Math").size());
    }

    @Test
    void testingFreeAuditoriums(){
        Data data=new Data();
        Generating generating=new Generating();
        generating.setAuditoriums(data.auditoriums.get(0));
        Assert.assertEquals(1,generating.getFreeAuditoriums().size());
    }

    @Test
    void testingCreateNewLesson(){
        Data data=new Data();
        Generating generating=new Generating();

        generating.setTeachers(data.teachers.get(0));
        generating.setStudents(data.students.get(0));
        generating.setAuditoriums(data.auditoriums.get(0));

        Calendar calendar=Calendar.getInstance();
        calendar.set(2021, 7, 8, 8, 0, 0);
        generating.setCurrentDate(calendar);

        generating.generatingNewLesson();

        System.out.print(generating.getStudents().get(0).getTimeTable().getLessons().get(0));
    }
}
