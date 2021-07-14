package com.example.Practice_GeneratingASchedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = {"classpath:applicationContext.xml"})
public class PracticeGeneratingAScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticeGeneratingAScheduleApplication.class, args);
    }

}
