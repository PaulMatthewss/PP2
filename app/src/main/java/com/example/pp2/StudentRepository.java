package com.example.pp2;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {
    public IStudentDao studentDao;
    private LiveData<List<Student>> allStudents;
    public StudentRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        studentDao = database.iStudentDao();
        allStudents = studentDao.getAllStudents();
    }

    public void insert(Student student){
        new StudentRepository.InsertSubjectAsyncTask(studentDao).execute(student);
    }
    public void update(Student student){
        new StudentRepository.UpdateSubjectAsyncTask(studentDao).execute(student);
    }
    public void delete(Student student){
        new StudentRepository.DeleteSubjectAsyncTask(studentDao).execute(student);
    }
    public LiveData<List<Student>> getAllStudent(){
        return allStudents;
    }
    private static class InsertSubjectAsyncTask extends AsyncTask<Student, Void, Void> {
        private  IStudentDao studentDao;
        private  InsertSubjectAsyncTask(IStudentDao studentDao){
            this.studentDao = studentDao;
        }
        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudent(students[0]);
            return null;
        }
    }
    private static class UpdateSubjectAsyncTask extends AsyncTask<Student, Void, Void>{
        private  IStudentDao studentDao;
        private  UpdateSubjectAsyncTask(IStudentDao studentDao){
            this.studentDao = studentDao;
        }
        @Override
        protected Void doInBackground(Student... students) {
            studentDao.updateStudent(students[0]);
            return null;
        }
    }
    private static class DeleteSubjectAsyncTask extends AsyncTask<Student, Void, Void>{
        private  IStudentDao studentDao;
        private  DeleteSubjectAsyncTask(IStudentDao studentDao){
            this.studentDao = studentDao;
        }
        @Override
        protected Void doInBackground(Student... students) {
            studentDao.deleteStudent(students[0]);
            return null;
        }
    }
}
