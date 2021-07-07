package com.example.Practice_GeneratingASchedule;

import java.util.ArrayList;
import java.util.List;

public class generating {
    public void gen(List<Student> AllStudents, Subject subject, List<Teacher> AllTeachers){
        //получим список студентов с заданным предметом
        List<Student> students=new ArrayList<>();
        for (Student s :
             AllStudents) {
            for (Subject sub:
                 s.getStudyPlan()) {
                if(sub.getNameOfSubject().equals(subject))
                    students.add(s);
            }
        }

        //найдем преподов
        List<Teacher> teachers=new ArrayList<>();
        for (Teacher t:
             AllTeachers) {
            for (Subject sub:
                 t.getSubjects()) {
                if(sub.equals(subject))
                    teachers.add(t);
            }
        }

    }
}
