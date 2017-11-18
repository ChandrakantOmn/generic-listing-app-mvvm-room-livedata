package com.madrzak.mygenericlistingapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.facebook.stetho.Stetho;
import com.madrzak.mygenericlistingapp.data.AppDatabase;
import com.madrzak.mygenericlistingapp.data.AppDatabaseHelper;

import timber.log.Timber;

/**
 * Created by Łukasz on 04/11/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        Stetho.initializeWithDefaults(this);


        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "lukasz-secret-data").build();

        AppDatabaseHelper.init(db);

    }
}
