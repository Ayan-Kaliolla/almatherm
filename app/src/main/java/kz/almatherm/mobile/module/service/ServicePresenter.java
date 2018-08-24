package kz.almatherm.mobile.module.service;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import kz.almatherm.mobile.model.Service;

@InjectViewState
public class ServicePresenter extends MvpPresenter<ServiceView>{
    private Context context;
    private ServiceModel model;
    public ServicePresenter(Context context) {
        this.context = context;
        this.model = new ServiceModel(context, this);
    }

    public void loadServices(int subCategoryId) {
        model.loadServices(subCategoryId);
    }

    public void onDataLoaded(List<Service> services) {
        getViewState().showData(services);
    }
}
