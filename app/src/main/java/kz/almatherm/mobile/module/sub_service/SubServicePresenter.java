package kz.almatherm.mobile.module.sub_service;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import kz.almatherm.mobile.model.Service;
import kz.almatherm.mobile.model.SubService;

@InjectViewState
public class SubServicePresenter extends MvpPresenter<SubServiceView>{
    private Context context;
    private SubServiceModel model;
    public SubServicePresenter(Context context) {
        this.context = context;
        this.model = new SubServiceModel(context, this);
    }

    public void loadSubServices(int subCategoryId) {
        model.loadSubServices(subCategoryId);
    }

    public void onDataLoaded(List<SubService> services) {
        getViewState().showData(services);
    }
}
