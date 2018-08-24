package kz.almatherm.mobile.module.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kz.almatherm.mobile.AlmathermMobile;
import kz.almatherm.mobile.model.Service;
import kz.almatherm.mobile.model.db.AppDatabase;

class ServiceModel {
    AppDatabase database;
    ServicePresenter presenter;
    public ServiceModel(Context context, ServicePresenter presenter) {
        database = ((AlmathermMobile) context.getApplicationContext()).getDatabase();
        this.presenter = presenter;
    }


    public void loadServices(int subCategoryId) {
        List<Service> services = new ArrayList<>();
        Observable.just("a")
                .observeOn(Schedulers.io())
                .doOnNext(e -> services.addAll(database.getServiceDao().getServiceByParentId(subCategoryId)))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> presenter.onDataLoaded(services))
                .subscribe();
    }
}
