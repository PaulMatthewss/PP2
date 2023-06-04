package com.example.pp2;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class GroupRepository {

    private IGroupDao groupDao;
    private LiveData<List<SubjectGroup>> allSubjectGroups;
    private LiveData<List<Group>> allGroups;


    public GroupRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        groupDao = database.iGroupDao();
        allSubjectGroups = groupDao.getGroups();
        allGroups = groupDao.getAllGroups();
    }
    public void insert(Group group){
        new GroupRepository.InsertGroupAsyncTask(groupDao).execute(group);
    }
    public void update(Group group){
        new GroupRepository.UpdateGroupAsyncTask(groupDao).execute(group);
    }
    public void delete(Group group){
        new GroupRepository.DeleteGroupAsyncTask(groupDao).execute(group);
    }
    public LiveData<List<SubjectGroup>> getAllSubjectGroups(){
        return allSubjectGroups;
    }
    public LiveData<List<Group>> getAllGroups(){
        return allGroups;
    }
    private static class InsertGroupAsyncTask extends AsyncTask<Group, Void, Void> {
        private  IGroupDao groupDao;
        private  InsertGroupAsyncTask(IGroupDao groupDao){
            this.groupDao = groupDao;
        }
        @Override
        protected Void doInBackground(Group... groups) {
            groupDao.insertGroup(groups[0]);
            return null;
        }
    }
    private static class UpdateGroupAsyncTask extends AsyncTask<Group, Void, Void>{
        private IGroupDao groupDao;
        private UpdateGroupAsyncTask(IGroupDao groupDao){
            this.groupDao = groupDao;
        }
        @Override
        protected Void doInBackground(Group... groups) {
            groupDao.updateGroup(groups[0]);
            return null;
        }
    }
    private static class DeleteGroupAsyncTask extends AsyncTask<Group, Void, Void>{
        private IGroupDao groupDao;
        private DeleteGroupAsyncTask(IGroupDao groupDao){
            this.groupDao = groupDao;
        }
        @Override
        protected Void doInBackground(Group... groups) {
            groupDao.deleteGroup(groups[0]);
            return null;
        }
    }
}
