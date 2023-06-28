package com.example.pp2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LessonViewModel extends AndroidViewModel {
    private LessonRepository lessonRepository;
    private LiveData<List<Lesson>> allLessons;
    public LessonViewModel(@NonNull Application application) {
        super(application);
        lessonRepository = new LessonRepository(application);
        allLessons = lessonRepository.getAllLessons();
    }
    public void insert(Lesson lesson){
        lessonRepository.insert(lesson);
    }
    public void update(Lesson lesson){
        lessonRepository.update(lesson);
    }
    public void delete(Lesson lesson){
        lessonRepository.delete(lesson);
    }
    public LiveData<List<Lesson>> getAllLessons(){
        return allLessons;
    }
}
