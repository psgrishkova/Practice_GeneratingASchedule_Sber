package com.example.Practice_GeneratingASchedule;

import com.example.Practice_GeneratingASchedule.DataProcessing.Data;
import com.example.Practice_GeneratingASchedule.DataProcessing.Generating;
import com.example.Practice_GeneratingASchedule.Entities.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class PracticeGeneratingAScheduleApplicationTests {
    Data data;
    Generating generating;


    @Before
    void creatingData(){
        data=new Data();

    }

    @Test
    void testingPassage() {
        Generating g = new Generating();
        g.setCurrentDate(LocalDateTime.of(2021, 7, 8, 8, 0, 0));
        g.setCurrentDate(g.getCurrentDate());
        g.passageOfTime();
        LocalDateTime testCalendar = LocalDateTime.of(2021, 7, 8, 9, 30, 0);
        System.out.println(g.getCurrentDate());
        System.out.println(testCalendar);
        Assert.assertEquals(testCalendar.toString(), g.getCurrentDate().toString());
    }

    @Test
    void testingFreeTeachers() {
        Data data = new Data();
        Generating generating = new Generating();
        generating.setTeachers(data.getTeachers().toArray(Teacher[]::new));
        Assert.assertEquals(2, generating.getUsersBySubject("Math",generating.getTeachers()).size());
    }

    @Test
    void testingFreeAuditoriums() {
        Data data = new Data();
        Generating generating = new Generating();
        generating.setAuditoriums(data.getAuditoriums().get(0));

        Assert.assertEquals(1, generating.getFreeAuditoriums().size());
    }

    @Test
    void testingNewCreating() {
        Data data = new Data();
        Generating generating = new Generating();

        generating.setTeachers(data.getTeachers().toArray(Teacher[]::new));
        generating.setStudents(data.getStudents().toArray(Student[]::new));
        generating.setAuditoriums(data.getAuditoriums().toArray(Auditorium[]::new));

        LocalDateTime calendar = LocalDateTime.of(2021, 7, 8, 8, 0, 0);
        generating.setCurrentDate(calendar);

        generating.mainCreate();

        int countOfRecords=0;
        List<User> students=generating.getStudents();
        for (int j=0;j<students.size();j++) {
            List<Lesson> lessons = students.get(j).getTimeTable().getLessons();
            System.out.println(students.get(j).getName());
            for (int i = 0; i < lessons.size(); i++) {
                System.out.println(lessons.get(i) + "\n");
                countOfRecords++;
            }
        }

        Assert.assertEquals(4,countOfRecords);
    }
}
