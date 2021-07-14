package com.example.Practice_GeneratingASchedule;

import com.example.Practice_GeneratingASchedule.DataProcessing.Data;
import com.example.Practice_GeneratingASchedule.DataProcessing.Generator;
import com.example.Practice_GeneratingASchedule.DataProcessing.GeneratorImpl;
import com.example.Practice_GeneratingASchedule.DataProcessing.KPI;
import com.example.Practice_GeneratingASchedule.Entities.*;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//@SpringBootTest
class PracticeGeneratingAScheduleApplicationTests {
    private static final LocalDateTime startDay = LocalDateTime.of(2021, 7, 8, 8, 0);
    private Data data;

    @BeforeEach
    void testingData() {
        data = new Data(startDay);
        Faker faker = new Faker();
        //количество студентов
        int n = 50;
        int subjectsStartID = 0;
        int auditoriumsStartID = 0;
        int studentsStartID = 600;
        int teachersStartID = 100;
        int timeTableStartID = 1;


        for (int i = 0; i < n; i++) {
            data.editStudents(new Student(faker.name().name(), ++studentsStartID, timeTableStartID));
        }

        for (int i = 0; i < 4; i++) {
            data.editTeachers(new Teacher(faker.name().name(), ++teachersStartID, timeTableStartID));
            data.editAuditoriums(new Auditorium(++auditoriumsStartID, timeTableStartID));
        }

        data.editSubjects(new Subject("Math", ++subjectsStartID));
        data.editSubjects(new Subject("Geom", ++subjectsStartID));
        data.editSubjects(new Subject("Information Security", ++subjectsStartID));
        data.editSubjects(new Subject("OOP", ++subjectsStartID));
        data.editSubjects(new Subject("DB", ++subjectsStartID));
        data.editSubjects(new Subject("C#", ++subjectsStartID));
        data.editSubjects(new Subject("Russian", ++subjectsStartID));
        data.editSubjects(new Subject("Assembler", ++subjectsStartID));
        data.editSubjects(new Subject("Web", ++subjectsStartID));


        addSubjectsForStudents();

        data.getTeachers().get(0).addSubjects(data.getSubjects().get(0), data.getSubjects().get(1));
        data.getTeachers().get(1).addSubjects(data.getSubjects().get(2), data.getSubjects().get(5), data.getSubjects().get(6), data.getSubjects().get(8));
        data.getTeachers().get(2).addSubjects(data.getSubjects().get(1), data.getSubjects().get(2), data.getSubjects().get(4));
        data.getTeachers().get(3).addSubjects(data.getSubjects().get(3), data.getSubjects().get(4), data.getSubjects().get(7));
    }

    private void addSubjectsForStudents() {
        List<Integer> subNumbers;

        for (User student : data.getStudents()) {
            subNumbers = createMas(new Random().nextInt(data.getSubjects().size()) + 1);
            for (Integer subNumber : subNumbers) {
                student.addSubjects(data.getSubjects().get(subNumber));
            }
        }
    }

    private List<Integer> createMas(int length) {
        List<Integer> mass = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            mass.add(rnd(data.getSubjects().size() - 1));
        }
        return mass;
    }

    private static int rnd(int max) {
        return (int) (Math.random() * ++max);
    }

    @Test
    void testingCreatingTimeTable() {
        Generator generatingTimeTable = new GeneratorImpl(data);

        generatingTimeTable.generatingTimeTable();
        for (Auditorium a :
                generatingTimeTable.getData().getAuditoriums()) {
            for (Lesson l :
                    a.getTimeTable().getLessons()) {
                System.out.println(l + "\n");
            }
            System.out.println();
            System.out.println();
        }
    }

    @Test
    void testingKPI() {
        Generator generatingTimeTable = new GeneratorImpl(data);

        generatingTimeTable.generatingTimeTable();
        KPI kpi = new KPI(generatingTimeTable.getData());
        System.out.println(kpi.getKpi() + "%");
        Assert.assertTrue(kpi.getKpi()<=100);
    }

    @Test
    void toStringStudents(){
        CreateJson createJson=new CreateJson(data);
        System.out.println(createJson.getHttpRequest());
    }
}
