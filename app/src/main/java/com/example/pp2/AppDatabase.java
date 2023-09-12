package com.example.pp2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.pp2.Entities.Group;
import com.example.pp2.Entities.Lesson;
import com.example.pp2.Entities.Student;
import com.example.pp2.Entities.Subject;
import com.example.pp2.Interfaces.IGroupDao;
import com.example.pp2.Interfaces.ILessonDao;
import com.example.pp2.Interfaces.IStudentDao;
import com.example.pp2.Interfaces.ISubjectsDao;

@Database(entities = {Subject.class, Group.class, Student.class, Lesson.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract ISubjectsDao iSubjectsDao();
    public abstract IGroupDao iGroupDao();
    public abstract IStudentDao iStudentDao();
    public abstract ILessonDao iLessonDao();

    public static  synchronized  AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return  instance;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
