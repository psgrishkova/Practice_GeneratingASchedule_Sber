package com.example.Practice_GeneratingASchedule.DataProcessing;

import com.example.Practice_GeneratingASchedule.Entities.Subject;
import com.example.Practice_GeneratingASchedule.Entities.User;

public interface Generating {
    void createLesson(User student, Subject currentSubject);

    void generatingTimeTable();

    Data getData();
}
