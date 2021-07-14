package com.example.Practice_GeneratingASchedule;

import com.example.Practice_GeneratingASchedule.DataProcessing.Data;

public class CreateJson {
    private String httpRequest;
    private Data data;
    final char dm = (char) 34;

    public CreateJson(Data data) {
        this.data = data;
        createJsonFromString();
    }

    public String getHttpRequest() {
        return httpRequest;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private void createJsonFromString() {
        StringBuilder res = new StringBuilder("curl -XPOST localhost:8080/api/v1/makeTimeTable -H 'Content-Type: application/json' -d '{" +
                dm + "students" + dm + ":[" + data.getStudents().get(0));
        for (int i = 1; i < data.getStudents().size(); i++) {
            res.append(",").append(data.getStudents().get(i));
        }
        res.append("]," + dm + "teachers" + dm + ":[").append(data.getTeachers().get(0));
        for (int i = 1; i < data.getTeachers().size(); i++) {
            res.append(",").append(data.getTeachers().get(i));
        }
        res.append("]," + dm + "subjects" + dm + ":[").append(data.getSubjects().get(0));
        for (int i = 1; i < data.getSubjects().size(); i++) {
            res.append(",").append(data.getSubjects().get(i));
        }
        res.append("]," + dm + "auditoriums" + dm + ":" + "[").append(data.getAuditoriums().get(0));
        for (int i = 1; i < data.getAuditoriums().size(); i++) {
            res.append(", ").append(data.getAuditoriums().get(i));
        }
        res.append("]," + dm + "startDay" + dm + ":" + dm).append(data.getStartDay()).append(dm).append("}'");
        httpRequest = res.toString();
    }
}
