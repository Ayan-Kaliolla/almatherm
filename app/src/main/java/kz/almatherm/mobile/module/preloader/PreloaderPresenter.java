package kz.almatherm.mobile.module.preloader;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;

import kz.almatherm.mobile.model.Category;

@InjectViewState
public class PreloaderPresenter extends MvpPresenter<PreloaderView> {
    private PreloaderModel model;

    public PreloaderPresenter() {
        this.model = new PreloaderModel(this);
    }

    public void loadData() {
        model.loadData();
    }

    public void onDataLoaded(ArrayList<Category> categories) {
        getViewState().completeLoad();
    }
}
