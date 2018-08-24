package kz.almatherm.mobile.module.category;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kz.almatherm.mobile.AlmathermMobile;
import kz.almatherm.mobile.model.SubCategory;
import kz.almatherm.mobile.model.db.AppDatabase;

class SubCategoryModel {
    private AppDatabase database;
    private SubCategoryPresenter presenter;

    public SubCategoryModel(Context context, SubCategoryPresenter subCategoryPresenter) {
        database = ((AlmathermMobile) context).getDatabase();
        presenter = subCategoryPresenter;
    }

    public void loadSubCategoryByParentId(int parentId) {
        List<SubCategory> subCategories = new ArrayList<>();
        Observable.just("a")
                .observeOn(Schedulers.io())
                .doOnNext(e -> subCategories.addAll(database.getSubCategoryDao().getSubCategoryByParentId(parentId)))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> presenter.onCategoryLoaded(subCategories))
                .subscribe();

    }
}
