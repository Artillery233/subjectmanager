package com.subjectmanager.subjectmanager;

import android.app.Application;

import database.AppDatabase;

public class AppJava extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase.init(this);
    }
}
