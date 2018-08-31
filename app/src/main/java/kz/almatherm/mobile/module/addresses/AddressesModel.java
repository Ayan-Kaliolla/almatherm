package kz.almatherm.mobile.module.addresses;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kz.almatherm.mobile.AlmathermMobile;
import kz.almatherm.mobile.model.MapItem;
import kz.almatherm.mobile.model.db.AppDatabase;

public class AddressesModel {
    private AddressesPresenter presenter;
    private AppDatabase database;
    private Context context;

    public AddressesModel(AddressesPresenter presenter, Context context) {
        this.presenter = presenter;
        database = ((AlmathermMobile) context.getApplicationContext()).getDatabase();
        this.context = context;
    }

    public void loadMapItems() {
        ArrayList<MapItem> mapItems = new ArrayList<>();
        Observable.just("")
                .observeOn(Schedulers.io())
                .doOnNext(e -> {
                    mapItems.addAll(database.getMapItemDao().getMapItems());
                    for (MapItem mapItem : mapItems) {
                        if (mapItem.getLat() == null || mapItem.getLng() == null) {
                            Geocoder geo = new Geocoder(context);
                            try {
                                Address address = geo.getFromLocationName(mapItem.getTitle(), 1).get(0);
                                mapItem.setLat(address.getLatitude());
                                mapItem.setLng(address.getLongitude());
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        database.getMapItemDao().insert(mapItem);
                    }
                })
                .doOnError(Throwable::printStackTrace)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> presenter.onDataLoaded(mapItems))
                .subscribe();
    }
}
