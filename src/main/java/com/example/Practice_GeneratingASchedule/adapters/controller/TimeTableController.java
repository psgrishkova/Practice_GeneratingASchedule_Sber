package com.example.Practice_GeneratingASchedule.adapters.controller;

import com.example.Practice_GeneratingASchedule.DataProcessing.Data;
import com.example.Practice_GeneratingASchedule.DataProcessing.Generator;
import com.example.Practice_GeneratingASchedule.DataProcessing.GeneratorImpl;
import com.example.Practice_GeneratingASchedule.DataProcessing.KPI;
import com.example.Practice_GeneratingASchedule.Entities.*;
import com.example.Practice_GeneratingASchedule.adapters.presentation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
public class TimeTableController {
    TimeTableRequest timeTableRequest;
    TimeTableResponse response;

    @GetMapping("getAllStudents")
    @ResponseBody
    List<ResourceDTO> getStudents(){
        return timeTableRequest.getStudents();
    }

    @GetMapping("getAllTeachers")
    @ResponseBody
    List<ResourceDTO> getTeachers(){
        return timeTableRequest.getTeachers();
    }

    @GetMapping("getAuditoriums")
    @ResponseBody
    List<AuditoriumDTO> getAuditoriums(){
        return timeTableRequest.getAuditoriums();
    }

    @GetMapping("getSubjects")
    @ResponseBody
    List<SubjectDTO> getSubjects(){
        return timeTableRequest.getSubjects();
    }

    @GetMapping("getTimeTable")
    @ResponseBody
    TimeTableResponse getTimeTable(){
        return response;
    }

    @PostMapping("getStud")
    @ResponseBody
    ResourceDTO getStud(@RequestParam String id){
        return timeTableRequest.getStudents().get(Integer.parseInt(id)-601);
    }

    @PostMapping("getTeach")
    @ResponseBody
    ResourceDTO getTeach(@RequestParam String id){
        return timeTableRequest.getTeachers().get(Integer.parseInt(id)-101);
    }

    @PostMapping("makeTimeTable")
    @ResponseBody
    TimeTableResponse makeTimeTable(@RequestBody TimeTableRequest request) {
        this.timeTableRequest=request;
        Data data = new Data(request.getStartDay());

        data.editAuditoriums(request.getAuditoriums().stream()
                .map(auditoriumDTO -> new Auditorium(auditoriumDTO.getNumber(), Integer.parseInt(auditoriumDTO.getTimeTableId())))
                .toArray(Auditorium[]::new));

        data.editStudents(request.getStudents().stream()
                .map(resourceDTO -> mapDtoToStudent(resourceDTO))
                .toArray(Student[]::new));

        data.editTeachers(request.getTeachers().stream()
                .map(resourceDTO -> mapDtoToTeacher(resourceDTO))
                .toArray(Teacher[]::new));


        List<String> studentsSubjectsIds = request.getStudents().stream()
                .flatMap(student -> student.getSubjects().stream()).map(SubjectDTO::getId).collect(Collectors.toList());

        List<String> teachersSubjectsIds = request.getTeachers().stream()
                .flatMap(teacher -> teacher.getSubjects().stream()).map(SubjectDTO::getId).collect(Collectors.toList());

        List<Subject> studentSubjects =
                data.getStudents().stream().flatMap(students -> students.getSubjects().stream())
                        .filter(subject -> studentsSubjectsIds.stream().anyMatch(studSubject -> subject.getSubjectID() == Integer.parseInt(studSubject))).collect(Collectors.toList());


        List<Subject> teacherSubjects =
                data.getTeachers().stream().flatMap(teacher -> teacher.getSubjects().stream())
                        .filter(subject -> teachersSubjectsIds.stream().anyMatch(teacherSubject -> subject.getSubjectID() == Integer.parseInt(teacherSubject))).collect(Collectors.toList());


        List<Subject> allSubjects = new ArrayList<>(teacherSubjects);
        allSubjects.addAll(studentSubjects);

        data.editSubjects(allSubjects.toArray(Subject[]::new));

        Generator generator = new GeneratorImpl(data); // можно было бы сделать синглтоном, если Data не был бы полем класса
        generator.generatingTimeTable();

        response = new TimeTableResponse();

        response.setEfficiency(new KPI(data).getKpi());
        List<LessonDTO> schedule = data.getAuditoriums()
                .stream().flatMap(auditorium -> auditorium.getTimeTable().getLessons().stream())
                .map(this::convertToLessonDto).collect(Collectors.toList());
        response.setLessonDTOS(schedule);
        return response;
    }

    private Student mapDtoToStudent(ResourceDTO resourceDTO) {
        Student student = new Student(resourceDTO.getName(), Integer.parseInt(resourceDTO.getId()), resourceDTO.getTimeTableId());
        student.addSubjects(resourceDTO.getSubjects()
                .stream().map(subjectDTO -> new Subject(subjectDTO.getName(), Integer.parseInt(subjectDTO.getId())))
                .toArray(Subject[]::new));
        return student;
    }


    private Teacher mapDtoToTeacher(ResourceDTO resourceDTO) {
        Teacher teacher = new Teacher(resourceDTO.getName(), Integer.parseInt(resourceDTO.getId()), resourceDTO.getTimeTableId());
        teacher.addSubjects(resourceDTO.getSubjects()
                .stream().map(subjectDTO -> new Subject(
                        subjectDTO.getName(),
                        Integer.parseInt(subjectDTO.getId())))
                .toArray(Subject[]::new));
        return teacher;
    }

    private LessonDTO convertToLessonDto(Lesson lesson) {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setAuditoriumNumber(String.valueOf(lesson.getAuditorium().getAuditoriumNumber()));
        lessonDTO.setStartLessonDateTIme(lessonDTO.getStartLessonDateTIme());
        lessonDTO.setTeacher(new ResourceDTO(lesson.getTeacher().getName(), String.valueOf(lesson.getTeacher().getId())));
        return lessonDTO;
    }

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }
}
