package kz.almatherm.mobile.module.addresses;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.IOException;
import java.util.ArrayList;

import kz.almatherm.mobile.model.MapItem;

@InjectViewState
public class AddressesPresenter extends MvpPresenter<AddressesView> {
    private AddressesModel model;
    private Context context;

    public AddressesPresenter(Context context) {
        model = new AddressesModel(this, context);
        this.context = context;
    }

    public void loadMapItems() {
        model.loadMapItems();
    }

    public void onDataLoaded(ArrayList<MapItem> mapItems) {
        getViewState().onDataLoaded(mapItems);
    }
}
