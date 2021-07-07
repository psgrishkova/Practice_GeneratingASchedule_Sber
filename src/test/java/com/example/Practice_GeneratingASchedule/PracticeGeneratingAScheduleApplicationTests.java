package com.example.Practice_GeneratingASchedule;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

//@SpringBootTest
class PracticeGeneratingAScheduleApplicationTests {

    @Test
    public void generatingASchedule() {

        Subject s = new Subject("Information Security");
        System.out.println(s);

        Faker faker = new Faker();
        Auditorium auditorium = new Auditorium();
        Student student = new Student(faker.name().name());
        Teacher teacher = new Teacher(faker.funnyName().name());
        student.setStudyPlan(s);
        teacher.setSubjects(s);
        System.out.println(student);
        System.out.println(teacher);
        Lesson lesson = new Lesson(teacher, s, student);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 8, 9, 8, 0, 0);
        lesson.setStartLessonDate(calendar);
        System.out.println(lesson.toString());
        TimeTable timeTable = new TimeTable(lesson);
        System.out.println(timeTable.toString());
        Subject s1=new Subject("Math");
        timeTable.getLessons().stream().filter(s-> s.getSubject().getNameOfSubject().equals("Math"));

    }


}
