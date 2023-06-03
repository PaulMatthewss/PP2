package com.example.pp2;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SubjectRepository {
    private ISubjectsDao subjectsDao;
    private LiveData<List<Subject>> allSubjects;

    public SubjectRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        subjectsDao = database.iSubjectsDao();
        allSubjects = subjectsDao.getAll();
    }

    public void insert(Subject subject){
        new InsertSubjectAsyncTask(subjectsDao).execute(subject);
    }
    public void update(Subject subject){
        new UpdateSubjectAsyncTask(subjectsDao).execute(subject);
    }
    public void delete(Subject subject){
        new DeleteSubjectAsyncTask(subjectsDao).execute(subject);
    }
    public LiveData<List<Subject>> getAllSubjects(){
        return allSubjects;
    }
    private static class InsertSubjectAsyncTask extends AsyncTask<Subject, Void, Void>{
        private  ISubjectsDao subjectsDao;
        private  InsertSubjectAsyncTask(ISubjectsDao subjectsDao){
            this.subjectsDao = subjectsDao;
        }
        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectsDao.insertSubject(subjects[0]);
            return null;
        }
    }
    private static class UpdateSubjectAsyncTask extends AsyncTask<Subject, Void, Void>{
        private  ISubjectsDao subjectsDao;
        private  UpdateSubjectAsyncTask(ISubjectsDao subjectsDao){
            this.subjectsDao = subjectsDao;
        }
        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectsDao.updateSubject(subjects[0]);
            return null;
        }
    }
    private static class DeleteSubjectAsyncTask extends AsyncTask<Subject, Void, Void>{
        private  ISubjectsDao subjectsDao;
        private  DeleteSubjectAsyncTask(ISubjectsDao subjectsDao){
            this.subjectsDao = subjectsDao;
        }
        @Override
        protected Void doInBackground(Subject... subjects) {
            subjectsDao.deleteSubject(subjects[0]);
            return null;
        }
    }
}
