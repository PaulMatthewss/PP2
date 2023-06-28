package com.example.pp2;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LessonRepository {
    public ILessonDao lessonDao;
    private LiveData<List<Lesson>> allLessons;
    public LessonRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        lessonDao = database.iLessonDao();
        allLessons = lessonDao.getAllLessons();
    }
    public void insert(Lesson lesson){
        new LessonRepository.InsertLessonAsyncTask(lessonDao).execute(lesson);
    }
    public void update(Lesson lesson){
        new LessonRepository.UpdateLessonAsyncTask(lessonDao).execute(lesson);
    }
    public void delete(Lesson lesson){
        new LessonRepository.DeleteLessonAsyncTask(lessonDao).execute(lesson);
    }
    public LiveData<List<Lesson>> getAllLessons(){
        return allLessons;
    }
    private static class InsertLessonAsyncTask extends AsyncTask<Lesson, Void, Void> {
        private  ILessonDao lessonDao;
        private  InsertLessonAsyncTask(ILessonDao lessonDao){
            this.lessonDao = lessonDao;
        }
        @Override
        protected Void doInBackground(Lesson... lessons) {
            lessonDao.insertLesson(lessons[0]);
            return null;
        }
    }
    private static class UpdateLessonAsyncTask extends AsyncTask<Lesson, Void, Void>{
        private  ILessonDao lessonDao;
        private  UpdateLessonAsyncTask(ILessonDao lessonDao){
            this.lessonDao = lessonDao;
        }
        @Override
        protected Void doInBackground(Lesson... lessons) {
            lessonDao.updateLesson(lessons[0]);
            return null;
        }
    }
    private static class DeleteLessonAsyncTask extends AsyncTask<Lesson, Void, Void>{
        private  ILessonDao lessonDao;
        private  DeleteLessonAsyncTask(ILessonDao lessonDao){
            this.lessonDao = lessonDao;
        }
        @Override
        protected Void doInBackground(Lesson... lessons) {
            lessonDao.deleteLesson(lessons[0]);
            return null;
        }
    }
}
