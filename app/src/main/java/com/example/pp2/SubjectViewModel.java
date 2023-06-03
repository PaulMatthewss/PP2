package com.example.pp2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SubjectViewModel extends AndroidViewModel {
    private  SubjectRepository subjectRepository;
    private LiveData<List<Subject>> allSubjects;
    public SubjectViewModel(@NonNull Application application) {
        super(application);
        subjectRepository = new SubjectRepository(application);
        allSubjects = subjectRepository.getAllSubjects();
    }
    public void insert(Subject subject){
        subjectRepository.insert(subject);
    }
    public void update(Subject subject){
        subjectRepository.update(subject);
    }
    public void delete(Subject subject){
        subjectRepository.delete(subject);
    }
    public LiveData<List<Subject>> getAllSubjects(){
        return allSubjects;
    }
}
