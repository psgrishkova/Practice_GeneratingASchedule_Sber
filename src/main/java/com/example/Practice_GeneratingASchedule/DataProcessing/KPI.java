package com.example.Practice_GeneratingASchedule.DataProcessing;

import com.example.Practice_GeneratingASchedule.Entities.Lesson;
import com.example.Practice_GeneratingASchedule.Entities.User;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class KPI {
    private int kpi;
    private final Data data;

    public KPI(Data data) {
        this.data = data;
        CalculateKpi();
    }

    public int getKpi() {
        return kpi;
    }

    private void CalculateKpi() {
        int countOfStudyingDays = getCountOfStudyingDays();
        kpi = 100 - ((getMaxCountOfStudyingDays() / countOfStudyingDays) - countOfStudyingDays);
    }

    private int getCountOfStudyingDays() {
        List<LocalDateTime> dates = new LinkedList<>();
        for (User student :
                data.getStudents()) {
            for (Lesson lesson :
                    student.getTimeTable().getLessons()) {
                dates.add(lesson.getStartLessonDate());
            }
        }
        dates = dates.stream().distinct().collect(Collectors.toList());
        Collections.sort(dates);
        return dates.get(dates.size() - 1).getDayOfYear() - data.getStartDay().getDayOfYear() + 1;
    }

    private int getMaxCountOfStudyingDays() {
        int countOfStudyingDays = 0;
        for (User student :
                data.getStudents()) {
            countOfStudyingDays += student.getSubjects().size();
        }
        return countOfStudyingDays / 4 + 1;
    }


}
