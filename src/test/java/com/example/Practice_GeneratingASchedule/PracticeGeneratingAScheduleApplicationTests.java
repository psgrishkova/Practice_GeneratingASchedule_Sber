package com.example.Practice_GeneratingASchedule;

import com.example.Practice_GeneratingASchedule.DataProcessing.Data;
import com.example.Practice_GeneratingASchedule.DataProcessing.GeneratingTimeTable;
import com.example.Practice_GeneratingASchedule.DataProcessing.KPI;
import com.example.Practice_GeneratingASchedule.Entities.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

//@SpringBootTest
class PracticeGeneratingAScheduleApplicationTests {
    private static final LocalDateTime startDay = LocalDateTime.of(2021, 7, 8, 8, 0);

    @Test
    void testingCreatingTimeTable() {
        Data data = new Data(startDay);
        GeneratingTimeTable generatingTimeTable = new GeneratingTimeTable(data);
        for (User student:
             data.getStudents()) {
            System.out.println(student);
        }
        System.out.println();

      //  LocalDateTime calendar = LocalDateTime.of(2021, 7, 8, 8, 0, 0);
      //  generatingTimeTable.setCurrentDate(calendar);

        generatingTimeTable.generatingTimeTable();

        int countOfRecords = 0;
        List<User> students = generatingTimeTable.getData().getStudents();
        for (User student : students) {
            List<Lesson> lessons = student.getTimeTable().getLessons();
            System.out.println(student.getName());
            for (Lesson lesson : lessons) {
                System.out.println(lesson + "\n");
                countOfRecords++;
            }
        }

    }

    @Test
    void testingDates() {
        Data data = new Data(startDay);
        GeneratingTimeTable generatingTimeTable = new GeneratingTimeTable(data);

        generatingTimeTable.generatingTimeTable();
        KPI kpi = new KPI(generatingTimeTable.getData());

        Assert.assertNotNull(kpi);
    }

    @Test
    void testingCreatingData() {
        Data data = new Data(startDay);
        for (User student :
                data.getStudents()) {
            System.out.println(student);
        }
        Assert.assertNotNull(data);
    }
}
