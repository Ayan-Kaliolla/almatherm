package kz.almatherm.mobile.module.sub_service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kz.almatherm.mobile.AlmathermMobile;
import kz.almatherm.mobile.model.Service;
import kz.almatherm.mobile.model.SubService;
import kz.almatherm.mobile.model.db.AppDatabase;

class SubServiceModel {
    AppDatabase database;
    SubServicePresenter presenter;

    public SubServiceModel(Context context, SubServicePresenter presenter) {
        database = ((AlmathermMobile) context.getApplicationContext()).getDatabase();
        this.presenter = presenter;
    }


    public void loadSubServices(int subCategoryId) {
        List<SubService> services = new ArrayList<>();
        Observable.just("a")
                .observeOn(Schedulers.io())
                .doOnNext(e -> services.addAll(database.getSubServiceDao().getSubServiceByParentId(subCategoryId)))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> presenter.onDataLoaded(services))
                .subscribe();
    }
}
