package com.skillforge.app.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.skillforge.app.data.entity.SubjectEntity;

import java.util.List;

@Dao
public interface SubjectDao {

    @Insert
    void insertSubject(SubjectEntity subject);

    @Update
    void updateSubject(SubjectEntity subject);

    @Delete
    void deleteSubject(SubjectEntity subject);

    @Query("SELECT * FROM subjects ORDER BY day ASC, startTime ASC")
    List<SubjectEntity> getAllSubjects();
}