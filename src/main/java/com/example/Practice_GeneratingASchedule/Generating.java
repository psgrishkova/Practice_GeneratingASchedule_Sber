package com.example.Practice_GeneratingASchedule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class Generating {
    private LocalDateTime currentDate;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Auditorium> auditoriums;


    public Generating() {

        teachers = new ArrayList<>();
        students = new ArrayList<>();
        auditoriums=new ArrayList<>();
    }

    public LocalDateTime getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDateTime currentDate) {
        this.currentDate = currentDate;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Teacher... teachers) {
        for (Teacher t :
                teachers) {
            this.teachers.add(t);
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(Student... students) {
        for (Student s :
                students) {
            this.students.add(s);
        }
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(Auditorium... auditoriums) {
        for (Auditorium a :
                auditoriums) {
            this.auditoriums.add(a);
        }
    }

    public void passageOfTime() {
        if (currentDate.getHour() < 12)
            currentDate=currentDate.plusMinutes(90);
        else {
           currentDate= currentDate.plusDays(1);
            currentDate= LocalDateTime.of(currentDate.getYear(),currentDate.getMonth(),currentDate.getDayOfMonth(),8,0,0);
        }
    }

    public void generatingNewLesson() {
        Student student = students.get(0);
        //(работет)  проверить, сободно ли у студента currentDate, если нет, перейти к след паре и тд
        if (!student.getTimeTable().getLessons().stream()
                .anyMatch(lesson -> lesson.getStartLessonDate().toString()
                        .equals(currentDate.toString()))) {

            //берем предмет, который нужно провести
            Subject currentSubject = student.getStudyPlan().get(0);
            if(isPlanComplete(student,currentSubject.getNameOfSubject())){
                currentSubject=student.getStudyPlan().get(1);
            }


            //отобрать преподов по предмету
            List<Teacher> freeTeacher=getFreeTeachers(currentSubject.getNameOfSubject());

            //теперь нужно найти аудитории, надо проверить, есть ли на это время там занятие
            List<Auditorium> freeAuditoriums=getFreeAuditoriums();


            //если препод и аудитория нашлись, то можно добавлять в расписание
            if (freeTeacher.size() != 0 && freeAuditoriums.size() != 0) {
                Lesson lesson = new Lesson(freeTeacher.get(0), currentSubject, student);
                lesson.setStartLessonDate(currentDate);
                lesson.setAuditorium(freeAuditoriums.get(0));

                //созданную пару добавили всем в расписание
                student.editTimeTable(lesson);
                freeTeacher.get(0).editTimeTable(lesson);
                freeAuditoriums.get(0).editTimeTable(lesson);
            } else {
                //перебирать время до конца недели
            }
            passageOfTime();
        }
        //иначе нужно взять другое время
        else {
            passageOfTime();
            generatingNewLesson();
        }
    }


    public List<Teacher> getFreeTeachers(String currentSubjectName){
        List<Teacher> freeTeacher = teachers.stream()
                .filter(teacher -> teacher.getSubjects().stream()
                        .anyMatch(subject -> subject.getNameOfSubject().equals(currentSubjectName))).collect(Collectors.toList());



        //исключить из списка преподов, у которых занято это время
        for (Teacher teacher :
                freeTeacher) {
            if (teacher.getTimeTable().getLessons().stream()
                    .anyMatch(lesson -> lesson.getStartLessonDate().toString()
                            .equals(currentDate.toString()))) {
                freeTeacher.remove(teacher);
            }
        }
        return freeTeacher;
    }

    public List<Auditorium> getFreeAuditoriums(){
        List<Auditorium> freeAuditoriums = new ArrayList<>();
        for (Auditorium auditorium:
                auditoriums) {
            if (!auditorium.getTimeTable().getLessons().stream()
                    .anyMatch(lesson -> lesson.getStartLessonDate().toString()
                            .equals(currentDate.toString()))) {
                freeAuditoriums.add(auditorium);
            }
        }
        return freeAuditoriums;
    }

    public boolean isPlanComplete(Student student, String subjectTitle){
       return student.getTimeTable().getLessons().stream().anyMatch(lesson -> lesson.getSubject().getNameOfSubject().equals(subjectTitle));
    }
}
