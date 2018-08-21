package kz.almatherm.mobile;

import android.app.Application;
import android.util.Log;

public class AlmathermMobile extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(this.getClass().getSimpleName(), "Starting application");
    }
}
