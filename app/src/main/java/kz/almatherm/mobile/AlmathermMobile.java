package kz.almatherm.mobile;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import kz.almatherm.mobile.model.db.AppDatabase;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;

public class AlmathermMobile extends Application {

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database").fallbackToDestructiveMigration().build();
//        Picasso.setSingletonInstance(
//                new Picasso.Builder(getApplicationContext())
//                        .downloader(new OkHttp3Downloader(new OkHttpClient.Builder()
//                                .connectTimeout(10, TimeUnit.SECONDS)
//                                .readTimeout(10, TimeUnit.SECONDS)
//                                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
//                                .cache(new Cache(getApplicationContext().getCacheDir(), 1024 * 1024 * 10))
//                                .build()))
//                        .loggingEnabled(true)
//                        .indicatorsEnabled(true)
//                        .build()
//        );
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
