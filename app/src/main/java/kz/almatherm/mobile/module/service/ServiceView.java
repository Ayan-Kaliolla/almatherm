package kz.almatherm.mobile.module.service;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import kz.almatherm.mobile.model.Service;

interface ServiceView extends MvpView {
    void showData(List<Service> services);
}
