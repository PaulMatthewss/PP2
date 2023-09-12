package com.example.pp2.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pp2.Entities.Group;
import com.example.pp2.Repos.GroupRepository;

import java.util.List;

public class GroupViewModel extends AndroidViewModel {
    private final GroupRepository groupRepository;
    private final LiveData<List<Group>> allGroups;
    public GroupViewModel(@NonNull Application application){
        super(application);
        groupRepository = new GroupRepository(application);
        allGroups = groupRepository.getAllGroups();
    }
    public void insert(Group group){
        groupRepository.insert(group);
    }
    public void update(Group group){
        groupRepository.update(group);
    }
    public void delete(Group group){
        groupRepository.delete(group);
    }
    public LiveData<List<Group>> getAllGroups(){
        return allGroups;
    }
}
