package com.example.pp2.Repos;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.example.pp2.AppDatabase;
import com.example.pp2.Interfaces.IStudentDao;
import com.example.pp2.Entities.Student;

import java.util.List;

public class StudentRepository {
    public IStudentDao studentDao;
    private final LiveData<List<Student>> allStudents;
    public StudentRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        studentDao = database.iStudentDao();
        allStudents = studentDao.getAllStudents();
    }

    public void insert(Student student){
        new StudentRepository.InsertStudentAsyncTask(studentDao).execute(student);
    }
    public void update(Student student){
        new StudentRepository.UpdateStudentAsyncTask(studentDao).execute(student);
    }
    public void delete(Student student){
        new StudentRepository.DeleteStudentAsyncTask(studentDao).execute(student);
    }
    public LiveData<List<Student>> getAllStudent(){
        return allStudents;
    }
    private static class InsertStudentAsyncTask extends AsyncTask<Student, Void, Void> {
        private final IStudentDao studentDao;
        private  InsertStudentAsyncTask(IStudentDao studentDao){
            this.studentDao = studentDao;
        }
        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudent(students[0]);
            return null;
        }
    }
    private static class UpdateStudentAsyncTask extends AsyncTask<Student, Void, Void>{
        private final IStudentDao studentDao;
        private  UpdateStudentAsyncTask(IStudentDao studentDao){
            this.studentDao = studentDao;
        }
        @Override
        protected Void doInBackground(Student... students) {
            studentDao.updateStudent(students[0]);
            return null;
        }
    }
    private static class DeleteStudentAsyncTask extends AsyncTask<Student, Void, Void>{
        private final IStudentDao studentDao;
        private  DeleteStudentAsyncTask(IStudentDao studentDao){
            this.studentDao = studentDao;
        }
        @Override
        protected Void doInBackground(Student... students) {
            studentDao.deleteStudent(students[0]);
            return null;
        }
    }
}
