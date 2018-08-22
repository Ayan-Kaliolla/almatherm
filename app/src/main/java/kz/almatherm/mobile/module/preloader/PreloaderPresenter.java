package kz.almatherm.mobile.module.preloader;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kz.almatherm.mobile.AlmathermMobile;
import kz.almatherm.mobile.model.Category;

@InjectViewState
public class PreloaderPresenter extends MvpPresenter<PreloaderView> {
    private PreloaderModel model;
    private Context context;
    public PreloaderPresenter(Context context) {
        this.model = new PreloaderModel(this);
        this.context = context;
    }

    public void loadData() {
        model.loadData();
    }

    public void onDataLoaded(ArrayList<Category> categories) {
        Observable.just(categories)
                .observeOn(Schedulers.io())
                .doOnNext(e -> ((AlmathermMobile) context.getApplicationContext()).getDatabase().getCategoryDao().insert(e))
        .observeOn(AndroidSchedulers.mainThread())
        .doOnComplete(
                getViewState()::completeLoad
        )
        .subscribe();

    }
}
