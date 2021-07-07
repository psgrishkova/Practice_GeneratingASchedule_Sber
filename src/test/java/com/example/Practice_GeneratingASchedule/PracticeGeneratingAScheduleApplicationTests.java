package com.example.Practice_GeneratingASchedule;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

//@SpringBootTest
class PracticeGeneratingAScheduleApplicationTests {


    @Test
    void testingPassage() {
        Generating g = new Generating();
        g.setCurrentDate(LocalDateTime.of(2021,7,8,8,0,0));
        g.setCurrentDate(g.getCurrentDate());
        g.passageOfTime();
        LocalDateTime testCalendar = LocalDateTime.of(2021,7,8,9,30,0);
        System.out.println(g.getCurrentDate());
        System.out.println(testCalendar);
        Assert.assertEquals(testCalendar.toString(), g.getCurrentDate().toString());
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

        generating.setTeachers(data.teachers.toArray(Teacher[]::new));
        generating.setStudents(data.students.toArray(Student[]::new));
        generating.setAuditoriums(data.auditoriums.toArray(Auditorium[]::new));

        LocalDateTime calendar=LocalDateTime.of(2021,7,8,8,0,0);
        generating.setCurrentDate(calendar);

        generating.generatingNewLesson();
        System.out.println(generating.getStudents().get(0).getTimeTable().getLessons().get(0)+"\n");
        generating.generatingNewLesson();
        System.out.println(generating.getStudents().get(0).getTimeTable().getLessons().get(1));


    }

    @Test
    void testingTheCompletion(){


    }
}
