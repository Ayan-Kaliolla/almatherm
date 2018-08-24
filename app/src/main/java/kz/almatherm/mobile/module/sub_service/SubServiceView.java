package kz.almatherm.mobile.module.sub_service;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import kz.almatherm.mobile.model.Service;
import kz.almatherm.mobile.model.SubService;

interface SubServiceView extends MvpView {
    void showData(List<SubService> services);
}
