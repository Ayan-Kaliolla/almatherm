package kz.almatherm.mobile.module.addresses;

import com.arellomobile.mvp.MvpView;

import java.util.ArrayList;

import kz.almatherm.mobile.model.MapItem;

public interface AddressesView extends MvpView{
    void onDataLoaded(ArrayList<MapItem> mapItems);
}
