package kz.almatherm.mobile;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;

import kz.almatherm.mobile.model.db.AppDatabase;

public class AlmathermMobile extends Application {

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(this.getClass().getSimpleName(), "Starting application");
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database").fallbackToDestructiveMigration().build();
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
