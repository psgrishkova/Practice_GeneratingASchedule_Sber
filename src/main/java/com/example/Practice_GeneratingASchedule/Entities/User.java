package com.example.Practice_GeneratingASchedule.Entities;

import java.util.List;

public interface User {
    void addLessonsInTimeTable(Lesson... lessons);

    void addSubjects(Subject... subjects);

    List<Subject> getSubjects();

    TimeTable getTimeTable();

    String getName();

    String toString();
}

