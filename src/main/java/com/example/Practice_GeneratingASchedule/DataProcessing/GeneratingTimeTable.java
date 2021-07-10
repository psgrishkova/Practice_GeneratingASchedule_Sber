package com.example.Practice_GeneratingASchedule.DataProcessing;

import com.example.Practice_GeneratingASchedule.Entities.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GeneratingTimeTable implements Generating {
    private LocalDateTime currentDate;
    Data data;
    private final List<User> studentsWhoNeedATimeTable;


    public GeneratingTimeTable(Data data) {
        this.data = data;
        studentsWhoNeedATimeTable = new ArrayList<>();
        currentDate = data.getStartDay();
    }

    @Override
    public Data getData() {
        return data;
    }

    public void setCurrentDate(LocalDateTime currentDate) {
        this.currentDate = currentDate;
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

    private int selectUsersByTime(List<User> users) {
        List<User> testUsers = new ArrayList<>(users);
        testUsers.removeIf(user -> user.getTimeTable().getLessons().stream()
                .anyMatch(lesson -> lesson.getStartLessonDate().toString()
                        .equals(currentDate.toString())));
        return testUsers.size();
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

    private void setFreeTime(List<User> students, List<User> teachers) {
        if (selectUsersByTime(students) == 0 || selectUsersByTime(teachers) == 0) {
            passageOfTime();
            setFreeTime(students, teachers);
        }
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
        setFreeTime(students, teachers);

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




/*
кладбище методов



    private List<User> searchingSubjectInTimeTable(List<User> users, Subject subject) {
        users.removeIf(user -> user.getTimeTable().getLessons().stream()
                .anyMatch(lesson -> lesson.getSubject().getNameOfSubject().equals(subject.getNameOfSubject())));
        return users;
    }

    public boolean isPlanComplete(Student student, String subjectTitle) {
        return student.getTimeTable().getLessons().stream().anyMatch(lesson -> lesson.getSubject().getNameOfSubject().equals(subjectTitle));
    }

    public void getNeedSubject() {

        Subject currentSubject = student.getSubjects().get(0);
        //если у студента уже есть этот предмет в расписании, берем другой
        if (isPlanComplete(student, currentSubject.getNameOfSubject())) {
            currentSubject = student.getSubjects().get(1);
        }

        return currentSubject;
    }


    public void generatingNewLesson() {
        studentsWhoNeedATimeTable = students;
        //while (studentsWhoNeedATimeTable.size() != 0) {
        //берем студента
        Student student = studentsWhoNeedATimeTable.get(0);
        //берем предмет, который нужно провести


        //находим студентов, которым нужна та же пара и они свободны в заданное время
        List<Student> freeStudents = getFreeStudents(currentSubject.getNameOfSubject());

        //отобрать преподов по предмету
        List<Teacher> freeTeacher = getFreeTeachers(currentSubject.getNameOfSubject());

        //теперь нужно найти аудитории, надо проверить, есть ли на это время там занятие
        List<Auditorium> freeAuditoriums = getFreeAuditoriums();


        //если препод и аудитория нашлись, то можно добавлять в расписание
        if (freeTeacher.size() != 0 && freeAuditoriums.size() != 0) {
            Lesson lesson = new Lesson(freeTeacher.get(0), currentSubject, freeStudents.toArray(Student[]::new));
            lesson.setStartLessonDate(currentDate);
            lesson.setAuditorium(freeAuditoriums.get(0));

            //созданную пару добавили всем в расписание
            for (Student s :
                    students) {
                s.addLessonsInTimeTable(lesson);
            }
            freeTeacher.get(0).addLessonsInTimeTable(lesson);
            freeAuditoriums.get(0).editTimeTable(lesson);
        } else {
            //перебирать время до конца недели
        }
        passageOfTime();
        // }
        //иначе нужно взять другое время
        // else {
        //     passageOfTime();
        //      generatingNewLesson();
        //   }
        //}
    }



    public List<Student> getFreeStudents(String currentSubjectName) {
        List<Student> freeStudents = studentsWhoNeedATimeTable.stream()
                .filter(student -> student.getSubjects().stream()
                        .anyMatch(subject -> subject.getNameOfSubject().equals(currentSubjectName))).collect(Collectors.toList());
        //исключить из списка студентов, у которых занято это время
        freeStudents.removeIf(student -> student.getTimeTable().getLessons().stream()
                .anyMatch(lesson -> lesson.getStartLessonDate().toString()
                        .equals(currentDate.toString())));
        return freeStudents;
    }

    public List<Teacher> getFreeTeachers(String currentSubjectName) {
        List<Teacher> freeTeacher = teachers.stream()
                .filter(teacher -> teacher.getSubjects().stream()
                        .anyMatch(subject -> subject.getNameOfSubject().equals(currentSubjectName))).collect(Collectors.toList());
        //исключить из списка преподов, у которых занято это время
        freeTeacher.removeIf(teacher -> teacher.getTimeTable().getLessons().stream()
                .anyMatch(lesson -> lesson.getStartLessonDate().toString()
                        .equals(currentDate.toString())));
        return freeTeacher;
    }



 */
