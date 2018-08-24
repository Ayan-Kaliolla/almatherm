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
import kz.almatherm.mobile.model.Service;
import kz.almatherm.mobile.model.SubCategory;
import kz.almatherm.mobile.model.SubService;
import kz.almatherm.mobile.model.db.AppDatabase;

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
                .doOnComplete(
                        getViewState()::completeLoad
                )
                .subscribe();

    }
}
