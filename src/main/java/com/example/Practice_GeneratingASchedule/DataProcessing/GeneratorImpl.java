package com.example.Practice_GeneratingASchedule.DataProcessing;

import com.example.Practice_GeneratingASchedule.Entities.Auditorium;
import com.example.Practice_GeneratingASchedule.Entities.Lesson;
import com.example.Practice_GeneratingASchedule.Entities.Subject;
import com.example.Practice_GeneratingASchedule.Entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeneratorImpl implements Generator {
    private LocalDateTime currentDate;
    Data data;
    private final List<User> studentsWhoNeedATimeTable;


    public GeneratorImpl(Data data) {
        this.data = data;
        studentsWhoNeedATimeTable = new ArrayList<>();
        currentDate = data.getStartDay();
    }

    @Override
    public Data getData() {
        return data;
    }

    private void passageOfTime() {
        if (currentDate.getHour() < 12)
            currentDate = currentDate.plusMinutes(90);
        else {
            currentDate = currentDate.plusDays(1);
            currentDate = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getDayOfMonth(), 8, 0, 0);
        }
    }

    private List<User> selectUsersBySubject(String currentSubjectName, List<User> users) {
        return users.stream()
                .filter(user -> user.getSubjects().stream()
                        .anyMatch(subject -> subject.getNameOfSubject().equals(currentSubjectName)))
                .collect(Collectors.toList());
    }

    private List<Auditorium> getFreeAuditoriums() {
        List<Auditorium> freeAuditoriums = new ArrayList<>();
        for (Auditorium auditorium :
                data.getAuditoriums()) {
            if (auditorium.getTimeTable().getLessons().stream()
                    .noneMatch(lesson -> lesson.getStartLessonDate().toString()
                            .equals(currentDate.toString()))) {
                freeAuditoriums.add(auditorium);
            }
        }
        return freeAuditoriums;
    }

    private List<User> selectUsersByTime(List<User> users) {
        List<User> testUsers = new ArrayList<>(users);
        testUsers.removeIf(user -> user.getTimeTable().getLessons().stream()
                .anyMatch(lesson -> lesson.getStartLessonDate().toString()
                        .equals(currentDate.toString())));
        return testUsers;
    }


    private List<User> setFreeTime(List<User> users) {
        List<User> testUsers = new ArrayList<>(users);
        testUsers = selectUsersByTime(testUsers);
        if (testUsers.size() == 0) {
            passageOfTime();
            testUsers = setFreeTime(users);
        }
        return testUsers;
    }

    private boolean isSubjectInTimeTable(User student, Subject subject) {
        return student.getTimeTable().getLessons().stream()
                .anyMatch(lesson -> lesson.getSubject().getNameOfSubject().equals(subject.getNameOfSubject()));
    }

    public void generatingTimeTable() {
        studentsWhoNeedATimeTable.addAll(data.getStudents());
        while (studentsWhoNeedATimeTable.size() != 0) {
            User student = studentsWhoNeedATimeTable.get(0);
            currentDate = data.getStartDay();
            int countOfSubjects = student.getSubjects().size();
            if (countOfSubjects != 0) {
                for (int i = 0; i < countOfSubjects; i++) {
                    if (!isSubjectInTimeTable(student, student.getSubjects().get(i)))
                        createLesson(student, student.getSubjects().get(i));
                }
            }
            studentsWhoNeedATimeTable.remove(student);
        }
    }

    public void createLesson(User student, Subject currentSubject) {

        //собрать студентов и преподов с заданным предметом
        List<User> students = selectUsersBySubject(currentSubject.getNameOfSubject(), studentsWhoNeedATimeTable);
        List<User> teachers = selectUsersBySubject(currentSubject.getNameOfSubject(), data.getTeachers());

        //найти время, удобное для всех
        students = setFreeTime(students);
        teachers = setFreeTime(teachers);

        //найти сободные аудитории
        List<Auditorium> auditoriums = getFreeAuditoriums();

        Lesson lesson = new Lesson(teachers.get(0), currentSubject, students.toArray(User[]::new));
        lesson.setStartLessonDate(currentDate);
        lesson.setAuditorium(auditoriums.get(0));

        //созданную пару добавили всем в расписание
        for (User s :
                students) {
            s.addLessonsInTimeTable(lesson);
        }
        teachers.get(0).addLessonsInTimeTable(lesson);
        auditoriums.get(0).editTimeTable(lesson);
        passageOfTime();
    }
}
