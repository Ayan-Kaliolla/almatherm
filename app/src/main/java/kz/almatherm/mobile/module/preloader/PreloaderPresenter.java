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
import kz.almatherm.mobile.model.MapItem;
import kz.almatherm.mobile.model.Service;
import kz.almatherm.mobile.model.SubCategory;
import kz.almatherm.mobile.model.SubService;
import kz.almatherm.mobile.model.db.AppDatabase;

@InjectViewState
public class PreloaderPresenter extends MvpPresenter<PreloaderView> {
    private PreloaderModel model;
    private Context context;
    private boolean categoryLoaded = false;
    private boolean mapItemsLoaded = false;

    public PreloaderPresenter(Context context) {
        this.model = new PreloaderModel(this);
        this.context = context;
    }

    public void loadData() {
        model.loadData();
    }

    public void onMapItemsLoaded(ArrayList<MapItem> mapItems) {
        Observable.just(mapItems)
                .observeOn(Schedulers.io())
                .doOnNext(e -> {
                    AppDatabase database = ((AlmathermMobile) context.getApplicationContext()).getDatabase();
                    database.getMapItemDao().insert(e);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                            mapItemsLoaded = true;
                            if (categoryLoaded) {
                                getViewState().completeLoad();
                            }
                        }
                )
                .subscribe();
    }

    public void onCategoryLoaded(ArrayList<Category> categories) {
        Observable.just(categories)
                .observeOn(Schedulers.io())
                .doOnNext(e -> {
                    AppDatabase database = ((AlmathermMobile) context.getApplicationContext()).getDatabase();
                    database.getCategoryDao().insert(e);

                    for (Category category : e) {
                        database.getSubCategoryDao().insert(category.getSubCatalogs());

                        for (SubCategory subCategory : category.getSubCatalogs()) {
                            if (subCategory.getServices() != null) {
                                database.getServiceDao().insert(subCategory.getServices());
                                for (Service service : subCategory.getServices()) {
                                    service.setParentId(subCategory.getId());
                                    if (service.getSubServices() != null) {
                                        database.getSubServiceDao().insert(service.getSubServices());
                                        for (SubService subService : service.getSubServices()) {
                                            subService.setParentId(service.getId());
                                        }
                                    }
                                }
                            }
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                            categoryLoaded = true;
                            if (mapItemsLoaded) {
                                getViewState().completeLoad();
                            }
                        }
                )
                .subscribe();
    }
}
