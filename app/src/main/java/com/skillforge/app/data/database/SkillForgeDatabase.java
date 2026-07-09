package com.skillforge.app.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.skillforge.app.data.dao.SubjectDao;
import com.skillforge.app.data.entity.SubjectEntity;

@Database(
        entities = {SubjectEntity.class},
        version = 1,
        exportSchema = false
)
public abstract class SkillForgeDatabase extends RoomDatabase {

    private static SkillForgeDatabase instance;

    public abstract SubjectDao subjectDao();

    public static synchronized SkillForgeDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            SkillForgeDatabase.class,
                            "skillforge_database"
                    )
                    .fallbackToDestructiveMigration(false)
                    .allowMainThreadQueries() // Temporary (we'll remove this later)
                    .build();
        }

        return instance;
    }
}