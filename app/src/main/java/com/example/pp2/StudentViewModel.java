package com.example.pp2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private  StudentRepository studentRepository;
    private LiveData<List<Student>> allStudents;
    public StudentViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
        allStudents = studentRepository.getAllStudent();
    }
    public void insert(Student student){
        studentRepository.insert(student);
    }
    public void update(Student student){
        studentRepository.update(student);
    }
    public void delete(Student student){
        studentRepository.delete(student);
    }
    public LiveData<List<Student>> getAllStudents(){
        return allStudents;
    }
}