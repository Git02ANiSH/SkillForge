package com.skillforge.app.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "subjects")
public class SubjectEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String subjectName;
    private String teacherName;
    private String roomNumber;
    private String day;
    private String startTime;
    private String endTime;

    public SubjectEntity(String subjectName,
                         String teacherName,
                         String roomNumber,
                         String day,
                         String startTime,
                         String endTime) {

        this.subjectName = subjectName;
        this.teacherName = teacherName;
        this.roomNumber = roomNumber;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}