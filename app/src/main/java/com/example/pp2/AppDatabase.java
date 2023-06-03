package com.example.pp2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Subject.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract ISubjectsDao iSubjectsDao();

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

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };
    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void>{
        private  ISubjectsDao subjectsDao;

        private PopulateDBAsyncTask(AppDatabase db){
            subjectsDao = db.iSubjectsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            subjectsDao.insertSubject(new Subject("Разработка мобильных приложений", "Java", "Android Studio"));
            subjectsDao.insertSubject(new Subject("Разработка игр", "С#", "Visual Studio"));
            subjectsDao.insertSubject(new Subject("Веб разработка", "Php", "VS Code"));
            return null;
        }
    }
}
