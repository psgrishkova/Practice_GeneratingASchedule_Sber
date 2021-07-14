package com.example.Practice_GeneratingASchedule.Entities;

import java.util.List;

public interface User {
    void addLessonsInTimeTable(Lesson... lessons);

    void addSubjects(Subject... subjects);

    List<Subject> getSubjects();

    TimeTable getTimeTable();

    String getName();

    int getId();


    //в этом необходимости нет - toString и так есть в Object
    String toString();
}

